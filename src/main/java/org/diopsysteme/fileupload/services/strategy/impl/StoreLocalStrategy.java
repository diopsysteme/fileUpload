package org.diopsysteme.fileupload.services.strategy.impl;

import org.diopsysteme.fileupload.domain.data.enums.StorageType;
import org.diopsysteme.fileupload.domain.entities.File;
import org.diopsysteme.fileupload.services.strategy.interfaces.StorageStrategy;
import org.diopsysteme.fileupload.model.dtos.requests.FileRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.diopsysteme.fileupload.config.Constants.DIR_CREATING_ERROR;
import static org.diopsysteme.fileupload.config.Constants.FILE_STORAGE_ERROR;


@Component
public class StoreLocalStrategy implements StorageStrategy {
    @Value("${storage.local.path}")
    private String storagePath;
    public StoreLocalStrategy(@Value("${storage.local.path}") String storagePath)  throws IOException {
        this.storagePath = storagePath;
        try {
            Path path = Paths.get(storagePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (  IOException e) {
            throw new IOException(DIR_CREATING_ERROR, e);
        }

    }
    @Override
    public void store(File file, FileRequestDto fileRequestDto) throws IOException {
        String UID = UUID.randomUUID().toString();
        String extension="";
        if (!(file.getFileExtension().isEmpty())) {
            extension = file.getFileExtension();
        }

        try {
            String path = storagePath + "/" + file.getFileName() + "-" + UID + "."+ extension;
            Files.copy(fileRequestDto.getFile().getInputStream(), Paths.get(path));
            file.setFileNameLocal(file.getFileName() + "-" + UID +"."+ extension);
        } catch (IOException e) {
            throw new FileNotFoundException(FILE_STORAGE_ERROR);
        }
    }
    @Override
    public byte[] get(File file) throws FileNotFoundException {
        try {
        Path filePath = Paths.get(storagePath, file.getFileNameLocal());
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new FileNotFoundException(FILE_STORAGE_ERROR);
        }
    }
    @Override
    public StorageType getStorageType() {
        return StorageType.LOCAL;
    }
}
