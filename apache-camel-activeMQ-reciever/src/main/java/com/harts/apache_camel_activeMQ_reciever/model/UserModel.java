package com.harts.apache_camel_activeMQ_reciever.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {
    private String userName;
    private int userId;
    private List<String> mobile;
}
