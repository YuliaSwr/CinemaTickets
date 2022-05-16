package com.cinematickets.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class ClientRequest {

    private String name;
    private String email;
    private String phoneNumber;
    private String movie;
    private String date;
    private String time;
    private String type;
}
