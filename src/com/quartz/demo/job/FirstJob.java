package com.quartz.demo.job;

import java.util.Date;  
import org.quartz.Job;  
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
  
public class FirstJob implements Job {  
  
     
    public FirstJob() {  
          
    }  
     
    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
          
        System.out.println(" FirstJob: " + new Date());  
          
    }  
} 
