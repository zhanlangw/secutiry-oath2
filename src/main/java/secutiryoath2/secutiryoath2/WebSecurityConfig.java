package secutiryoath2.secutiryoath2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN") //设置访问角色
                .antMatchers("/vip/**").hasAuthority("VIP") //设置访问权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
//	            .loginPage("/login") //设置自定义登陆页
                .usernameParameter("username")  //自定义用户名name值
//                .defaultSuccessUrl("/userRole")
                .passwordParameter("password")  //自定义密码name值
                .failureUrl("/login?error") //登录失败则重定向到此URl
                .permitAll() //登录页都可以访问
                .and()
                .logout()//开启自动配置的注销功能
                .logoutSuccessUrl("/")//注销成功后返回到页面并清空Session
                .and()
                .rememberMe()
                .rememberMeParameter("remember")//自定义rememberMe的name值，默认remember-Me
                .tokenValiditySeconds(86400);//记住我的时间/秒
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }


    /**
     * 定义认证规则
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         保存用户信息到内存中
//		auth.inMemoryAuthentication()
//			.withUser("root")
//			.password(passwordEncoder.encode("1234")).roles("ROLE_USER", "ROLE_VIP").authorities("ADMIN");
//		auth.authenticationProvider(new CustomAuthenticationProvider());
        auth.userDetailsService(userDetailsService());
    }
}
