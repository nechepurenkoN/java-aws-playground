package io.github.nechepurenkon.aws.tests;

import io.github.nechepurenkon.aws.config.AppConfig;
import io.github.nechepurenkon.aws.providers.BucketProvider;
import io.github.nechepurenkon.aws.service.BucketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {AppConfig.class})
public class BucketTests extends AbstractTestNGSpringContextTests {

    @Autowired
    protected BucketService bucketService;

    @Test(
        dataProviderClass = BucketProvider.class,
        dataProvider = "simpleBucketNames"
    )
    public void createBuckets(List<String> bucketNames) {
        bucketNames.stream()
                   .peek(bucketService::createBucket)
                   .forEach(bucketName -> {
                       final String createdBucketName = bucketService.getBucket(bucketName).getName();
                       logger.info(String.format("Assert that created bucket name %s equals to %s",
                           createdBucketName, bucketName));
                       assert bucketName.equals(createdBucketName);
                   });
    }

    @AfterSuite
    public void cleanTestBuckets() {
        bucketService.deleteAllBuckets();
    }
}
