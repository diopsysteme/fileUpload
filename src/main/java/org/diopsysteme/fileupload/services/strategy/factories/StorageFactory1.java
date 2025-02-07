package org.diopsysteme.fileupload.services.strategy.factories;

import org.diopsysteme.fileupload.domain.data.enums.StorageType;
import org.diopsysteme.fileupload.services.strategy.impl.StoreDBStrategy;
import org.diopsysteme.fileupload.services.strategy.impl.StoreLocalStrategy;
import org.diopsysteme.fileupload.services.strategy.interfaces.StorageStrategy;
import org.diopsysteme.fileupload.services.strategy.interfaces.StorageIFactory;
import org.springframework.stereotype.Component;

@Component
public class StorageFactory1 implements StorageIFactory {
    private final StoreDBStrategy dbStrategy;
    private final StoreLocalStrategy localStrategy;

    public StorageFactory1(StoreDBStrategy dbStrategy, StoreLocalStrategy localStrategy) {
        this.dbStrategy = dbStrategy;
        this.localStrategy = localStrategy;

    }

    public StorageStrategy getWhichStorageType(StorageType storageType) {
        return switch (storageType) {
            case DB -> dbStrategy;
            case LOCAL -> localStrategy;
            default -> throw new IllegalArgumentException("Type de stockage non supporté: " + storageType);

        };
    }

    }