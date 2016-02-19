package com.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.sc.conf.SchedulerConfig;

@SpringBootApplication
@Import({SchedulerConfig.class})
public class QuartzDynamicJobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzDynamicJobsApplication.class, args);
	}
}
