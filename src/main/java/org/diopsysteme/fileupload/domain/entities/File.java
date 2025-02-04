package org.diopsysteme.fileupload.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.diopsysteme.fileupload.domain.data.enums.StorageType;
import prog.dependancy.Datas.Entity.EntityAbstract;

import java.time.LocalDate;

@Data
@Entity
@ToString
public class File extends EntityAbstract {
    private String fileName;
    private String fileNameLocal;
    private String fileType;
    private StorageType storageType;
    private String fileExtension;
    private LocalDate date;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fileData;

}
