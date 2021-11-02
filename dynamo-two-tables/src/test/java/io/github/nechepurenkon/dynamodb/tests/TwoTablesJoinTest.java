package io.github.nechepurenkon.dynamodb.tests;

import io.github.nechepurenkon.dynamodb.dto.MasterSlaveJoin;
import io.github.nechepurenkon.dynamodb.entity.Master;
import io.github.nechepurenkon.dynamodb.entity.Slave;
import io.github.nechepurenkon.dynamodb.providers.MasterSlaveTestData;
import io.github.nechepurenkon.dynamodb.repository.MasterRepository;
import io.github.nechepurenkon.dynamodb.repository.SlaveRepository;
import io.github.nechepurenkon.dynamodb.service.TableService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class TwoTablesJoinTest {

    @Autowired
    private TableService tableService;

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private SlaveRepository slaveRepository;

    @Autowired
    private MasterSlaveTestData dataProvider;

    @Before
    public void setupDynamoDB() {
        tableService.createTables(Master.class, Slave.class);
        dataProvider.initSampleData();
    }

    @After
    public void teardownDynamoDB() {
        tableService.deleteTables(Master.class, Slave.class);
    }

    @Test
    @SuppressWarnings("all")
    public void joinTablesTest() {
        List<MasterSlaveJoin> joinList = StreamSupport.stream(masterRepository.findAll().spliterator(), false)
                                                      .map(master -> MasterSlaveJoin.from(
                                                          master,
                                                          master.getSlaveId() != null ? slaveRepository
                                                              .findById(master.getSlaveId()).get() : null)
                                                      )
                                                      .sorted(Comparator.comparing(MasterSlaveJoin::getMasterName))
                                                      .collect(Collectors.toList());

        log.info("Join list is {}", joinList.toString());

        assert joinList.get(0).getMasterName().equals("The first one");
        assert joinList.get(0).getSlaveName().equals("null");

        assert joinList.get(1).getMasterName().equals("The second one");
        assert joinList.get(1).getSlaveName().equals("The second one's slave");
    }
}
