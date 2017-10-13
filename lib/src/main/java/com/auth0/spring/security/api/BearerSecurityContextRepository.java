package com.auth0.spring.security.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BearerSecurityContextRepository implements SecurityContextRepository {
    private final static Logger logger = LoggerFactory.getLogger(BearerSecurityContextRepository.class);

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        String token = tokenFromRequest(requestResponseHolder.getRequest());
        return TokenUtils.createSecurityContext(token, logger);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return tokenFromRequest(request) != null;
    }

    private String tokenFromRequest(HttpServletRequest request) {
        return TokenUtils.tokenFromHeader(request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
