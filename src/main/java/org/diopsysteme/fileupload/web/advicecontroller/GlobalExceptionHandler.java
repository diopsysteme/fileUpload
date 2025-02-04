package org.diopsysteme.fileupload.web.advicecontroller;

import lombok.extern.slf4j.Slf4j;
import org.diopsysteme.fileupload.model.dtos.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import prog.dependancy.Exceptions.ResourceNotFoundException;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiResponse errorResponse = ApiResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        log.error("Resource not found: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)

                .body(errorResponse);
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(FileNotFoundException e) {
        ApiResponse errorResponse = ApiResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        log.error("Resource not found: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)

                .body(errorResponse);
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception e) {
        ApiResponse errorResponse = ApiResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred "+ e.getMessage())
                .build();

        log.error("Unexpected error: {}", e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }
}
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
//        // Log the exception with stack trace
//        logger.error("Unexpected error occurred: ", ex);
//
//        // Create response map
//        Map<String, Object> response = new HashMap<>();
//        response.put("timestamp", LocalDateTime.now().format(DATE_FORMATTER));
//        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        response.put("success", false);
//        response.put("message", ex.getMessage());
//        response.put("data", null);
//
//        // In development, you might want to include the stack trace
//        if (isDevelopmentProfile()) {
//            response.put("debug", ex.getMessage());
//            response.put("trace", ex.getStackTrace());
//        }
//
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    // Add specific exception handlers
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("timestamp", LocalDateTime.now().format(DATE_FORMATTER));
//        response.put("status", HttpStatus.BAD_REQUEST.value());
//        response.put("success", false);
//        response.put("message", "Paramètres invalides");
//        response.put("data", null);
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    private boolean isDevelopmentProfile() {
//        // Implement your logic to check if running in development mode
//        // For example, check for a specific environment variable or Spring profile
//        return System.getProperty("spring.profiles.active", "").contains("dev");
//    }
//}