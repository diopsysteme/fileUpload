package org.diopsysteme.fileupload.ENV;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "storage")
@Data  // Lombok pour les getters/setters
public class StorageProperties {
    private List<String> strategies;
}