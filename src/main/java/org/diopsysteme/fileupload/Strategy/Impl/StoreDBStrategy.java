package org.diopsysteme.fileupload.Strategy.Impl;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
@Component
public class StoreDBStrategy implements StorageStrategy {
    @Override
    public void store(File file, FileReqDto fileReqDto) throws IOException {
        file.setFileData(fileReqDto.getFile().getBytes());
    };
    @Override
    public byte[] get(File file) throws IOException {
        if (file.getFileData() == null) {
            throw new FileNotFoundException("Fichier non trouvé dans la base de données");
        }
        return file.getFileData();
    }


}
