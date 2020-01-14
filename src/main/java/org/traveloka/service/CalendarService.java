package org.traveloka.service;

import org.traveloka.enums.MeetingRoomStatus;
import org.traveloka.model.Employee;
import org.traveloka.model.Meeting;
import org.traveloka.model.MeetingRoom;

import java.util.*;

public class CalendarService {

    Map<String, Employee> employees;
    Map<String, MeetingRoom> meetingRooms;
    List<Meeting> meetings;

    public CalendarService() {
        employees = new HashMap<String, Employee>();
        meetingRooms = new HashMap<String, MeetingRoom>();
        meetings = new ArrayList<Meeting>();
    }

    public void addEmployee(String username, String email) throws Exception {
        if (employees.containsKey(email)) {
            System.out.println("Employee already exists");
        } else {
            employees.put(email, new Employee(username, email));
            System.out.println("Employee created successfully");
        }
    }

    public void addMeetingRoom(String meetingRoomName, int capacity) throws Exception {
        if (meetingRooms.containsKey(meetingRoomName)) {
            System.out.println("Meeting Room already exists");
        } else {
            if (capacity <= 0) {
                System.out.println("Please enter valid capacity");
            }
            meetingRooms.put(meetingRoomName, new MeetingRoom(meetingRoomName, capacity, MeetingRoomStatus.AVAILABLE));
            System.out.println("Meeting room created..");
        }
    }

    public void addMeeting(String meetingName, String[] emailIds, Optional<String> room, int start, int end) {

        if (emailIds.length <= 0) {
            System.out.println("The number of invitees have to be greater than 0");
        }
        else if(start<0 || start>= 23 || end <=0 || end >24 || start>=end){
            System.out.println("Invalid timings.");
        }
        else {
            Set<Employee> invitees = new HashSet<Employee>();
            for (Map.Entry<String, Employee> employeeEntry : employees.entrySet()) {
                for (String emailId : emailIds) {
                    if (employeeEntry.getKey().equals(emailId)) {
                        invitees.add(employeeEntry.getValue());
                    }
                }
            }
            if (invitees.size() == 0) {
                System.out.println("No valid invitees found");
            } else {
                if(room.isPresent()){
                    if(meetingRooms.containsKey(room.get())){
                        boolean conflict = false;
                        for(Meeting meeting: meetings){
                            if(meeting.getMeetingRoom().getRoomId().equals(room.get())){
                                if((start >= meeting.getStartTime() && start<=meeting.getEndTime()) || (end >= meeting.getStartTime() && end<=meeting.getEndTime())){
                                    conflict = true;
                                }
                            }
                        }
                        if(conflict){
                            System.out.println("There is a conflict");
                        }
                        else {
                            meetings.add(new Meeting(meetingName, invitees, meetingRooms.get(room.get()), start, end));
                            System.out.println("Meeting added..");
                        }
                    }
                    else {
                        System.out.println("Meeting room does not exist");
                    }
                }
                else{
                    meetings.add(new Meeting(meetingName, invitees, null, start, end));
                    System.out.println("Meeting added..");
                }
            }
        }
    }

    public void getEmployeeMeeting(String email) {
        if (!employees.containsKey(email)) {
            System.out.println("Invalid employee or employee does not exist");
        } else {
            List<String> employeeMeetings = new LinkedList<String>();
            for (Meeting meeting : meetings) {
                if (meeting.getMembers().contains(employees.get(email))) {
                    employeeMeetings.add(meeting.getMeetingName());
                }
            }
            System.out.println(employeeMeetings.toString());
        }
    }


    public void printAllEmployees() {
        if (employees.size() == 0) {
            System.out.println("No employees present");
        } else {
            for (Employee employee : employees.values()) {
                System.out.println("***********************************");
                System.out.println("Employee Name:" + employee.getName());
                System.out.println("Employee Email:" + employee.getEmailId());
            }
        }
    }

    public void printAllMeetingRooms() {
        if (meetingRooms.size() == 0) {
            System.out.println("No Meeting rooms present");
        } else {
            for (MeetingRoom meetingRoom : meetingRooms.values()) {
                System.out.println("***********************************");
                System.out.println("Meeting room Name:" + meetingRoom.getRoomId());
                System.out.println("Meeting room capacity:" + meetingRoom.getCapacity());
            }
        }
    }

}
