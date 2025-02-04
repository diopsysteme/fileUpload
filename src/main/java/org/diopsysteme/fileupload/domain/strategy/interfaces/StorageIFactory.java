package org.diopsysteme.fileupload.domain.strategy.interfaces;

import org.diopsysteme.fileupload.domain.data.enums.StorageType;

public interface StorageIFactory {
    public StorageStrategy getWhichStorageType(StorageType storageType);
}
