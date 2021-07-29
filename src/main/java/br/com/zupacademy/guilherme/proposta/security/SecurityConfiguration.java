package br.com.zupacademy.guilherme.proposta.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests->
                authorizeRequests
                .antMatchers(HttpMethod.POST, "/api/proposals/**").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.GET, "/api/proposals/**").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.POST, "/api/cards/**").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.GET, "/api/cards/**").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.POST, "/api/fingerprints/**").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.GET, "/actuator/prometheus").hasAuthority("SCOPE_prometheus")
                .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
