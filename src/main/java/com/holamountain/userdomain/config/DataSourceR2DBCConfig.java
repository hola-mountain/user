package com.holamountain.userdomain.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceR2DBCConfig extends AbstractR2dbcConfiguration {

    @Override
    @Bean("customR2ConnectionFactory")
    @Primary
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get("r2dbc:mysql://minjunkang:alswnsrkdA1$@15.164.98.73:3306/holamuser?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Seoul");
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}