package org.diopsysteme.fileupload.Validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;


public class FileSizeValidator implements ConstraintValidator<MaxFileSize, MultipartFile> {
    private long maxSize;
    @Override
    public void initialize(MaxFileSize constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
    }
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return true;
        }
        return file.getSize() <= maxSize;
    }
}
