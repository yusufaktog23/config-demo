package com.aktog.yusuf.apigateway.component;


import jakarta.ws.rs.BadRequestException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.DummyConfig> {

    private final RouteValidator routeValidator;
    private final JwtTokenValidator jwtTokenValidator;

    public JwtAuthenticationFilter(RouteValidator routeValidator, JwtTokenValidator jwtTokenValidator) {
        super(DummyConfig.class);
        this.routeValidator = routeValidator;
        this.jwtTokenValidator = jwtTokenValidator;
    }


    @Override
    public GatewayFilter apply(DummyConfig config) {
        return (exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){

                Map<String,List<String>> headers = exchange.getRequest().getHeaders();
                if(!headers.containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new BadRequestException("No authorization header...!");
                }

                // TODO
                //  burda yaptığım validasyon sadece jwt tokeni kontrol ediyor roller vs tamamen auth-serverda kalmış oldu ve çöpe gitti
                //  ya komple buraya aktarmamız lazım ya da feign client ile oraya yönlendirip tam kontrol sağlanmalı...
                //  oradaki filteri buradaki gibi yapıp direk api gatewayde kullanabilir miyiz ?
                String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
                System.out.println("==>" + " " + authHeader);
                try{
                    jwtTokenValidator.validateToken(authHeader);
                }
                catch (Exception e){
                    throw new RuntimeException("Unathorized access denied..." + e.getMessage());
                }

            }
            
            return chain.filter(exchange);
        };
    }

    public static class DummyConfig{

    }
}
