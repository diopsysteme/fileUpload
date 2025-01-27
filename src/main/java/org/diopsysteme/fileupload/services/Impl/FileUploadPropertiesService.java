package org.diopsysteme.fileupload.services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUploadPropertiesService {
    @Value("#{'${fileUpload.allowedTypes}'.split(',')}")
    private List<String> allowedTypes;

    // Remove the constructor since we're using @Value injection

    public List<String> getAllowedTypes() {
        return allowedTypes;
    }
}