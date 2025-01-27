package org.diopsysteme.fileupload.Web.Dtos;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import prog.dependancy.Web.DTO.ApiResponse;
import org.springframework.util.AntPathMatcher;

public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final String[] EXCLUDED_PATHS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/error",
            "/actuator/**",
            "/api-docs/**"
    };

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        String path = getRequestPath();
        if (path != null) {
            for (String excludedPath : EXCLUDED_PATHS) {
                if (pathMatcher.match(excludedPath, path)) {
                    System.out.println("Exclusion appliquée pour le chemin : " + path);
                    return false;
                }
            }
        }
        System.out.println("Conseil appliqué pour le chemin : " + path);
        return true;
    }

    private String getRequestPath() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            return request.getRequestURI();
        }
        return null;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            org.springframework.http.server.ServerHttpRequest request,
            org.springframework.http.server.ServerHttpResponse response) {

        if (body instanceof ApiResponse<?> || body instanceof String) {
            return body;
        }
        if (body == null) {
            return new ApiResponse<>(null, "No data found");
        }
        return new ApiResponse<>(body, "Success");
    }
}