package com.neno;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @author root
 * @date
 */
@SpringBootApplication
public class ReadWriteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadWriteApplication.class, args);
    }

    @Bean
    public DataSource dataSource() throws FileNotFoundException, SQLException, IOException {
        return MasterSlaveDataSourceFactory.createDataSource(ResourceUtils.getFile("classpath:sharding-jdbc.yml"));
    }
}
