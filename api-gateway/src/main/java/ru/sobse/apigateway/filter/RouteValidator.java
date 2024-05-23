package ru.sobse.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> openEndPoints = List.of(
            "/auth/login",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecure
            = request -> !openEndPoints.contains(request.getURI().getPath());
}
