package fr.aboussin.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class Config {
	@Bean
	public JobDetailFactoryBean getJBFB() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();

		factory.setJobClass(SimpleJob.class);

		return factory;
	}

	@Bean
	public CronTriggerFactoryBean getTriggerFactory() {
		CronTriggerFactoryBean factory = new CronTriggerFactoryBean();

		factory.setJobDetail(getJBFB().getObject());
		factory.setCronExpression("0 0/1 * 1/1 * ? *");

		return factory;
	}

	@Bean
	public SchedulerFactoryBean getSchedulerBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(getTriggerFactory().getObject());
		return scheduler;
	}
}
