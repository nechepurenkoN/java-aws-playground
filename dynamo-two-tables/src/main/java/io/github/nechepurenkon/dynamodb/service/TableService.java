package io.github.nechepurenkon.dynamodb.service;

public interface TableService {

    void createTables(Class<?>... entityList);

    void deleteTables(Class<?>... entityList);

}
