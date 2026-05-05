package com.contacts.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
