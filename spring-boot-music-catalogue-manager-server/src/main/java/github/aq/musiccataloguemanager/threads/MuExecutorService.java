package github.aq.musiccataloguemanager.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


public class MuExecutorService {

	@Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }
	
	List<ExecutorService> executors = new ArrayList<>();
	
	public ExecutorService newFixedThreadPool(int threadCount, ThreadFactory threadFactory) {
		ExecutorService executor = Executors.newFixedThreadPool(threadCount, threadFactory);
		executors.add(executor);
		return executor;
	}
	
	public ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(corePoolSize, threadFactory);
		executors.add(executor);
		return executor;
	}

	public void shutdown() {
		for (ExecutorService executor : executors)
			executor.shutdown();
	}
}
