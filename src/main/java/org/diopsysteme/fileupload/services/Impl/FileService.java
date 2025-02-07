package org.diopsysteme.fileupload.services.Impl;

import org.diopsysteme.fileupload.domain.entities.File;
import org.diopsysteme.fileupload.repositories.FileRepository;
import org.diopsysteme.fileupload.services.strategy.interfaces.StorageStrategy;
import org.diopsysteme.fileupload.services.strategy.interfaces.StorageIFactory;
import org.diopsysteme.fileupload.services.mappers.FileMapper;
import org.diopsysteme.fileupload.model.dtos.requests.FileRequestDto;
import org.diopsysteme.fileupload.model.dtos.responses.FileDownloadResponseDto;
import org.diopsysteme.fileupload.model.dtos.responses.FileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import prog.dependancy.Services.Interfaces.AbstractService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static org.diopsysteme.fileupload.config.Constants.FILE_NOT_FOUND;
import static org.diopsysteme.fileupload.config.Constants.FILE_STORAGE_ERROR;


@Service
public class FileService extends AbstractService<File, FileRequestDto, FileResponseDto> {
@Autowired
FileRepository fileRepository;
@Autowired
        FileMapper fileMapper;

    StorageIFactory storageFactory;
    public FileService(FileRepository repository, FileMapper mapper, @Qualifier("storageFactory") StorageIFactory storageFactory) {
        this.repository = repository;
        this.mapper = mapper;
        this.storageFactory = storageFactory;
    }
    @CacheEvict(value = "files",allEntries = true)
    @Override
    public FileResponseDto save(FileRequestDto fileRequestDto)  {
        System.out.println("here");
        File file = new File();

        String originalFileName = fileRequestDto.getFile().getOriginalFilename();
        String mimeType = fileRequestDto.getFile().getContentType();
        //TODO tell, do not ask
        file.setFileName(fileRequestDto.getFileName());
        file.setFileType(mimeType);
        file.setStorageType(fileRequestDto.getType());
        file.setFileExtension(getExtension(originalFileName));
        StorageStrategy storageStrategy = storageFactory.getWhichStorageType(fileRequestDto.getType());
        System.out.println("here2"+file.toString());


        try {
            storageStrategy.store(file, fileRequestDto);
        } catch (IOException e) {
            throw new RuntimeException(FILE_STORAGE_ERROR, e);
        }
        file.setDate(LocalDate.now());
        File fileS = repository.save(file);
        System.out.println("here333");

        return mapper.toDto(fileS);
    }
    @Cacheable(value = "files",key = "#id")
        public FileDownloadResponseDto getFileForDownload(Long id) throws IOException {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException(FILE_NOT_FOUND));

            StorageStrategy strategy = storageFactory.getWhichStorageType(file.getStorageType());
            byte[] content = strategy.get(file);

            return fileMapper.toDownloadDtoWithContent(file, content);
    }

    private String getExtension(String fileName) {
        return fileName.contains(".")
                ? fileName.substring(fileName.lastIndexOf(".") + 1)
                : "unknown";
    }


@Cacheable(value = "files")
    public Page<FileResponseDto> searchFiles(String searchQuery, int page, int size) {
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
