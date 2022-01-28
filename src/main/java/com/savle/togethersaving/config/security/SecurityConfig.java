package com.savle.togethersaving.config.security;

import com.savle.togethersaving.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserRepository userRepository;
    private final CorsConfig corsConfig;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //개발용 코드
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll();

        //실제 사용될 코드. 개발할 때 토큰이 걸리적 거릴까봐 주석처리 합니다.
//        http
//                .addFilter(corsConfig.corsFilter())
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
//                .authorizeRequests()
//                .antMatchers("/api/v1/users/**")
//                .hasRole("USER")
//                .antMatchers("/api/v1/users/reviews/**")
//                .hasRole("USER")
//                .anyRequest().permitAll();
    }


}