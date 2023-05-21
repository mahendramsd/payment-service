package com.daofab.service.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Mahendra on 5/17/2023
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest implements Serializable {

    private String username;
    private String password;

}
