package com.harts.apache_camel_activeMQ.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {
//    private static final long serialVersionUID = 1L;

    private String userName;
    private int userId;
    private List<String> mobile;
}
