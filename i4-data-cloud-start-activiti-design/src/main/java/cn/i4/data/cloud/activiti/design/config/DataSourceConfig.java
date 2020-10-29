package cn.i4.data.cloud.activiti.design.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author wangjc
 * @title: DataSourceConfig
 * @projectName i4-data-cloud-start-activiti-design
 * @description: TODO
 * @date 2020/7/3111:05
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSourceMaster(){
        return  new DruidDataSource();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSourceMaster) {
        return new DataSourceTransactionManager(dataSourceMaster);
    }

}
