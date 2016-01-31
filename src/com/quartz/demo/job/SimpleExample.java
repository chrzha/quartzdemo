package com.quartz.demo.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
public class SimpleExample {  
  
    public void run() throws Exception {  
    	SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.clear();
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(FirstJob.class)
                .usingJobData("name","chris")
                .withIdentity("JOB1","Group1")
                .build();
 
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .forJob("JOB1","Group1").startNow()
                .build();
 
        scheduler.scheduleJob(jobDetail,trigger);
 
        scheduler.start();
          
    }  
  
    public static void main(String[] args) throws Exception {  
        SimpleExample example = new SimpleExample();  
        example.run();  
    }  
} 
