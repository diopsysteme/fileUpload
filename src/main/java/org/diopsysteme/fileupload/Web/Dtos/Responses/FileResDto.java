package org.diopsysteme.fileupload.Web.Dtos.Responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FileResDto {
    private Long id;
    private String fileName;
    private String fileType;
    private String fileExtension;
    private LocalDate date;
}
