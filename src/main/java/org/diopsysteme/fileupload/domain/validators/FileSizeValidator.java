package org.diopsysteme.fileupload.domain.validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import static org.diopsysteme.fileupload.domain.data.constants.FileConstants.ALLOWED_TYPES;
import static org.diopsysteme.fileupload.domain.data.constants.FileConstants.MAX_FILE_SIZE;


public class FileSizeValidator implements ConstraintValidator<FileValidator, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        System.out.println(ALLOWED_TYPES);
        System.out.println(isAllowedSize(file,context));
        System.out.println(isAllowedType(file,context));
       return (isAllowedSize(file,context) && isAllowedType(file,context));
    }
    private boolean isAllowedSize(MultipartFile file, ConstraintValidatorContext context) {
        System.out.println("here1 valid1");

        return file.getSize() <= MAX_FILE_SIZE;
    }
    private boolean isAllowedType(MultipartFile file , ConstraintValidatorContext context){
        System.out.println("here1 valid2");

        String contentType = file.getContentType();
        if(!ALLOWED_TYPES.isEmpty()&&(contentType ==null || !ALLOWED_TYPES.contains(contentType))){

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("le type de fichier: "+contentType+" n'est pas autorise");
            return false;
        }
        System.out.println("good ");
        return true;
    }
}
