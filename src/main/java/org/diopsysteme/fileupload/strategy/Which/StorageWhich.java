package org.diopsysteme.fileupload.strategy.Which;

import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.strategy.Impl.StoreDBStrategy;
import org.diopsysteme.fileupload.strategy.Impl.StoreLocalStrategy;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageWhichInterface;
import org.springframework.stereotype.Component;

@Component
public class StorageWhich implements StorageWhichInterface {
    private final StoreDBStrategy dbStrategy;
    private final StoreLocalStrategy localStrategy;

    public StorageWhich(StoreDBStrategy dbStrategy, StoreLocalStrategy localStrategy) {
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