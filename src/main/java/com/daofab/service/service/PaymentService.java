package com.daofab.service.service;

import com.daofab.service.dto.response.PaymentDetailsResponse;
import com.daofab.service.dto.response.PaymentResponse;

import java.util.List;

/**
 * @author Mahendra on 5/18/2023
 */
public interface PaymentService {
    PaymentResponse getPaymentDetails(Integer page, Integer count, String by, String sort);

    List<PaymentDetailsResponse> getPaymentDetailsByParent(Integer parentId);
}
