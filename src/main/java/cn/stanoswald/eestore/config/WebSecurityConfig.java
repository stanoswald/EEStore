package cn.stanoswald.eestore.config;

import cn.stanoswald.eestore.service.impl.PersistentLoginServiceImpl;
import cn.stanoswald.eestore.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/admin/api/**").hasAuthority("ADMIN")
                .antMatchers("/user/api/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/public/api/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/auth/unauthorized")
                .loginProcessingUrl("/auth/login")
                .successForwardUrl("/auth/login/success")
                .failureForwardUrl("/auth/login/failure")
                .permitAll().and()
                .httpBasic().disable()
                .rememberMe().userDetailsService(userDetailsService())
                .tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60 * 60 * 24 * 7).and()
                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/logout/success").permitAll().and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(userDetailsService());
        impl.setPasswordEncoder(passwordEncoder());
        impl.setHideUserNotFoundExceptions(false);
        return impl;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
