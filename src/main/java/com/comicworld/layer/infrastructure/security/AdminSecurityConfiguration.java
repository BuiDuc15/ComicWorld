package com.comicworld.layer.infrastructure.security;

import com.comicworld.layer.domain.filter.admin.AdminRememberMeFilterV1;
import com.comicworld.layer.domain.filter.admin.AdminUsernamePasswordAuthenticationFilterV1;
import com.comicworld.utils.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("adminAuthenticationSuccessHandlerV1")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("adminAuthenticationFailureHandlerV1")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    @Qualifier("adminAccessDeniedHandlerV1")
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("adminDetailsServiceImplV1")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("adminRememberMeFilterV1")
    private AdminRememberMeFilterV1 adminRememberMeFilterV1;

    @Bean
    public PasswordEncoder adminPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(this.adminPasswordEncoder());
        provider.setUserDetailsService(this.userDetailsService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/admin/**")
                .and()
            .csrf().disable()
            .cors()
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(this.adminRememberMeFilterV1, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, Env.ADMIN_SIGN_IN_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.POST, "/admin/v1/access-token").permitAll()
                .anyRequest().hasRole("ADMIN")
                .and()
            .exceptionHandling()
                .accessDeniedHandler(this.accessDeniedHandler);
    }

    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        AdminUsernamePasswordAuthenticationFilterV1 filter = new AdminUsernamePasswordAuthenticationFilterV1(this.authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(this.authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(this.authenticationFailureHandler);
        filter.setFilterProcessesUrl(Env.ADMIN_SIGN_IN_ENDPOINT);
        return filter;
    }

}







































