package org.diopsysteme.fileupload.Web.Dtos.Requests;

import lombok.Data;
import org.diopsysteme.fileupload.Data.Entities.File;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileReqDto {
    private MultipartFile file;
    private String fileName;
}
