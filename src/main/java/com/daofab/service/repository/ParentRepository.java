package com.daofab.service.repository;

import com.daofab.service.domain.Parent;
import com.daofab.service.dto.PaymentDto;
import com.daofab.service.dto.response.PaymentDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mahendra on 5/18/2023
 */
@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {

    @Query(value = "select new com.daofab.service.dto.PaymentDto(p,sum(c.paidAmount)) from Parent as p left join Child as c on p.id = c.parent.id group by p.id")
    Page<PaymentDto> findPaymentDetails(Pageable pageable);

    @Query(value = "select new com.daofab.service.dto.response.PaymentDetailsResponse(c.id, c.parent.sender, c.parent.receiver, c.parent.totalAmount, c.paidAmount) from Child c where c.parent.id = :parentId order by c.id ASC ")
    List<PaymentDetailsResponse> findPaymentDetailsByParent(@Param("parentId") Integer parentId);
}
