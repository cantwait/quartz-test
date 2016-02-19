package com.sc.conf;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.sc.AutowiringSpringBeanJobFactory;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {
	
	@Bean
	public AutowiringSpringBeanJobFactory quartzJobFactory(ApplicationContext applicationContext){
		AutowiringSpringBeanJobFactory autoWiringSpringBeanJobFactory = new AutowiringSpringBeanJobFactory();
		autoWiringSpringBeanJobFactory.setApplicationContext(applicationContext);
		return autoWiringSpringBeanJobFactory;
	}
	
	@Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, ApplicationContext applicationContext) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file:
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setApplicationContext(applicationContext);
        factory.setJobFactory(quartzJobFactory(applicationContext));        
        factory.setQuartzProperties(quartzProperties());
//        factory.setTriggers(sampleJobTrigger);

        return factory;
    }
	
	@Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
	


}
