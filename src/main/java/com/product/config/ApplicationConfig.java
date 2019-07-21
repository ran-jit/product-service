package com.product.config;

import com.product.data.Dataset;
import com.product.data.TrieNode;
import com.product.data.badwords.DatasetLoader;
import com.product.data.local.LocalDataset;
import com.product.data.redis.RedisDataset;
import com.product.exception.ServiceExceptionMapper;
import com.product.resource.ProductResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** author: Ranjith Manickam @ 21 July' 2019 */
@Configuration
public class ApplicationConfig {

    private final String badWordsFile;
    private final Boolean isLocalStorageMode;

    public ApplicationConfig(@Value("${data.bad-words-file:data/bad-words.txt}") String badWordsFile,
                             @Value("${data.is-local-storage-mode:true}") Boolean isLocalStorageMode) {
        this.badWordsFile = badWordsFile;
        this.isLocalStorageMode = isLocalStorageMode;
    }

    @Bean
    public ResourceConfig resourceConfig() {
        return new ResourceConfig().register(ProductResource.class)
                .register(ServiceExceptionMapper.class);
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Dataset getDataset() throws IOException, URISyntaxException {
        if (Boolean.TRUE.equals(this.isLocalStorageMode)) {
            TrieNode node = new TrieNode();

            Path filePath = Paths.get(this.badWordsFile).toFile().exists() ? Paths.get(this.badWordsFile) :
                    Paths.get(ClassLoader.getSystemResource(this.badWordsFile).toURI());
            DatasetLoader.build(filePath, node);
            return new LocalDataset(node);
        }

        return new RedisDataset();
    }
}
