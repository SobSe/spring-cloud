package ru.sobse.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import ru.sobse.apigateway.security.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RouteValidator routeValidator;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecure.test(exchange.getRequest())) {
                ServerHttpRequest request = exchange.getRequest();
                if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
                    throw new RuntimeException("Authorization header not present");
                }
                String auth = request.getHeaders().getFirst("Authorization");
                if (auth != null && auth.startsWith("Bearer ")) {
                    auth = auth.substring(7);
                }
                try {
                    jwtUtil.validateToken(auth);
                } catch (Exception e) {
                    throw new RuntimeException("Invalid token", e);
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
