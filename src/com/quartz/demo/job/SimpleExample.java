package com.quartz.demo.job;

import java.util.Properties;

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
                .withIdentity("Test1","Group1")
                .build();//ͨ��JobBuilder����JobDetailImpl ʵ��,Ҳ����ֱ��new JobDetailImpl
 
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?").withMisfireHandlingInstructionFireAndProceed())
                .forJob("Test1","Group1").startNow()      //Trigger�ҵ���Ӧ������ΪTest1��ΪGroup1��Job,��������������ִ��scheduler.scheduleJob(jobDetail,trigger);����
                .build();//ͨ��TriggerBuilder����CronTriggerImplʵ��,Ҳ����ֱ��new CronTriggerImpl
 
        scheduler.scheduleJob(jobDetail,trigger);//����ÿ3�봥��һ��
 
        scheduler.start();
          
    }  
  
    public static void main(String[] args) throws Exception {  
        SimpleExample example = new SimpleExample();  
        example.run();  
    }  
} 
