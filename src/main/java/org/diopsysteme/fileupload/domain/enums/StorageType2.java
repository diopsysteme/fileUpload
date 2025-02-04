package org.diopsysteme.fileupload.domain.data.enums;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum StorageType2 {

    // Les valeurs seront initialisées dynamiquement

    ;

    private static final Map<String, StorageType> VALUES = new HashMap<>();

    

    @SneakyThrows

    public static void initializeFromProperties(List<String> strategies) {

        // Accéder au constructeur privé de l'enum via reflection

        Constructor<?> constructor = StorageType.class

            .getDeclaredConstructor(String.class, int.class);

        constructor.setAccessible(true);

        

        // Accéder au champ $VALUES

        Field valuesField = StorageType.class.getDeclaredField("$VALUES");

        valuesField.setAccessible(true);

        

        // Créer les nouvelles instances d'enum

        StorageType[] values = new StorageType[strategies.size()];

        for (int i = 0; i < strategies.size(); i++) {

            String strategy = strategies.get(i);

            StorageType enumValue = (StorageType) constructor

                .newInstance(strategy, i);

            values[i] = enumValue;

            VALUES.put(strategy, enumValue);

        }

        

        // Mettre à jour le champ $VALUES

        valuesField.set(null, values);

    }


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