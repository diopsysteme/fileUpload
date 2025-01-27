package org.diopsysteme.fileupload.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Component
public class ResponseFilter extends OncePerRequestFilter {

    private final List<String> excludeUrlPatterns = Arrays.asList(
            "/swagger-ui.html",
            "/swagger-ui/",
            "/v3/api-docs",
            "/swagger-resources",
            "/webjars/swagger-ui"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return excludeUrlPatterns.stream()
                .anyMatch(pattern -> path.startsWith(pattern));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);

            byte[] responseArray = responseWrapper.getContentAsByteArray();
            String responseBody = new String(responseArray, responseWrapper.getCharacterEncoding());

            // Construire la réponse formatée
            String formattedResponse = formatResponse(
                    responseWrapper.getStatus(),
                    responseWrapper.getStatus() < 400,
                    responseWrapper.getStatus() < 400 ? "Opération réussie" : "Une erreur est survenue",
                    responseBody
            );

            responseWrapper.resetBuffer();
            responseWrapper.setContentType("application/json");
            responseWrapper.setCharacterEncoding("UTF-8");
            responseWrapper.getWriter().write(formattedResponse);

        } catch (Exception ex) {
            // Propager l'exception pour qu'elle soit capturée par le gestionnaire global
            throw new ServletException("Erreur lors du traitement de la requête", ex);
        } finally {
            responseWrapper.copyBodyToResponse();
        }
    }

    private String formatResponse(int status, boolean success, String message, String data) {
        StringBuilder json = new StringBuilder();

        // Formater le timestamp (LocalDateTime) pour JSON
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        json.append("{");
        json.append("\"timestamp\":\"").append(timestamp).append("\",");
        json.append("\"status\":").append(status).append(",");
        json.append("\"success\":").append(success).append(",");
        json.append("\"message\":\"").append(escapeJson(message)).append("\",");
        json.append("\"data\":").append(data != null && !data.isBlank() ? data : "null");
        json.append("}");

        return json.toString();
    }

    private String escapeJson(String value) {
        return value.replace("\"", "\\\"");
    }
}
