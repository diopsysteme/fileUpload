package org.diopsysteme.fileupload.Strategy.Interfaces;

import org.diopsysteme.fileupload.Data.Enums.StorageType;

public interface StorageWhichInterface {
    public StorageStrategy getWhichStorageType(StorageType storageType);
}
