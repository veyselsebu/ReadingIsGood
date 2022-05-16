package com.tr.getir.ReadingIsGood.Config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.net.HttpHeaders;
import com.tr.getir.ReadingIsGood.Model.SystemRequestLog;
import com.tr.getir.ReadingIsGood.Service.SystemRequestLogService;
import com.tr.getir.ReadingIsGood.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class RequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    @Autowired
    SystemRequestLogService systemRequestLogService;
    @Autowired
    UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);


        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                response.getCharacterEncoding());

        LOGGER.info(
                "FINISHED PROCESSING : METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}; TIM TAKEN={}",
                request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
                timeTaken);

        SystemRequestLog systemRequestLog = new SystemRequestLog();
        systemRequestLog.setRequestDate(new Date(startTime));
        systemRequestLog.setRequest(requestBody);
        systemRequestLog.setRequestUri(request.getRequestURI());
        systemRequestLog.setResponse(responseBody);
        systemRequestLog.setStatus(response.getStatus());
        systemRequestLog.setRemoteAddress(request.getRemoteAddr());

        if (request.getHeader(HttpHeaders.AUTHORIZATION) != null && !(request.getHeader(HttpHeaders.AUTHORIZATION).equals(""))){
            systemRequestLog.setUser(userService.getTokenToUser(request));
        }

        systemRequestLogService.addLog(systemRequestLog);


        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {

            return new String(contentAsByteArray, 0, contentAsByteArray.length);

    }

}