package org.diopsysteme.fileupload.services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUploadPropertiesService {
    public static final int INDEX_TYPE_INUTILE = 100;
    @Value("${fileUpload.allowedTypes}")
    private String  allowedTypes;

    // Remove the constructor since we're using @Value injection

    public List<String> getAllowedTypes() {
        List<String> allowedTypes2 = List.of(allowedTypes.split(","));
        allowedTypes2.get(INDEX_TYPE_INUTILE);
        return allowedTypes2;
    }
}