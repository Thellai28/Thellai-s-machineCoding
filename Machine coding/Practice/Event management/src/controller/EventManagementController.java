package controller;

import models.Event;
import service.UserInputOutputService;

import java.util.*;

public class EventManagementController {
    public static void manageEvents() {
        List<Event> evenList = getEventsList();
        int day = 1;

        while ( getTotalTimeInEventList(evenList) >= 360 ) {
            // 360 / 60 => 6 hours, we should have minimum 6 hours of events per day

            List<String> output = new ArrayList<>();
            List<Event> firstHalfEvents = scheduleEventsForFixedTime(180,
                    getDurationFromEventList(evenList), evenList);

            if (firstHalfEvents != null) {
                int time = convertEventsIntoOutput(output, firstHalfEvents, 9);
                output.add(String.format("%02d:%02d -> LUNCH BREAK for one hour", time / 60, time % 60));
            }

            List<Event> secondHalfEvents = scheduleEventForRange(180, 239,
                    getDurationFromEventList(evenList), evenList);

            if (secondHalfEvents != null) {
                int time = convertEventsIntoOutput(output, secondHalfEvents, 13);

                // time will go beyond 12, so mod it with 12
                output.add(String.format("%02d:%02d -> Networking session", (time / 60)%12, time % 60));
            }

            UserInputOutputService.printScheduledEvents(day, output);
            day++;
        }
    }

    private static int getTotalTimeInEventList( List<Event> eventList ){
        int time = 0;
        for( Event event : eventList ){
            time += event.getDuration();
        }
        return time;
    }


    private static List<Integer> getDurationFromEventList( List<Event> eventList ){
        List<Integer> durations = new ArrayList<>();

        for( Event event : eventList ){
            durations.add( event.getDuration() );
        }
        return durations;
    }

    private static List<Event> getEventsList() {
        List<Event> eventList = new ArrayList<>();

        String[] strings = {
                "Welcome event 30 mins", "C programming 45 mins", "Working with Java Beans 30 mins",
                "Ruby on Rails programming 60 mins", "Introduction to Groovy 60 mins", "Rails Debugging 45 mins",
                "Tips and tricks in C 30 mins", "Back end development in MySQL 50 mins", "Sit down and Take notes lightning",
                "Clojure Introduction 45 mins", "Team Management Concepts 30 mins", "Introduction to Java Frameworks lightning",
                "Working with Angular JS 45 mins", "Ruby on Rails programming web development concepts 60 mins",
                "Introduction to Kotlin Java 60 mins", "Debugging and Testing products 60 mins",
                "Documenting a software 40 mins", "Server side development 60 mins"
        };

        for (String eventDescription : strings) {
            int duration;

            if (eventDescription.contains("lightning")) {
                duration = 5;
            } else {
                String[] eventArray = eventDescription.split(" ");
                duration = Integer.parseInt(eventArray[eventArray.length - 2]); // time will be present in last second index
            }

            eventList.add(new Event(duration, eventDescription));
        }
        return eventList;
    }


    private static List<Event> scheduleEventsForFixedTime(int totalTime,
                                                          List<Integer> durations,
                                                          List<Event> eventList) {
        List<Integer> timeSlots = new ArrayList<>();

        if (canScheduleEvents(durations, totalTime, timeSlots)) {
            List<Event> scheduledEvents =  createScheduleList(timeSlots, eventList);
            eventList = removeScheduledEventsFromEventList(scheduledEvents, eventList);
            return scheduledEvents;
        }
        return null;
    }

    private static List<Event> scheduleEventForRange(int start, int end,
                                                     List<Integer> durations,
                                                     List<Event> eventList) {
        for (int i = start; i <= end; i++) {
            List<Integer> timeSlots = new ArrayList<>();

            if ( canScheduleEvents(durations, i, timeSlots) ) {
                List<Event> scheduledEvents =  createScheduleList(timeSlots, eventList);
                eventList = removeScheduledEventsFromEventList(scheduledEvents, eventList);
                return scheduledEvents;
            }
        }
        return null;
    }

    private static List<Event> createScheduleList(List<Integer> timeSlots,
                                                  List<Event> eventList) {
        Set<Event> set = new HashSet<>();

        for( int time : timeSlots ){
            for( Event event : eventList ){
                // we might end up adding same event again and again, so using the below condition :
                if( time == event.getDuration() && !set.contains(event) ){
                    set.add(event);
                    break;
                }
            }
        }
        return  new ArrayList<>(set);
    }

    private static List<Event> removeScheduledEventsFromEventList( List<Event> scheduledEvents,
                                                                   List<Event> eventList ){
        for( Event event : scheduledEvents ){
            eventList.remove(event);
        }
        return eventList;
    }

    private static boolean canScheduleEvents( List<Integer> durations,
                                              int timeRemaining,
                                              List<Integer> timeSlots) {
        if (timeRemaining == 0) {
            return true;
        }
        if (timeRemaining < 0) {
            return false;
        }

        // for loop will start always from 0, since we remove elements from list at any index :
        for (int i = 0; i < durations.size(); i++) {
            int currDuration = durations.get(i);
            timeSlots.add(currDuration);
            durations.remove(i);

            if (canScheduleEvents(durations, timeRemaining - currDuration, timeSlots)) {
                return true;
            }

            durations.add(i, currDuration);
            timeSlots.remove(timeSlots.size() - 1);
        }
        return false;
    }

    private static int convertEventsIntoOutput(List<String> outputString,
                                               List<Event> scheduledEvents, int baseTime) {
        int time = baseTime * 60;

        for (Event event : scheduledEvents) {
            int hours = (time / 60)%12;
            int minutes = time % 60;
            String out = String.format("%02d:%02d -> %s", hours, minutes, event.getEventDescription());
            outputString.add(out);

            time += event.getDuration();
        }
        return time;
    }
}
