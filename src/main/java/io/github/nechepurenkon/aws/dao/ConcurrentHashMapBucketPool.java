package io.github.nechepurenkon.aws.dao;

import com.amazonaws.services.s3.model.Bucket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class ConcurrentHashMapBucketPool implements BucketPool {

    protected final Map<String, Bucket> map = new ConcurrentHashMap<>();

    @Override
    public Bucket getBucket(String id) {
        return map.get(id);
    }

    @Override
    public List<Bucket> getAllBuckets() {
        return new CopyOnWriteArrayList<>(map.values());
    }

    @Override
    public void addBucket(String id, Bucket bucket) {
        map.put(id, bucket);
    }

    @Override
    public Bucket deleteBucket(String id) {
        return map.remove(id);
    }

    @Override
    public void deleteAllBuckets() {
        map.clear();
    }
}
