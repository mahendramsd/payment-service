package com.daofab.service.dto.response;

import com.daofab.service.domain.Parent;
import com.daofab.service.dto.PaymentDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mahendra on 5/17/2023
 */
@Data
@EqualsAndHashCode
@ToString
public class PaymentResponse implements Serializable {

    private long totalItems;
    private List<PaymentDto> payments;


    public PaymentResponse(long totalItems, List<PaymentDto> data) {
        this.totalItems = totalItems;
        this.payments = data;
    }
}
