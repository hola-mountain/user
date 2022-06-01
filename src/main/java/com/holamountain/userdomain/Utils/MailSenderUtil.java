package com.holamountain.userdomain.Utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.holamountain.userdomain.Utils.SHA512.getSHA512Token;

@RequiredArgsConstructor
@Component
public class MailSenderUtil {
    private final JavaMailSenderImpl javaMailSender;
    private final StringRedisTemplate stringRedisTemplate;

    @Async
    public void sendMail(String email, String username, int type) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("[본인인증] 회원가입 이메일 인증");
            int rand = new Random().nextInt(999999);
            String formatted = String.format("%06d", rand);
            String hash = getSHA512Token(username, formatted);
            String redisKey = null;
            String htmlStr = null;
            if (type == 0) {
                redisKey = "email-" + username;
                stringRedisTemplate.opsForValue().set(redisKey, hash);
                htmlStr = "안녕하세요 " + username + "님. 인증하기를 눌러주세요"
                        + "<a href='http://54.180.124.185:8080" + "/users/auth/verify?nickname=" + username + "&key=" + hash + "'>인증하기</a></p>";
            }

            stringRedisTemplate.expire(redisKey, 60 * 24 * 1000, TimeUnit.MILLISECONDS); // for one day

            message.setText(htmlStr, "UTF-8", "html");
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
