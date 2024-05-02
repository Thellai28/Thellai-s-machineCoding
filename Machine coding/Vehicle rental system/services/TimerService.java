package services;

import controllers.Main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerService {
    private static final int DEFAULT_TIMER_DURATION_MINUTES = 1 ;
    private static Timer timer;
    private static TimerTask timerTaskThread;


    public static void startTimer(){
        timer = new Timer();

        timerTaskThread = new TimerTask() {
            @Override
            public void run() {
                System.out.println("‼️Timer expired, Shutting down the application ‼️");
                stopTimer();
                System.exit(0);
            }
        };
        long delay = TimeUnit.MINUTES.toMillis(DEFAULT_TIMER_DURATION_MINUTES);
        timer.schedule(timerTaskThread, delay);
    }

    public static String getFormattedRemainingTime(){
        if( timerTaskThread != null ){
            long remainingTimeMillis = timerTaskThread.scheduledExecutionTime() - System.currentTimeMillis();
            long hours = TimeUnit.MILLISECONDS.toHours(remainingTimeMillis);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTimeMillis) % 60;
            long seconds =  TimeUnit.MILLISECONDS.toSeconds(remainingTimeMillis) % 60;
            return String.format("%02d H : %02d M : %02d S", hours, minutes, seconds);
        }
        return "00:00:00";
    }

    public static void stopTimer(){
        if( timer != null ){
            timer.cancel();
        }
    }
}
