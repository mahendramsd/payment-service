package com.daofab.service.dto.response;

import com.daofab.service.domain.Parent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Mahendra on 5/17/2023
 */
@Data
@EqualsAndHashCode
@ToString
public class PaymentDetailsResponse implements Serializable {

    private Integer id;
    private String sender;
    private String receiver;
    private Double totalAmount;
    private Double paidAmount;

    public PaymentDetailsResponse(Integer id, String sender, String receiver, Double totalAmount, Double paidAmount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }
}
