package com.daofab.service.dto;

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
public class PaymentDto implements Serializable {

    private Integer id;
    private String sender;
    private String receiver;
    private Double totalAmount;
    private Double totalPaidAmount = 0.0;

    public PaymentDto(Parent parent, Double totalPaidAmount) {
        this.id = parent.getId();
        this.sender = parent.getSender();
        this.receiver = parent.getReceiver();
        this.totalAmount = parent.getTotalAmount();
        if(totalPaidAmount != null) {
            this.totalPaidAmount = totalPaidAmount;
        }

    }
}
