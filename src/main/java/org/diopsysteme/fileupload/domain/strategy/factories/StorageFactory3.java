package org.diopsysteme.fileupload.domain.strategy.factories;

import org.diopsysteme.fileupload.domain.data.enums.StorageType;
import org.diopsysteme.fileupload.domain.strategy.interfaces.StorageStrategy;
import org.diopsysteme.fileupload.domain.strategy.interfaces.StorageIFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("storageFactory")
public class StorageFactory3 implements StorageIFactory {
//TODO  preferer des nom de classe parlant de methodes ou d'attributs

    private final Map<StorageType, StorageStrategy> strategyMap;
private final  List<StorageStrategy> storage2;
    public StorageFactory3(Map<StorageType, StorageStrategy> strategyMap, List<StorageStrategy> storage2) {
        this.strategyMap = strategyMap;
        this.storage2 = storage2;
    }
@Override
    public StorageStrategy getWhichStorageType(StorageType storageType) {
        return Optional.ofNullable(strategyMap.get(storageType))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported storage type: " + storageType));
    }
}
