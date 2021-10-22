package io.github.nechepurenkon.aws;

import io.github.nechepurenkon.aws.config.AppConfig;
import io.github.nechepurenkon.aws.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {AppConfig.class})
public class BucketTests extends AbstractTestNGSpringContextTests {

    @Autowired
    protected BucketService bucketService;

    @Test
    public void test() {
        System.out.println(bucketService.getAllBuckets());
    }
}
