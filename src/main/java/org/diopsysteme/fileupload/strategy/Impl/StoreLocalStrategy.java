package org.diopsysteme.fileupload.strategy.Impl;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Component
public class StoreLocalStrategy implements StorageStrategy {
    @Value("${storage.local.path}")
    private String storagePath;
    public StoreLocalStrategy(@Value("${storage.local.path}") String storagePath) {
        this.storagePath = storagePath;
        try {
            Path path = Paths.get(storagePath);
            if (!Files.exists(path)) {
                Files.createDirectories(Paths.get(storagePath));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create storage directory: " + storagePath, e);
        }
    }


    @Override
    public void store(File file, FileReqDto fileReqDto) throws IOException {
        String UID = UUID.randomUUID().toString();

        String originalFileName = fileReqDto.getFile().getOriginalFilename();
        String extension="";
        if (!(file.getFileExtension().isEmpty())) {
            extension = file.getFileExtension();
        }

        String path = storagePath + "/" + file.getFileName() + "-" + UID + "."+ extension;
        Files.copy(fileReqDto.getFile().getInputStream(), Paths.get(path));
        file.setFileNameLocal(file.getFileName() + "-" + UID +"."+ extension);
    }

    @Override
    public byte[] get(File file) throws IOException {
        if (file.getFileNameLocal() == null) {
            throw new FileNotFoundException("Nom du fichier local non trouvé");
        }

        Path filePath = Paths.get(storagePath, file.getFileNameLocal());
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Fichier non trouvé: " + filePath);
        }
        return Files.readAllBytes(filePath);
    }
    @Override
    public StorageType getStorageType() {
        return StorageType.LOCAL;
    }
}
