package org.diopsysteme.fileupload.web.restcontroller;

import com.google.j2objc.annotations.AutoreleasePool;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.diopsysteme.fileupload.domain.entities.File;
import org.diopsysteme.fileupload.services.Impl.FileService;
import org.diopsysteme.fileupload.model.dtos.requests.FileRequestDto;
import org.diopsysteme.fileupload.model.dtos.responses.FileDownloadResponseDto;
import org.diopsysteme.fileupload.model.dtos.responses.FileResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import prog.dependancy.Web.Controller.AbstractController;
import prog.dependancy.Services.Interfaces.AbstractService;

import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/file")
public class FileController extends AbstractController<File, FileRequestDto, FileResponseDto> {

    FileService fileService;
    public FileController(AbstractService<File, FileRequestDto, FileResponseDto> service, FileService fileService) {
        super(service);
        this.fileService=fileService;
    }
    @Override
    @Operation(

            summary = "Upload a file",
            description = "Upload a file with its metadata in a single JSON object"
    )
    @PostMapping(

            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FileResponseDto> save(@Valid @RequestBody FileRequestDto request) {
        System.out.println("here1 controller");
        FileResponseDto response = service.save(request);
        System.out.println("here1 controller");

        response.add(linkTo(methodOn(FileController.class).downloadFile(response.getId())).withRel("download"));
        response.add(linkTo(methodOn(FileController.class).searchFiles(null,0,10)).withRel("getAllfiles or search using file name"));
        return ResponseEntity.ok(response);
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        try {
            FileDownloadResponseDto downloadDto = fileService.getFileForDownload(id);
            return downloadDto.toResponseEntity();
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/files")
    public Page<FileResponseDto> searchFiles(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return fileService.searchFiles(query, page, size);
    }

}
