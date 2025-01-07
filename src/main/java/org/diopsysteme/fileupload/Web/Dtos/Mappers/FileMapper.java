package org.diopsysteme.fileupload.Web.Dtos.Mappers;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileDownloadDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileResDto;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import prog.dependancy.Web.Mappper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends GenericMapper<File, FileReqDto, FileResDto> {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);
    @Mapping(source = "fileData", target = "content")
    FileDownloadDto toDownloadDto(File file);
    default FileDownloadDto toDownloadDtoWithContent(File file, byte[] content) {
        FileDownloadDto dto = toDownloadDto(file);
        dto.setContent(content);
        return dto;
    }
}
