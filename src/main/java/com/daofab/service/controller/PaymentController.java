package com.daofab.service.controller;

import com.daofab.service.dto.ResponseDto;
import com.daofab.service.dto.response.PaymentResponse;
import com.daofab.service.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahendra on 5/18/2023
 */
@RestController
@Api(value = "Payment Controller")
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService  paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @ApiOperation(value = "Get All Payments API", response = PaymentResponse.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Content-Type", value = "application/json", paramType = "header"),
            @ApiImplicitParam(name = "Authorization", value = "Generated access token", paramType = "header")})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> getAllPayments(@RequestParam Integer page,
                                                      @RequestParam Integer limit,
                                                      @RequestParam Optional<String> by,
                                                      @RequestParam Optional<String> sort) {
        ResponseDto responseDto = ResponseDto.success(paymentService.getPaymentDetails(page, limit, by.orElseGet(() -> "id"), sort.orElseGet(() -> "asc")));
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/parent/{parentId}")
    @ApiOperation(value = "Get Payment Details API", response = PaymentResponse.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Content-Type", value = "application/json", paramType = "header"),
            @ApiImplicitParam(name = "Authorization", value = "Generated access token", paramType = "header")})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> getPaymentDetails(@PathVariable Integer parentId) {
        ResponseDto responseDto = ResponseDto.success(paymentService.getPaymentDetailsByParent(parentId));
        return ResponseEntity.ok(responseDto);
    }

}
