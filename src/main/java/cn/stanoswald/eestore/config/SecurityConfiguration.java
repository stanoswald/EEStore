package cn.stanoswald.eestore.config;

import cn.stanoswald.eestore.service.impl.PersistentLoginServiceImpl;
import cn.stanoswald.eestore.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/admin/api/**").hasAuthority("ADMIN")
                        .antMatchers("/user/api/**").hasAnyAuthority("USER", "ADMIN")
                        .antMatchers("/public/api/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(configurer -> configurer
                        .loginPage("/auth/unauthorized")
                        .loginProcessingUrl("/auth/login")
                        .successForwardUrl("/auth/login/success")
                        .failureForwardUrl("/auth/login/failure")
                        .permitAll())
                .httpBasic().disable()
                .rememberMe(configurer -> configurer
                        .userDetailsService(userDetailsService())
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(60 * 60 * 24 * 7))
                .logout(configurer -> configurer
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/logout/success").permitAll())
                .csrf().disable()
                .authenticationProvider(authProvider());

        return http.build();
    }

    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(userDetailsService());
        impl.setPasswordEncoder(passwordEncoder());
        impl.setHideUserNotFoundExceptions(false);
        return impl;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new PersistentLoginServiceImpl();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
