import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SampleSourceCode {

    static class Event {
        String name;
        int duration; // in minutes

        Event(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }
    }

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Event> events = new ArrayList<>();

        // Reading 20 lines of input
        for (int i = 0; i < 20; i++) {
            String input = scanner.nextLine();
            events.add(parseEvent(input));
        }

        // Organize events by duration
        List<Integer> durations = new ArrayList<>();
        Map<Integer, List<String>> eventMap = new HashMap<>();
        for (Event event : events) {
            durations.add(event.duration);
            eventMap.computeIfAbsent(event.duration, k -> new ArrayList<>()).add(event.name);
        }

        // Target time for scheduling (8 hours = 480 minutes excluding lunch break)
        int targetTime = 480 - 60;

        // Find a valid schedule
        List<Integer> timeSlots = new ArrayList<>();
        if (findSchedule(durations, targetTime, timeSlots)) {
            List<String> schedule = createSchedule(timeSlots, eventMap);
            printSchedule(schedule);
        } else {
            System.out.println("No valid schedule found.");
        }
    }

    private static Event parseEvent(String input) {
        String name = input.replaceAll("\\d+ mins", "").replaceAll("lightning", "").trim();
        int duration;
        if (input.contains("lightning")) {
            duration = 5;
        } else {
            String[] parts = input.split(" ");
            duration = Integer.parseInt(parts[parts.length - 2]);
        }
        return new Event(name, duration);
    }

    private static boolean findSchedule(List<Integer> durations, int targetTime, List<Integer> timeSlots) {
        if (targetTime == 0) {
            return true;
        }
        if (targetTime < 0) {
            return false;
        }
        for (int i = 0; i < durations.size(); i++) {
            int duration = durations.get(i);
            timeSlots.add(duration);
            durations.remove(i);
            if (findSchedule(durations, targetTime - duration, timeSlots)) {
                return true;
            }
            durations.add(i, duration);
            timeSlots.remove(timeSlots.size() - 1);
        }
        return false;
    }

    private static List<String> createSchedule(List<Integer> timeSlots, Map<Integer, List<String>> eventMap) {
        List<String> schedule = new ArrayList<>();
        for (int duration : timeSlots) {
            String event = eventMap.get(duration).remove(0);
            schedule.add(event + " " + duration + " mins");
        }
        return schedule;
    }

    private static void printSchedule(List<String> schedule) {
        LocalTime currentTime = LocalTime.of(9, 0);
        LocalTime lunchStartTime = LocalTime.of(12, 0);
        LocalTime lunchEndTime = LocalTime.of(13, 0);
        LocalTime networkingStartTime = LocalTime.of(16, 0);

        System.out.println("Schedule:");
        for (String event : schedule) {
            if (currentTime.equals(lunchStartTime)) {
                System.out.println("12:00 PM LUNCH");
                currentTime = lunchEndTime;
            }
            System.out.println(currentTime.format(TIME_FORMATTER) + " " + event);
            int duration = Integer.parseInt(event.split(" ")[event.split(" ").length - 2]);
            currentTime = currentTime.plusMinutes(duration);
            if (currentTime.isAfter(networkingStartTime)) {
                break;
            }
        }
        if (currentTime.isBefore(networkingStartTime)) {
            currentTime = networkingStartTime;
        }
        System.out.println(currentTime.format(TIME_FORMATTER) + " Networking Hands-on 60 mins");
    }
}
