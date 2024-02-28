package com.folksdevsecurity.basicauth.security;

import com.folksdevsecurity.basicauth.model.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Component
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security, HandlerMappingIntrospector introspector)throws Exception{

        MvcRequestMatcher.Builder mvcRequestBuilder = new MvcRequestMatcher.Builder(introspector);

        security
                .headers(x->x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(csrfConfig ->
                                csrfConfig.ignoringRequestMatchers(mvcRequestBuilder.pattern("/public/**"))
                                        .ignoringRequestMatchers(PathRequest.toH2Console())
                        )
                .authorizeHttpRequests(x->
                        x       .requestMatchers(mvcRequestBuilder.pattern("/public/**")).permitAll()
                                .requestMatchers(mvcRequestBuilder.pattern("/private/admin/**")).hasRole("ADMIN")
                                .requestMatchers(mvcRequestBuilder.pattern("/private/**")).hasAnyRole(
                                        Role.ROLE_MOD.getValue(), 
                                        Role.ROLE_ADMIN.getValue(),
                                        Role.ROLE_FSK.getValue())

                                .requestMatchers(PathRequest.toH2Console()).hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return security.build();
    }
}
