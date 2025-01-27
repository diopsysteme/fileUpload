package org.diopsysteme.fileupload.services.Impl;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Data.Repositories.FileRepository;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageWhichInterface;
import org.diopsysteme.fileupload.Web.Dtos.Mappers.FileMapper;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileDownloadDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileResDto;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import prog.dependancy.Services.Interfaces.AbstractService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class FileService extends AbstractService<File, FileReqDto, FileResDto> {
@Autowired
FileRepository fileRepository;
@Autowired
        FileMapper fileMapper;

    StorageWhichInterface storageWhich;
    public FileService(FileRepository repository, FileMapper mapper, @Qualifier("storageWhich3") StorageWhichInterface storageWhich) {
        this.repository = repository;
        this.mapper = mapper;
        this.storageWhich = storageWhich;
    }
    @CacheEvict(value = "files",allEntries = true)
    @Override
    public FileResDto save(FileReqDto fileReqDto){
        File file = new File();

        String originalFileName = fileReqDto.getFile().getOriginalFilename();
        String mimeType = fileReqDto.getFile().getContentType();
        file.setFileName(fileReqDto.getFileName() != null && !fileReqDto.getFileName().isEmpty()
                ? fileReqDto.getFileName()
                : originalFileName);

        file.setFileType(mimeType);
        file.setStorageType(fileReqDto.getWhere());
        file.setFileExtension(getExtension(originalFileName));

        StorageStrategy storageStrategy = storageWhich.getWhichStorageType(fileReqDto.getWhere());
        try {
            storageStrategy.store(file,fileReqDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        file.setDate(LocalDate.now());

        File fileS = repository.save(file);
        return mapper.toDto(fileS);
    }
    @Cacheable(value = "files",key = "#id")
        public FileDownloadDto getFileForDownload(Long id) throws FileNotFoundException {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Fichier non trouvé avec l'ID: " + id));

        try {
            StorageStrategy strategy = storageWhich.getWhichStorageType(file.getStorageType());
            byte[] content = strategy.get(file);

            return fileMapper.toDownloadDtoWithContent(file, content);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
    }

    private String getExtension(String fileName) {
        return fileName.contains(".")
                ? fileName.substring(fileName.lastIndexOf(".") + 1)
                : "unknown";
    }


@Cacheable(value = "files")
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
