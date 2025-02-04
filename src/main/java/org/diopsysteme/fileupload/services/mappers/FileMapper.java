package org.diopsysteme.fileupload.services.mappers;

import org.diopsysteme.fileupload.domain.data.entities.File;
import org.diopsysteme.fileupload.model.dtos.requests.FileRequestDto;
import org.diopsysteme.fileupload.model.dtos.responses.FileDownloadResponseDto;
import org.diopsysteme.fileupload.model.dtos.responses.FileResponseDto;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import prog.dependancy.Web.Mappper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends GenericMapper<File, FileRequestDto, FileResponseDto> {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);
    @Mapping(source = "fileData", target = "content")
    FileDownloadResponseDto toDownloadDto(File file);
    default FileDownloadResponseDto toDownloadDtoWithContent(File file, byte[] content) {
        FileDownloadResponseDto dto = toDownloadDto(file);
        dto.setContent(content);
        return dto;
    }
}
