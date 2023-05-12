package com.musala.drones.scheduler;

import com.musala.drones.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.musala.drones.constant.ScheduleConstant.*;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class CheckDroneBatteryLevelSchedule {

    private final ScheduleService scheduleService;

    @Scheduled(cron = CHECK_EXPRESSION)
    public void initSchedule() {
        log.info("::: CHECK BATTERY LEVEL SCHEDULER STARTED {} ::: ", new Date());
        scheduleService.updateSchedule();
        log.info("::: CHECK BATTERY LEVEL SCHEDULER COMPETED ::: ", new Date());
    }
}
