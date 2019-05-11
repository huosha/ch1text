package com.bee.sample.ch1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        poolExecutor.setCorePoolSize(5);
        // 配置最大线程数
        poolExecutor.setMaxPoolSize(10);
        // 配置队列大小
        poolExecutor.setQueueCapacity(200);

        // 配置线程池中线程的名称前缀
        poolExecutor.setThreadNamePrefix("spring-");
        // 配置拒绝策略
        //poolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        // poolExecutor.initialize();

        //线程池维护线程所允许的空闲时间
        poolExecutor.setKeepAliveSeconds(30000);
        poolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return poolExecutor;


    }


}
