package com.daofab.service.service.impl;

import com.daofab.service.dto.PaymentDto;
import com.daofab.service.dto.response.PaymentDetailsResponse;
import com.daofab.service.dto.response.PaymentResponse;
import com.daofab.service.exception.CustomException;
import com.daofab.service.repository.ParentRepository;
import com.daofab.service.service.PaymentService;
import com.daofab.service.util.CustomErrorCodes;
import com.daofab.service.util.SortUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahendra on 5/18/2023
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final ParentRepository parentRepository;

    public PaymentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    /**
     * getPaymentDetails
     * @param page
     * @param count
     * @param by
     * @param sort
     * @return
     */
    @Override
    public PaymentResponse getPaymentDetails(Integer page, Integer count, String by, String sort) {
        List<PaymentDto> paymentResponse = new ArrayList<>();
        long totalItems;
        try{
            Pageable pageable = PageRequest.of(page, count, SortUtil.getSort(by, sort));
            logger.debug("Page {} count {}", page, count);
            paymentResponse = parentRepository.findPaymentDetails(pageable).getContent();
            totalItems = parentRepository.findPaymentDetails(pageable).getTotalElements();
            logger.debug("Payment List {}", paymentResponse.size());
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException(CustomErrorCodes.INVALID_RECORD);
        }
        return  new PaymentResponse(totalItems, paymentResponse);
    }

    /**
     * getPaymentDetailsByParent
     * @param parentId
     * @return
     */
    @Override
    public List<PaymentDetailsResponse> getPaymentDetailsByParent(Integer parentId) {
        List<PaymentDetailsResponse> paymentDetailsResponses = new ArrayList<>();
        try{
            logger.debug("Parent Id {} ", parentId);
            paymentDetailsResponses = parentRepository.findPaymentDetailsByParent(parentId);
            logger.debug("Payment details Size {}", paymentDetailsResponses.size());
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException(CustomErrorCodes.PAYMENT_DETAIL_ERROR);
        }
        return  paymentDetailsResponses;
    }


}
