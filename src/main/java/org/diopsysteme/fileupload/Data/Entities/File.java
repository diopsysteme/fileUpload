package org.diopsysteme.fileupload.Data.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import prog.dependancy.Datas.Entity.EntityAbstract;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@ToString
public class File extends EntityAbstract {
    private String fileName;

    private String fileType;

    private String fileExtension;
private LocalDate date;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fileData;

}
