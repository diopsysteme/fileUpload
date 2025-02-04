package org.diopsysteme.fileupload.model.dtos.responses;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
public class FileResponseDto extends RepresentationModel<FileResponseDto> {
    private Long id;
    private String fileName2;
    private String fileType;
    private String fileExtension;
    private LocalDate date;
    
}
