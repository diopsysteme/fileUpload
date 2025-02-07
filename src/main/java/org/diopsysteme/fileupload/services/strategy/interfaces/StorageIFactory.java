package org.diopsysteme.fileupload.services.strategy.interfaces;

import org.diopsysteme.fileupload.domain.data.enums.StorageType;

public interface StorageIFactory {
    public StorageStrategy getWhichStorageType(StorageType storageType);
}
