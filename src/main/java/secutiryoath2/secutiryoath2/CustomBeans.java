package secutiryoath2.secutiryoath2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CustomBeans {


    //加密算法
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        System.out.println("CustomBeans----BCryptPasswordEncoder======");
        return new BCryptPasswordEncoder();
    }
}
