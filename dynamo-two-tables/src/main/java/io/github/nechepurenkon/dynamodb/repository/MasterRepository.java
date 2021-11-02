package io.github.nechepurenkon.dynamodb.repository;

import io.github.nechepurenkon.dynamodb.entity.Master;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MasterRepository extends CrudRepository<Master, String> {
}
