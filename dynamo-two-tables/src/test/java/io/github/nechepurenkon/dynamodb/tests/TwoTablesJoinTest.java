package io.github.nechepurenkon.dynamodb.tests;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import io.github.nechepurenkon.dynamodb.entity.Master;
import io.github.nechepurenkon.dynamodb.entity.Slave;
import io.github.nechepurenkon.dynamodb.providers.MasterSlaveTestData;
import io.github.nechepurenkon.dynamodb.repository.MasterRepository;
import io.github.nechepurenkon.dynamodb.repository.SlaveRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class TwoTablesJoinTest {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private SlaveRepository slaveRepository;

    @Autowired
    private MasterSlaveTestData dataProvider;

    @Before
    public void setupDynamoDB() {
        createTables(Master.class, Slave.class);
        dataProvider.initSampleData();
    }

    @After
    public void teardownDynamoDB() {
        deleteTables(Master.class, Slave.class);
    }

    @Test
    public void test() {
        masterRepository.findAll().forEach(System.out::println);
    }

    protected void createTables(Class<?>... entityList) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        for (Class<?> entityClass : entityList) {
            CreateTableRequest tableRequest = mapper
                .generateCreateTableRequest(entityClass).withProvisionedThroughput(
                    new ProvisionedThroughput(1L,1L)
                );
            amazonDynamoDB.createTable(tableRequest);
        }
    }

    protected void deleteTables(Class<?>... entityList) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        for (Class<?> entityClass : entityList) {
            DeleteTableRequest tableRequest = mapper
                .generateDeleteTableRequest(entityClass);
            amazonDynamoDB.deleteTable(tableRequest);
        }
    }
}
