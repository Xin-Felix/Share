package work.huangxin.share.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
/**
 * 执行定动态时间
 */
public class TaskUtil {

    /**
     * 默认单线程，可能存在堵塞，多给几个线程
     *
     * @return
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    @Scheduled(cron = "0 0 23 * * ?")
//    @Scheduled(fixedRate = 1000)
    private void reportCurrentTime() {

    }
}
