package job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobScheduler {
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void job() {
        log.info("Job is running");
    }
}
