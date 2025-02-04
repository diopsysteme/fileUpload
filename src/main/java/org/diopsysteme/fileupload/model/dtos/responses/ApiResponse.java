package org.diopsysteme.fileupload.model.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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

