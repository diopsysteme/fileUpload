package org.diopsysteme.fileupload.model.dtos.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class FileDownloadResponseDto {
    private Long id;
    private String fileName;
    private String fileType;
    private byte[] content;

    public ResponseEntity<byte[]> toResponseEntity() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.valueOf(fileType))
                .body(content);
    }
}
