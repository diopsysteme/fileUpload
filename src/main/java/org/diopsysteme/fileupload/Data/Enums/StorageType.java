package org.diopsysteme.fileupload.Data.Enums;

import java.util.Arrays;

public enum StorageType {
    DB,LOCAL;
    public static StorageType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Le type de stockage ne peut pas être null");
        }

        String normalizedValue = value.trim().toUpperCase();
        try {
            return StorageType.valueOf(normalizedValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type de stockage invalide: " + value +
                    ". Les valeurs acceptées sont: " + Arrays.toString(StorageType.values()));
        }
    }
}
