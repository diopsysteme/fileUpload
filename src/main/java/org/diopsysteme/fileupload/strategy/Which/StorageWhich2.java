package org.diopsysteme.fileupload.strategy.Which;

import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageWhichInterface;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component("storageWhich2")
public class StorageWhich2 implements StorageWhichInterface {
    private final Map<StorageType, StorageStrategy> strategies = new ConcurrentHashMap<>();

    public StorageWhich2(Map<StorageType, StorageStrategy> strategyMap) {
        this.strategies.putAll(strategyMap);
    }

    @Override
    public StorageStrategy getWhichStorageType(StorageType storageType) {
        return Optional.ofNullable(strategies.get(storageType))
                .orElseThrow(() -> new IllegalArgumentException("Type de stockage non supporté: " + storageType));
    }
}