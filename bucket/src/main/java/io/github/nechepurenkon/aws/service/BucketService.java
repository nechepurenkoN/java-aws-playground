package io.github.nechepurenkon.aws.service;

import com.amazonaws.services.s3.model.Bucket;
import java.util.List;

public interface BucketService {
    Bucket getBucket(String id);

    List<Bucket> getAllBuckets();

    void createBucket(String id);

    void deleteBucket(Bucket bucket);

    void deleteAllBuckets();
}
