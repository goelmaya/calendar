package org.traveloka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.traveloka.enums.MeetingRoomStatus;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoom {

    @NonNull
    private String roomId;

    private int capacity;

    private MeetingRoomStatus status;

}
