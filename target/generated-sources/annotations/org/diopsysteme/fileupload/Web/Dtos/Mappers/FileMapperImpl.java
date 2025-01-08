package org.diopsysteme.fileupload.Web.Dtos.Mappers;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileDownloadDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileResDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-08T11:40:17+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class FileMapperImpl implements FileMapper {

    @Override
    public FileResDto toDto(File entity) {
        if ( entity == null ) {
            return null;
        }

        FileResDto fileResDto = new FileResDto();

        fileResDto.setId( entity.getId() );
        fileResDto.setFileName( entity.getFileName() );
        fileResDto.setFileType( entity.getFileType() );
        fileResDto.setFileExtension( entity.getFileExtension() );
        fileResDto.setDate( entity.getDate() );

        return fileResDto;
    }

    @Override
    public File toEntity(FileReqDto dto) {
        if ( dto == null ) {
            return null;
        }

        File file = new File();

        file.setFileName( dto.getFileName() );

        return file;
    }

    @Override
    public FileDownloadDto toDownloadDto(File file) {
        if ( file == null ) {
            return null;
        }

        FileDownloadDto.FileDownloadDtoBuilder fileDownloadDto = FileDownloadDto.builder();

        byte[] content = file.getFileData();
        if ( content != null ) {
            fileDownloadDto.content( Arrays.copyOf( content, content.length ) );
        }
        fileDownloadDto.id( file.getId() );
        fileDownloadDto.fileName( file.getFileName() );
        fileDownloadDto.fileType( file.getFileType() );

        return fileDownloadDto.build();
    }
}
