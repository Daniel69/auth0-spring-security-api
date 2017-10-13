package com.auth0.spring.security.api.reactive;

import com.auth0.spring.security.api.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.context.SecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class BearerSecurityContextRepository implements SecurityContextRepository {
    private final static Logger logger = LoggerFactory.getLogger(BearerSecurityContextRepository.class);

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String token = tokenFromRequest(exchange.getRequest());
        return Mono.justOrEmpty(TokenUtils.createSecurityContext(token, logger));
    }


    private String tokenFromRequest(ServerHttpRequest request) {
        return TokenUtils.tokenFromHeader(request.getHeaders()
                                                .getFirst(HttpHeaders.AUTHORIZATION));
    }

}
