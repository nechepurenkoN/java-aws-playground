package io.github.nechepurenkon.dynamodb.providers;

import io.github.nechepurenkon.dynamodb.entity.Master;
import io.github.nechepurenkon.dynamodb.entity.Slave;
import io.github.nechepurenkon.dynamodb.repository.MasterRepository;
import io.github.nechepurenkon.dynamodb.repository.SlaveRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterSlaveTestData {

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private SlaveRepository slaveRepository;

    public void initSampleData() {
        Slave slave = new Slave();
        slave.setName("The second one's slave");
        slaveRepository.save(slave);

        Master master1 = new Master();
        master1.setName("The first one");
        Master master2 = new Master();
        master2.setName("The second one");
        master2.setSlaveId(slave.getId());
        masterRepository.saveAll(Arrays.asList(master1, master2));
    }
}
