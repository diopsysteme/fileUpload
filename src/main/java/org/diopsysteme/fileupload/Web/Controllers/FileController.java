package org.diopsysteme.fileupload.Web.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.services.Impl.FileService;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileDownloadDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileResDto;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prog.dependancy.Web.Controller.AbstractController;
import prog.dependancy.Services.Interfaces.AbstractService;

import java.io.FileNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/file")
public class FileController extends AbstractController<File, FileReqDto, FileResDto> {

    FileService fileService;
    public FileController(AbstractService<File, FileReqDto, FileResDto> service,FileService fileService) {
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
    public ResponseEntity<FileResDto> save(@Valid @ModelAttribute FileReqDto request) {
        FileResDto response = service.save(request);
        response.add(linkTo(methodOn(FileController.class).downloadFile(response.getId())).withRel("download"));
        response.add(linkTo(methodOn(FileController.class).searchFiles(null,0,10)).withRel("getAllfiles or search using file name"));
        return ResponseEntity.ok(response);
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        try {
            FileDownloadDto downloadDto = fileService.getFileForDownload(id);
            return downloadDto.toResponseEntity();
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/files")
    public Page<FileResDto> searchFiles(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return fileService.searchFiles(query, page, size);
    }

}
