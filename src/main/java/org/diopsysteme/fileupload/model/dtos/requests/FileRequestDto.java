package org.diopsysteme.fileupload.model.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.diopsysteme.fileupload.domain.data.enums.StorageType;
import org.diopsysteme.fileupload.domain.validators.FileValidator;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequestDto {
//TODO  preferer des nom de classe parlant de methodes ou d'attributs
    @Schema(description = "selectionner un fichier a telecharger  ")
    @NotNull(message = "File cannot be null")
    @FileValidator
    private MultipartFile file;
    @Schema(description = "donner le nom avec lequel vous voulez enregistrer votre file par defaut ça prend le nom uploade")
    private String fileName;
    @Schema(description = "donner le type de stockage Local =>pour stockage en local DB=>stockage en base de donnee", example = "LOCAL")
    private StorageType type =StorageType.LOCAL;

    // Getters standards...

    public void setType(String type) {
        this.type = StorageType.fromString(type);
    }
public String getFileName() {

    return (fileName != null && !fileName.isEmpty())
            ? fileName
            : file.getOriginalFilename();
    }

}
