package com.tr.getir.ReadingIsGood.Config;

import com.tr.getir.ReadingIsGood.Enums.Role;
import com.tr.getir.ReadingIsGood.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable().authorizeRequests().antMatchers(
                        "/authentication",
                        "/authentication/",
                        "/authentication/new-account",
                        "/authentication/new-account/",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/swagger-ui.html/**",
                        "/swagger-resources/**",
                        "/swagger-resources",
                        "/v2/**",
                        "/api-docs",
                        "/webjars/**",
                        "/book/get",
                        "/book/get/**"
                ).permitAll()
                .antMatchers("/book",
                        "/book/**",
                        "/statistic/admin",
                        "/statistic/admin/**",
                        "/system-log",
                        "/authentication/new-admin-account/",
                        "/authentication/new-admin-account",
                        "/system-log/**").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers(
                        "/user-address",
                        "/user-address/**",
                        "/shopping-cart",
                        "/shopping-cart/**",
                        "/order",
                        "/order/**",
                        "/statistic",
                        "/statistic/",
                        "/user-address",
                        "/user-address/**"
                ).hasAnyAuthority(Role.CUSTOMER.name())
                .anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
