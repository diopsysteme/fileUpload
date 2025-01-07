package org.diopsysteme.fileupload.Strategy.Which;

import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.Strategy.Impl.StoreDBStrategy;
import org.diopsysteme.fileupload.Strategy.Impl.StoreLocalStrategy;
import org.diopsysteme.fileupload.Strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.Strategy.Interfaces.StorageWhichInterface;
import org.springframework.stereotype.Component;

import static org.hibernate.tool.schema.TargetType.DATABASE;
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