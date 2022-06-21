package tp_loader.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
//@Service
public class JobScheduler {
    @Scheduled(fixedRate = 1000)
    public void job() {
        log.info("Job is running");
    }
}
