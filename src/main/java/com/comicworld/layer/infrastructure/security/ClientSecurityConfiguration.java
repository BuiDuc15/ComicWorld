package com.comicworld.layer.infrastructure.security;

import com.comicworld.layer.domain.filter.client.ClientRememberMeFilterV1;
import com.comicworld.layer.domain.filter.client.ClientUsernamePasswordAuthenticationFilterV1;
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
@Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
public class ClientSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("clientAuthenticationSuccessHandlerImplV1")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("clientAuthenticationFailureHandlerImplV1")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    @Qualifier("clientAccessDeniedHandlerImplV1")
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("clientDetailsServiceImplV1")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("clientRememberMeFilterV1")
    private ClientRememberMeFilterV1 clientRememberMeFilterV1;

    @Bean
    public PasswordEncoder clientPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(this.clientPasswordEncoder());
        provider.setUserDetailsService(this.userDetailsService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean("authenticationManagerBean2")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")
                .and()
            .csrf().disable()
            .cors()
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(this.clientRememberMeFilterV1, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/authentication/signout").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/comics/*/chapters/*/comments").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/comics/*/comments").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/comments/*/upvote").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/comments/*/dislike").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/comments/*/replies").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/replies/*/upvote").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/replies/*/dislike").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/comics/*/dislike").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/comics/*/love").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/comments/*").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/comments/*").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/replies/*").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/replies/*").hasRole("USER")
                .anyRequest().permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedHandler(this.accessDeniedHandler);
    }

    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        ClientUsernamePasswordAuthenticationFilterV1 filter = new ClientUsernamePasswordAuthenticationFilterV1(this.authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(this.authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(this.authenticationFailureHandler);
        filter.setFilterProcessesUrl(Env.CLIENT_SIGN_IN_ENDPOINT);
        return filter;
    }
}
