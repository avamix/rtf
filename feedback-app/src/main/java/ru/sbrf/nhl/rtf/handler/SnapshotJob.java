package ru.sbrf.nhl.rtf.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sbrf.nhl.rtf.service.SnapshotService;

@Slf4j
@Component
public class SnapshotJob {
    private static final int FIXED_DELAY = 10_000;

    private final SnapshotService service;

    @Autowired
    public SnapshotJob(SnapshotService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = FIXED_DELAY, initialDelay = FIXED_DELAY)
    public void start() {
        log.debug("Start calculate snapshots");
        service.createSnapshots();
        log.info("Snapshots calculation complete");
    }
}
