package com.quartz.demo.job;

import java.text.SimpleDateFormat;
import java.util.Date;  

import org.quartz.Job;  
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.quartz.JobKey;
import org.quartz.SchedulerException;
  
public class FirstJob implements Job {  
  
     
    public FirstJob() {  
          
    }  
     
    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            JobDataMap dataMap = context.getJobDetail().getJobDataMap();

            String name = dataMap.getString("name");
        	System.out.println(Thread.currentThread().getName()+" started at: \t" + sdf.format(new Date()));  
			Thread.currentThread().sleep(10000L);
			System.out.println("hello," + name);
			System.out.println(Thread.currentThread().getName() + " finished at: \t" + sdf.format(new Date()));  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
        System.out.println("---------------------------------------"); 
    }  
} 
