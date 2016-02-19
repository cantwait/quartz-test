package com.sc;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.sc.services.HelloService;

@Component
@ConditionalOnProperty(name = "quartz.enabled")
public class GenericJob implements Job{

	@Autowired
	HelloService helloService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDetail jobDetail = context.getJobDetail();
		String name = jobDetail.getKey().getName();
		if(name.equalsIgnoreCase("task1")){
			helloService.task1();
		}else if(name.equalsIgnoreCase("task2")){
			helloService.task2();
		}else if(name.equalsIgnoreCase("task3")){
			helloService.task3();
		}else if(name.equalsIgnoreCase("task4")){
			helloService.task4();
		}else{
			System.out.println("That Task does not exists!");
		}
	}
	
	

}
