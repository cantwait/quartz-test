package com.sc.services;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.sc.GenericJob;
import com.sc.data.JobRequest;

@Service
@ConditionalOnProperty(name = "quartz.enabled")
public class HelloServiceImpl implements HelloService{
	
	private static final String GROUP = "ACCTGROUP";
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;

	@Override
	public void task1() {
		System.out.println("Hello Task1");		
	}

	@Override
	public void task2() {
		System.out.println("Hello Task2");		
	}

	@Override
	public void task3() {
		System.out.println("Hello Task3");		
	}

	@Override
	public void task4() {
		System.out.println("Hello Task4");		
	}

	@Override
	public void addJob(JobRequest jobRequest) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = new JobKey(jobRequest.getName(), GROUP);
		JobDetail jobDetail = JobBuilder
				.newJob(GenericJob.class)
				.withIdentity(jobKey)
				.withDescription(jobRequest.getDescription())
				.build();
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(new TriggerKey(jobRequest.getName(), GROUP))
				.withSchedule(CronScheduleBuilder.cronSchedule(jobRequest.getCronExp()))
				.build();
		scheduler.scheduleJob(jobDetail, trigger);
	}

	@Override
	public void updateJob(String oldJobName, JobRequest jobRequest) throws SchedulerException {		
		//Stop the job and trigger first
		jobRequest.setCommand("pause");
		pauseOrResumeJob(jobRequest);
		//Delete 
		deleteJob(jobRequest);
		//add the job again with the new definition
		addJob(jobRequest);
		
	}

	@Override
	public void deleteJob(JobRequest jobRequest) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = new TriggerKey(jobRequest.getName());
		JobKey jobKey = new JobKey(jobRequest.getName());
		
		scheduler.unscheduleJob(triggerKey);
		scheduler.deleteJob(jobKey);
	}

	@Override
	public void pauseOrResumeJob(JobRequest jobRequest) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobDetail jobDetail = scheduler.getJobDetail(new JobKey(jobRequest.getName()));
		if(jobRequest.getCommand().equalsIgnoreCase("pause")){
			scheduler.pauseJob(jobDetail.getKey());
		}
		if(jobRequest.getCommand().equalsIgnoreCase("resume")){
			scheduler.resumeJob(jobDetail.getKey());			
		}
		
		
	}

}
