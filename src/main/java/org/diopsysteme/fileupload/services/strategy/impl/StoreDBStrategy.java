package org.diopsysteme.fileupload.services.strategy.impl;

import org.diopsysteme.fileupload.domain.data.enums.StorageType;
import org.diopsysteme.fileupload.domain.entities.File;
import org.diopsysteme.fileupload.services.strategy.interfaces.StorageStrategy;
import org.diopsysteme.fileupload.model.dtos.requests.FileRequestDto;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.diopsysteme.fileupload.config.Constants.FILE_NOT_FOUND;


@Component
public class StoreDBStrategy implements StorageStrategy {
    @Override
    public void store(File file, FileRequestDto fileRequestDto) throws IOException {
        file.setFileData(fileRequestDto.getFile().getBytes());
    };
    @Override
    public byte[] get(File file) throws IOException {
        if (file.getFileData() == null) {
            throw new FileNotFoundException(FILE_NOT_FOUND);
        }
        return file.getFileData();
    }
    @Override
    public StorageType getStorageType() {
        return StorageType.DB;
    }

}
