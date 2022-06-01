package com.holamountain.userdomain.service.redis;

public interface RedisService {
    // 키-벨류 설정
    public void setValues(String token, String email);

    // 키값으로 벨류 가져오기
    public String getValues(String token);

    // 키-벨류 삭제
    public void delValues(String token);
}
