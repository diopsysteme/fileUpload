package org.diopsysteme.fileupload.config;

import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CaffeineCacheManager cacheManager() {
        return new CaffeineCacheManager("files", "users");
    }}




//package org.diopsysteme.fileupload.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//@Configuration
//@EnableCaching
//public class CacheConfig {
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Configure ObjectMapper for polymorphic type handling
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.activateDefaultTyping(
//                objectMapper.getPolymorphicTypeValidator(),
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.PROPERTY
//        );
//
//        // Custom module for Page deserialization
//        SimpleModule pageModule = new SimpleModule();
//        pageModule.addDeserializer(Page.class, new PageDeserializer<>());
//        objectMapper.registerModule(pageModule);
//
//        // Jackson serializer setup
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        serializer.setObjectMapper(objectMapper);
//
//        // Redis cache configuration
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
//
//        return RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .build();
//    }
//
//    @Bean
//    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        return new StringRedisTemplate(redisConnectionFactory);
//    }
//
//    // Custom deserializer for Page
//    public static class PageDeserializer<T> extends JsonDeserializer<Page<T>> {
//        @SuppressWarnings("unchecked")
//        @Override
//        public Page<T> deserialize(JsonParser p, DeserializationContext ctxt)
//                throws IOException, JsonProcessingException {
//            JsonNode node = p.getCodec().readTree(p);
//
//            // Read the "content" array
//            List<T> content = new ArrayList<>();
//            if (node.has("content")) {
//                JsonNode contentNode = node.get("content");
//                for (JsonNode element : contentNode) {
//                    T item = (T) ctxt.readValue(element.traverse(p.getCodec()), Object.class);
//                    content.add(item);
//                }
//            }
//
//            // Read the pagination details
//            int pageNumber = node.has("number") ? node.get("number").asInt() : 0;
//            int pageSize = node.has("size") ? node.get("size").asInt() : content.size();
//            long totalElements = node.has("totalElements") ? node.get("totalElements").asLong() : content.size();
//
//            return new PageImpl<>(
//                    content,
//                    org.springframework.data.domain.PageRequest.of(pageNumber, pageSize),
//                    totalElements
//            );
//        }
//    }
//}
