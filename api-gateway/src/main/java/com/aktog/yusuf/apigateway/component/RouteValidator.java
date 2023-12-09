package com.aktog.yusuf.apigateway.component;


import com.ctc.wstx.shaded.msv_core.util.Uri;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/eureka",
            "/auth/generate",
            "/auth/create-user"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> openApiEndpoints
            .stream()
            .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

}
