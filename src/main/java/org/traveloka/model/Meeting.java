package org.traveloka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @NonNull
    private String meetingName;

    @NonNull
    private Set<Employee> members;

    //Can be nullable
    private MeetingRoom meetingRoom;

    private int startTime;

    private int endTime;
}
