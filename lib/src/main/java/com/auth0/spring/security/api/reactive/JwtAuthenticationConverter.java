package com.auth0.spring.security.api.reactive;

import com.auth0.spring.security.api.TokenUtils;
import com.auth0.spring.security.api.authentication.PreAuthenticatedAuthenticationJsonWebToken;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @author Sebastien Astie
 */
public class JwtAuthenticationConverter implements Function<ServerWebExchange, Mono<Authentication>> {

    @Override
    public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {
        String token = TokenUtils.tokenFromHeader(serverWebExchange.getRequest()
                                                    .getHeaders()
                                                    .getFirst(HttpHeaders.AUTHORIZATION));
        return Mono.justOrEmpty(PreAuthenticatedAuthenticationJsonWebToken.usingToken(token));
    }
}
