package io.github.nechepurenkon.dynamodb.repository;

import io.github.nechepurenkon.dynamodb.entity.Slave;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SlaveRepository extends CrudRepository<Slave, String> {
}
