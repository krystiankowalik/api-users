package com.krystiankowalik.apiresourceusers.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/secure").authenticated().and()
                .authorizeRequests()
                .antMatchers("/normal").permitAll();
    }

    @Bean
    public RemoteTokenServices remoteTokenServices() {
        final RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl("http://localhost:9191/uaa/oauth/check_token");
        tokenServices.setClientId("my-trusted-client");
        tokenServices.setClientSecret("password");
        return tokenServices;
    }
}
