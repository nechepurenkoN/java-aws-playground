package io.github.nechepurenkon.aws.dao;

import com.amazonaws.services.s3.model.Bucket;
import java.util.List;

public interface BucketPool {
    Bucket getBucket(String id);

    List<Bucket> getAllBuckets();

    void addBucket(String id, Bucket bucket);

    Bucket deleteBucket(String id);

    void deleteAllBuckets();
}
