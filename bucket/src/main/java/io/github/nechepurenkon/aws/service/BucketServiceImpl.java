package io.github.nechepurenkon.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import io.github.nechepurenkon.aws.dao.BucketPool;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    private BucketPool dao;

    @Override
    public Bucket getBucket(String id) {
        return dao.getBucket(id);
    }

    @Override
    public List<Bucket> getAllBuckets() {
        return dao.getAllBuckets();
    }

    @Override
    public synchronized void createBucket(String id) { // rewrite
        final Bucket newBucket = s3client.createBucket(id);
        dao.addBucket(id, newBucket);
    }

    @Override
    public synchronized void deleteBucket(Bucket bucket) {
        s3client.deleteBucket(bucket.getName());
        dao.deleteBucket(bucket.getName());
    }

    @Override
    public synchronized void deleteAllBuckets() { // rewrite
        dao.getAllBuckets().forEach(bucket -> s3client.deleteBucket(bucket.getName()));
        dao.deleteAllBuckets();
    }

}
