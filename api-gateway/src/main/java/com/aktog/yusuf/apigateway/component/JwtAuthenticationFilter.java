package com.aktog.yusuf.apigateway.component;


import jakarta.ws.rs.BadRequestException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

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

                Map headers = exchange.getRequest().getHeaders();
                if(!headers.containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new BadRequestException("No authorization header...!");
                }

                String authHeader = (String) headers.get(HttpHeaders.AUTHORIZATION);
                try{
                    jwtTokenValidator.validateToken(authHeader);
                }
                catch (Exception e){
                    throw new RuntimeException("Unathorized access denied...");
                }

            }
            
            return chain.filter(exchange);
        };
    }

    public static class DummyConfig{

    }
}
