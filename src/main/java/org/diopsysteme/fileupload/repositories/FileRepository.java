package org.diopsysteme.fileupload.repositories;

import org.diopsysteme.fileupload.domain.data.entities.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prog.dependancy.Datas.Repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends SoftDeleteRepository<File, Long> {
    Page<File> findByFileName(String fileName, Pageable pageable);
}
