package com.daofab.service.dto.response;

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
public class LoginResponse implements Serializable {

    private Integer id;
    private String username;
    private String accessToken;

}
