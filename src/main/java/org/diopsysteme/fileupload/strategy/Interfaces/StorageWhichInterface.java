package org.diopsysteme.fileupload.strategy.Interfaces;

import org.diopsysteme.fileupload.Data.Enums.StorageType;

public interface StorageWhichInterface {
    public StorageStrategy getWhichStorageType(StorageType storageType);
}
