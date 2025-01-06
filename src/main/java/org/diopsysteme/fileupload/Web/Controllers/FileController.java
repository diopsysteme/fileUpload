package org.diopsysteme.fileupload.Web.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.diopsysteme.fileupload.Data.Entities.File;
import org.diopsysteme.fileupload.Services.Impl.FileService;
import org.diopsysteme.fileupload.Web.Dtos.Requests.FileReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.FileResDto;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import prog.dependancy.Web.Controller.AbstractController;
import prog.dependancy.Services.Interfaces.AbstractService;

import java.io.FileNotFoundException;

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
    public ResponseEntity<FileResDto> save(@ModelAttribute FileReqDto request) {
        FileResDto response = service.save(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws FileNotFoundException {
        return fileService.downloadFile(id);
    }
    @GetMapping("/files")
    public Page<FileResDto> searchFiles(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return fileService.searchFiles(query, page, size);
    }

}
