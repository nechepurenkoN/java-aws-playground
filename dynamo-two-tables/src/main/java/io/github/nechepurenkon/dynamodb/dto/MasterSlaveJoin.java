package io.github.nechepurenkon.dynamodb.dto;

import io.github.nechepurenkon.dynamodb.entity.Master;
import io.github.nechepurenkon.dynamodb.entity.Slave;
import lombok.Value;

@Value
public class MasterSlaveJoin {
    String masterName;
    String slaveName;

    public static MasterSlaveJoin from(Master master, Slave slave) {
        return new MasterSlaveJoin(
            master != null ? master.getName() : "null",
            slave != null ? slave.getName() : "null"
        );
    }
}
