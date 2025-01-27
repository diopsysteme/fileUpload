package org.diopsysteme.fileupload.Web.Dtos.Responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Data
@Builder
public class ApiResponse  {
    private String timestamp;
    private int status;
    private boolean success;
    private String message;
    private Object data;

}

