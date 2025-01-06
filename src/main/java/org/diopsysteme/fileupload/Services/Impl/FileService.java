package org.diopsysteme.fileupload.Services.Impl;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Data.Repositories.FileRepository;
import org.diopsysteme.fileupload.Web.Dtos.Mappers.FileMapper;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import prog.dependancy.Services.Interfaces.AbstractService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class FileService extends AbstractService<File, FileReqDto, FileResDto> {
@Autowired
FileRepository fileRepository;
    public FileService(FileRepository repository, FileMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public FileResDto save(FileReqDto fileReqDto){
        File file = new File();

        String originalFileName = fileReqDto.getFile().getOriginalFilename();
        file.setFileName(fileReqDto.getFileName() != null && !fileReqDto.getFileName().isEmpty()
                ? fileReqDto.getFileName()
                : originalFileName);

        String mimeType = fileReqDto.getFile().getContentType();
        file.setFileType(mimeType);

        file.setFileExtension(getExtension(originalFileName));

        try {
            file.setFileData(fileReqDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }

        file.setDate(LocalDate.now());

        File fileS = repository.save(file);
        return mapper.toDto(fileS);
    }
    public ResponseEntity<byte[]> downloadFile(Long id) throws FileNotFoundException {
        File file = repository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.valueOf(file.getFileType()))
                .body(file.getFileData());
    }
    private String getExtension(String fileName) {
        return fileName.contains(".")
                ? fileName.substring(fileName.lastIndexOf(".") + 1)
                : "unknown";
    }

    public Page<FileResDto> searchFiles(String searchQuery, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<File> filePage;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            filePage = fileRepository.findByFileName(searchQuery, pageable);
        } else {
            filePage = repository.findAll(pageable);
        }
        return filePage.map(mapper::toDto);
    }
}
