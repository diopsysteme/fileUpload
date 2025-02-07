package org.diopsysteme.fileupload.services.validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.diopsysteme.fileupload.services.Impl.FileUploadPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class FileSizeValidator implements ConstraintValidator<FileValidator, MultipartFile> {
@Value("${fileUpload.maxFileSize}")
@Autowired
    FileUploadPropertiesService fileUploadPropertiesService;
    private long maxFileSize;
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        System.out.println(isAllowedSize(file,context));
        System.out.println(isAllowedType(file,context));
       return (isAllowedSize(file,context) && isAllowedType(file,context));
    }
    private boolean isAllowedSize(MultipartFile file, ConstraintValidatorContext context) {
        System.out.println("here1 valid1");

        return file.getSize() <= maxFileSize;
    }
    private boolean isAllowedType(MultipartFile file , ConstraintValidatorContext context){
        System.out.println("here1 valid2");
        List<String> allowedTypes = FileUploadPropertiesService.getAllowedTypes();
        String contentType = file.getContentType();
        if(!allowedTypes.isEmpty()&&(contentType ==null || !allowedTypes.contains(contentType))){

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("le type de fichier: "+contentType+" n'est pas autorise");
            return false;
        }
        System.out.println("good ");
        return true;
    }
}
