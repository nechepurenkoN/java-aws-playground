package io.github.nechepurenkon.dynamodb.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private ProvisionedThroughput provisionedThroughput;

    @Override
    public void createTables(Class<?>... entityList) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        for (Class<?> entityClass : entityList) {
            CreateTableRequest tableRequest = mapper
                .generateCreateTableRequest(entityClass)
                .withProvisionedThroughput(provisionedThroughput);
            amazonDynamoDB.createTable(tableRequest);
        }
    }

    @Override
    public void deleteTables(Class<?>... entityList) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        for (Class<?> entityClass : entityList) {
            DeleteTableRequest tableRequest = mapper
                .generateDeleteTableRequest(entityClass);
            amazonDynamoDB.deleteTable(tableRequest);
        }
    }
}
