package com.sc.services;

import org.quartz.SchedulerException;

import com.sc.data.JobRequest;

public interface HelloService {
	
	public void task1();
	
	public void task2();
	
	public void task3();
	
	public void task4();
	
	public void addJob(JobRequest jobRequest) throws SchedulerException;
	
	public void updateJob(String oldJobName, JobRequest jobRequest) throws SchedulerException;
	
	public void deleteJob(JobRequest jobRequest) throws SchedulerException;
	
	public void pauseOrResumeJob(JobRequest jobRequest) throws SchedulerException;

}
