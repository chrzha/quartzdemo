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
 
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(FirstJob.class)
                .usingJobData("Test1","Quartz")
                .withIdentity("Test1","Group1")
                .build();//通过JobBuilder构建JobDetailImpl 实例,也可以直接new JobDetailImpl
 
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .forJob("Test1","Group1").startNow()      //Trigger找到对应的名称为Test1组为Group1的Job,如果不存在则会在执行scheduler.scheduleJob(jobDetail,trigger);报错
                .build();//通过TriggerBuilder构建CronTriggerImpl实例,也可以直接new CronTriggerImpl
 
        scheduler.scheduleJob(jobDetail,trigger);//任务每3秒触发一次
 
        scheduler.start();
          
    }  
  
    public static void main(String[] args) throws Exception {  
        SimpleExample example = new SimpleExample();  
        example.run();  
    }  
} 
