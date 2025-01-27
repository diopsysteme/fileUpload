package org.diopsysteme.fileupload.strategy.Which;

import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.strategy.Interfaces.StorageWhichInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("storageWhich3")
public class StorageWhich3 implements StorageWhichInterface {

    private final Map<StorageType, StorageStrategy> strategyMap;
private final  List<StorageStrategy> storage2;
    public StorageWhich3(Map<StorageType, StorageStrategy> strategyMap, List<StorageStrategy> storage2) {
        this.strategyMap = strategyMap;
        this.storage2 = storage2;
    }
@Override
    public StorageStrategy getWhichStorageType(StorageType storageType) {
        return Optional.ofNullable(strategyMap.get(storageType))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported storage type: " + storageType));
    }
}
