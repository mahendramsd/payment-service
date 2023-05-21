package com.daofab.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mahendra on 5/18/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "child")
public class Child implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Column(name = "paid_amount")
    private Double paidAmount;
}
