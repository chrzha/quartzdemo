package com.quartz.demo.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class FirstJob implements Job {

	public FirstJob() {

	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		String name = dataMap.getString("name");
		System.out.println(context.getJobDetail().getKey() + " started at: \t" + new Date());
		System.out.println("hello," + name);
	}
}
