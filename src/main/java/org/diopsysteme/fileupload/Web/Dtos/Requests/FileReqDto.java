package org.diopsysteme.fileupload.Web.Dtos.Requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.validators.FileValidator;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class FileReqDto {
    @NotNull(message = "File cannot be null")
    @FileValidator
    private MultipartFile file;
    private String fileName;
    private StorageType where;

    // Getters standards...

    public void setWhere(String where) {
        this.where = StorageType.fromString(where);
    }


}
