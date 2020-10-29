package cn.i4.data.cloud.activiti.design.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * 设计器的配置
 * 特注：activiti由于自动生成的表名全是大写，无法在配置中实现转小写，需要手动配置mysql服务
 * 用root登录，修改etc/my.cnf，在[mysqld]的节点下面添加一行lower_case_table_names=1  #表名全部为小写，避免出现大小写敏感，重启mysql服务，service mysqld restart 即可
 * lower_case_table_names = 1  表名存储在磁盘是小写的，但是比较的时候是不区分大小写
 * lower_case_table_names=0  表名存储为给定的大小和比较是区分大小写的
 * lower_case_table_names=2, 表名存储为给定的大小写但是比较的时候是小写的
 * unix下lower_case_table_names默认值为 0 .Windows下默认值是 1 .Mac OS X下默认值是 2
 * @author wangjc
 * @title: ActivitiConfig
 * @projectName i4-data-cloud-start-activiti-design
 * @description: TODO
 * @date 2020/7/3110:57
 */
@Configuration
public class ActivitiConfig  implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 文字样式
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");

        springProcessEngineConfiguration.setDbIdentityUsed(false);//是否使用用户自带组织结构表
        springProcessEngineConfiguration.setDatabaseSchemaUpdate("true");//架构更新：是否自动创建表
        springProcessEngineConfiguration.setDatabaseType("mysql");//数据库类型
    }
}
