package org.diopsysteme.fileupload.services.strategy.interfaces;

import org.diopsysteme.fileupload.domain.data.enums.StorageType;
import org.diopsysteme.fileupload.domain.entities.File;
import org.diopsysteme.fileupload.model.dtos.requests.FileRequestDto;

import java.io.IOException;

public interface StorageStrategy {
    StorageType getStorageType();
    void store(File file, FileRequestDto fileRequestDto) throws IOException;
    byte[] get(File file) throws IOException;
}
