package org.traveloka.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @NonNull
    private String name;

    @NonNull
    private String emailId;

}
