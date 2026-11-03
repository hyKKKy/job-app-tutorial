package com.example.job_app_tutorial;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    private static final int REQUEST_CACHE_LIMIT = 1024 * 1024;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, REQUEST_CACHE_LIMIT);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            logRequestDetails(wrappedRequest);
            logResponseDetails(wrappedRequest, wrappedResponse);
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequestDetails(ContentCachingRequestWrapper wrappedRequest) {
        String requestBody = getBody(wrappedRequest.getContentAsByteArray(), wrappedRequest.getCharacterEncoding());
        logger.info("Incoming request: method='{}', URI='{}', headers='{}', body='{}'",
                wrappedRequest.getMethod(), wrappedRequest.getRequestURI(), getHeaders(wrappedRequest), requestBody);
    }

    private void logResponseDetails(ContentCachingRequestWrapper wrappedRequest,
                                    ContentCachingResponseWrapper wrappedResponse) {
        String responseBody = getBody(wrappedResponse.getContentAsByteArray(), wrappedResponse.getCharacterEncoding());
        logger.info("Request completed for URI='{}' with status='{}', body='{}'",
                wrappedRequest.getRequestURI(), wrappedResponse.getStatus(), responseBody);
    }

    private String getBody(final byte[] body, final String characterEncoding) {
        try {
            return new String(body, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to decode body", e);
            return "[unknown]";
        }
    }

    private String getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .map(headerName -> headerName + "=" + request.getHeader(headerName))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
