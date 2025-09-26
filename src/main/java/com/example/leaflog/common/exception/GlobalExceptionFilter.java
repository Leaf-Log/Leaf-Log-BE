package com.example.leaflog.common.exception;

import com.example.leaflog.common.exception.model.CustomException;
import com.example.leaflog.common.exception.model.ErrorCode;
import com.example.leaflog.common.exception.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (CustomException e){
            log.error("CustomException 발생!", e);
            ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
            errorToJson(errorResponse, response);
        } catch (Exception e){
            if(e.getCause() instanceof CustomException ce){
                log.error("wrapper CustomException 발생!", ce);
                ErrorResponse errorResponse = ErrorResponse.of(ce.getErrorCode());
                errorToJson(errorResponse, response);
            } else{
                log.error("예상하지 못한 에러 발생!", e);
                ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
                errorToJson(errorResponse, response);
            }
        }
    }

    private void errorToJson(
            ErrorResponse errorResponse,
            HttpServletResponse response
    ) throws IOException{
        response.setStatus(errorResponse.statusCode());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}

