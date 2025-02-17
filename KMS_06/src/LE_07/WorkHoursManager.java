package LE_07;

import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class WorkHoursManager {
    public static void calculateWorkHours() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter start time (HH:mm): ");
            LocalTime startTime = LocalTime.parse(sc.next(), TimeUtils.TIME_FORMAT);

            System.out.print("Enter end time (HH:mm): ");
            LocalTime endTime = LocalTime.parse(sc.next(), TimeUtils.TIME_FORMAT);

            if (endTime.isBefore(startTime)) {
                throw new IllegalArgumentException("End time cannot be before start time");
            }

            Duration duration = Duration.between(startTime, endTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();

            System.out.println("Total Work Time: " + hours + ":" + minutes);

        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid time format! Please enter time in HH:mm format.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}