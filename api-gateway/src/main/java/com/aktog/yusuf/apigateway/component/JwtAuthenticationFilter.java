package com.aktog.yusuf.apigateway.component;


import com.aktog.yusuf.apigateway.config.JwtAuthenticationConfig;
import jakarta.ws.rs.BadRequestException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import static com.aktog.yusuf.apigateway.util.SecurityUtils.AUTH_KEY;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationConfig> {

    private final RouteValidator routeValidator;

    public JwtAuthenticationFilter(Class<JwtAuthenticationConfig> configClass, RouteValidator routeValidator) {
        super(configClass);
        this.routeValidator = routeValidator;
    }

    @Override
    public GatewayFilter apply(JwtAuthenticationConfig config) {
        return (exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){

                if(!exchange.getRequest().getHeaders().containsKey(AUTH_KEY)){
                    throw new BadRequestException("No auth info found !");
                }
            }
            
            return chain.filter(exchange);
        };
    }
}
