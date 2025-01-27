package org.diopsysteme.fileupload.strategy.Interfaces;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;

import java.io.IOException;

public interface StorageStrategy {
    StorageType getStorageType();
    void store(File file, FileReqDto fileReqDto) throws IOException;
    byte[] get(File file) throws IOException;
}
