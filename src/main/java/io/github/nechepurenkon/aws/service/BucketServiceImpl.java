package io.github.nechepurenkon.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    private AmazonS3 s3client;

    @Override
    public Bucket getBucket(String id) {
        return null;
    }

    @Override
    public List<Bucket> getAllBuckets() {
        return s3client.listBuckets();
    }

    @Override
    public void createBucket(String id) {

    }

    @Override
    public void addFiles(Bucket bucket, List<String> paths) {

    }

    @Override
    public void deleteBucket(Bucket bucket) {

    }

    @Override
    public void clearBucket(Bucket bucket) {

    }

    @Override
    public void deleteAllBuckets() {

    }

}
