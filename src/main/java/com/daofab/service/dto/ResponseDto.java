package com.daofab.service.dto;

import com.daofab.service.util.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mahendra on 5/17/2023
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ResponseDto {

    private ResponseEnum status;
    private Object data;

    /**
     * success Message
     * @param data
     * @return
     */
    public static ResponseDto success(Object data) {
        return new ResponseDto(ResponseEnum.Ok,data);
    }

    /**
     * Fail Message
     * @param data
     * @return
     */
    public static ResponseDto failure(Object data) {
        return new ResponseDto(ResponseEnum.FAILURE,data);
    }
}
