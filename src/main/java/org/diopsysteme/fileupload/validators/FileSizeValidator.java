package org.diopsysteme.fileupload.validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.diopsysteme.fileupload.services.Impl.FileUploadPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


public class FileSizeValidator implements ConstraintValidator<FileValidator, MultipartFile> {
    private final FileUploadPropertiesService fileUploadPropertiesService;
    private long maxSize;

    @Autowired
    public FileSizeValidator(FileUploadPropertiesService fileUploadPropertiesService) {
        this.fileUploadPropertiesService = fileUploadPropertiesService;
    }
    @Override
    public void initialize(FileValidator constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
    }
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        System.out.println(fileUploadPropertiesService.getAllowedTypes());
        System.out.println(isAllowedSize(file,context));
        System.out.println(isAllowedType(file,context));
       return (isAllowedSize(file,context) && isAllowedType(file,context));
    }
    private boolean isAllowedSize(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return true;
        }
        return file.getSize() <= maxSize;
    }
    private boolean isAllowedType(MultipartFile file , ConstraintValidatorContext context){
        String contentType = file.getContentType();
        System.out.println(fileUploadPropertiesService);
        if(!fileUploadPropertiesService.getAllowedTypes().isEmpty()&&(contentType ==null || !fileUploadPropertiesService.getAllowedTypes().contains(contentType))){

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("le type de fichier: "+contentType+" n'est pas autorise");
            return false;
        }

        System.out.println("good ");
        return true;
    }
}
