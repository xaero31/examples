package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Log
public class TimerDemo {

    public static void main(String[] args) throws InterruptedException {
        final var timer = new Timer();
        // timer.schedule(new TimerDemoTask(), 0L, 2000L);
        timer.scheduleAtFixedRate(new TimerDemoTask(), 0L, 2000L);
        log.info("end");
        TimeUnit.SECONDS.sleep(20L);
        timer.cancel();
    }

    @Log
    private static class TimerDemoTask extends TimerTask {

        @Override
        public void run() {
            Try.run(() -> {
                log.info("start execute");
                TimeUnit.SECONDS.sleep(10L);
                log.info("end execute");
            });
        }
    }
}
