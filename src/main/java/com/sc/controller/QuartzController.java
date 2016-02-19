package com.sc.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sc.data.JobRequest;
import com.sc.data.JobResponse;
import com.sc.services.HelloService;

@RestController(value="/quartz")
@ConditionalOnProperty(name = "quartz.enabled")
public class QuartzController {
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping(path="/job", produces={MediaType.APPLICATION_JSON_VALUE}, method= RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JobResponse> createNewJob(@RequestBody(required=true) JobRequest jobRequest){
		try {
			helloService.addJob(jobRequest);			
		} catch (SchedulerException e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JobResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));			
		}		
		return ResponseEntity.ok().body(new JobResponse(HttpStatus.OK.value(), "Job: " + jobRequest.getName() + " was processed!"));
	}
	
	@RequestMapping(path="/job",produces={MediaType.APPLICATION_JSON_VALUE}, method= RequestMethod.DELETE, consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JobResponse> deleteJob(@RequestBody(required=true) JobRequest jobRequest){
		try {
			helloService.deleteJob(jobRequest);			
		} catch (SchedulerException e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JobResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));			
		}		
		return ResponseEntity.ok().body(new JobResponse(HttpStatus.OK.value(), "Job: " + jobRequest.getName() + " was deleted!"));
		
	}
	
	@RequestMapping(path="/job/status",produces={MediaType.APPLICATION_JSON_VALUE}, method= RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JobResponse> pauseOrResumeJob(@RequestBody(required=true) JobRequest jobRequest){
		try {
			helloService.pauseOrResumeJob(jobRequest);			
		} catch (SchedulerException e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JobResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));			
		}		
		return ResponseEntity.ok().body(new JobResponse(HttpStatus.OK.value(), "Job: " + jobRequest.getName() + " was deleted!"));
		
	}
	
	@RequestMapping(path="/job/{jobName}",produces={MediaType.APPLICATION_JSON_VALUE}, method= RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JobResponse> deleteJob(@PathVariable(value="jobName") String name, @RequestBody(required=true) JobRequest jobRequest){
		try {
			helloService.updateJob(name,jobRequest);			
		} catch (SchedulerException e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JobResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));			
		}		
		return ResponseEntity.ok().body(new JobResponse(HttpStatus.OK.value(), "Job: " + jobRequest.getName() + " was deleted!"));
		
	}

}
