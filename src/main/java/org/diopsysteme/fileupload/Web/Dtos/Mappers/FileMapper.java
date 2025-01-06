package org.diopsysteme.fileupload.Web.Dtos.Mappers;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileResDto;
import org.mapstruct.factory.Mappers;
import prog.dependancy.Web.Mappper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends GenericMapper<File, FileReqDto, FileResDto> {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);
}
