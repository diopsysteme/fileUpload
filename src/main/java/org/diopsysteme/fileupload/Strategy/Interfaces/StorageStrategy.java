package org.diopsysteme.fileupload.Strategy.Interfaces;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface StorageStrategy {
    void store(File file, FileReqDto fileReqDto) throws IOException;
    byte[] get(File file) throws IOException;
}
