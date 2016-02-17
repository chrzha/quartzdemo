package com.quartz.demo.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import com.quartz.demo.listener.MyJobListener;
public class SimpleExample {  
  
    public void run() throws Exception {  
    	SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.clear();
        JobKey jobKey = new JobKey("JOB1", "Group1");
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(FirstJob.class)
                .usingJobData("name","chris")
                .withIdentity(jobKey)
                .build();
 
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .forJob("JOB1","Group1").startNow()
                .build();
 
      //Listener attached to jobKey
    	scheduler.getListenerManager().addJobListener(
    		new MyJobListener(), KeyMatcher.keyEquals(jobKey)
    	);
    	
        scheduler.scheduleJob(jobDetail,trigger);
 
        scheduler.start();
          
    }  
  
    public static void main(String[] args) throws Exception {  
        SimpleExample example = new SimpleExample();  
        example.run();  
    }  
} 
