package org.diopsysteme.fileupload.Data.Repositories;

import org.diopsysteme.fileupload.Data.Entities.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import prog.dependancy.Datas.Repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends SoftDeleteRepository<File, Long> {
    Page<File> findByFileName(String fileName, Pageable pageable);
}
