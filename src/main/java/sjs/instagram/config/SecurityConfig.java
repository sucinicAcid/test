package sjs.instagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
TODO
로그아웃 접근제어 url 어케할지 고민
 */

@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/loginForm").permitAll()
                .requestMatchers("/loginProc").permitAll()
                .requestMatchers("/joinForm").permitAll()
                .requestMatchers("/joinProc").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .formLogin()
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/loginProc")
                    .defaultSuccessUrl("/");
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
