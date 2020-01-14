package org.traveloka.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CalendarGateway {

    CalendarService calendarService;

    public static void main(String[] args) throws Exception {
        CalendarService calendarService = new CalendarService();
        CalendarGateway.start(calendarService);
    }

    public static void start(CalendarService calendarService) throws Exception {

        BufferedReader bufferedReader;
        String input;
        while (true) {
            CalendarGateway.printAddUserConsole();
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            input = bufferedReader.readLine().trim();
            if (input.equalsIgnoreCase("1")) {
                System.out.println("Enter user name::");
                String name = bufferedReader.readLine().trim();
                System.out.println("Enter user email::");
                String email = bufferedReader.readLine().trim();
                calendarService.addEmployee(name, email);
            } else if (input.equalsIgnoreCase("2")) {
                System.out.println("Enter Meeting room name::");
                String name = bufferedReader.readLine().trim();
                System.out.println("Enter Capacity::");
                int capacity = Integer.valueOf(bufferedReader.readLine().trim());
                calendarService.addMeetingRoom(name, capacity);
            } else if (input.equalsIgnoreCase("3")) {
                List<String> emailIDs = new ArrayList<String>();
                System.out.println("Enter MeetingName::");
                String meetingName = bufferedReader.readLine().trim();
                System.out.println("Enter comma separated emailIds::");
                String emailIds = bufferedReader.readLine().trim();
                System.out.println("Enter Start time::");
                int startTime = Integer.valueOf(bufferedReader.readLine().trim());
                System.out.println("Enter EndTime time::");
                int endTime = Integer.valueOf(bufferedReader.readLine().trim());
                System.out.println("Enter Meeting room::");
                String room = bufferedReader.readLine().trim();
                calendarService.addMeeting(meetingName, emailIds.split(","), java.util.Optional.ofNullable(room), startTime, endTime);
            } else if (input.equalsIgnoreCase("4")) {
                System.out.println("Enter user email::");
                String email = bufferedReader.readLine().trim();
                calendarService.getEmployeeMeeting(email);
            } else if (input.equalsIgnoreCase("5")) {
                calendarService.printAllEmployees();
            } else if (input.equalsIgnoreCase("6")) {
                calendarService.printAllMeetingRooms();
            } else if (input.equalsIgnoreCase("7")) {
                break;
            } else {
                System.out.println("Invalid input entered. Please enter the correct input");
            }
        }
    }

    public static void printAddUserConsole() {
        System.out.println("1 - Add employee");
        System.out.println("2 - Add Meeting Room");
        System.out.println("3 - Add Meeting Invite");
        System.out.println("4 - List Meetings");
        System.out.println("5 - Print all Employees");
        System.out.println("6 - Print all Meeting rooms");
        System.out.println("7 - Exit");
        System.out.print("Enter input:::");

    }


}
