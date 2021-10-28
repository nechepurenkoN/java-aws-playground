package io.github.nechepurenkon.aws.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.github.nechepurenkon.aws")
public class AppConfig {

    @Bean
    AmazonS3 client() {
        return AmazonS3ClientBuilder.standard()
                                    .withRegion(Regions.DEFAULT_REGION)
                                    .build();
    }

}
