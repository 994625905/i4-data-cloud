package cn.i4.data.cloud.file.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author wangjc
 * @title: FdfsConfiguration
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1220:19
 */
@Configuration
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
@Import(FdfsClientConfig.class)
public class FdfsConfiguration {

}
