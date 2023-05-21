package com.daofab.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Mahendra on 5/18/2023
 */
@Entity
@Data
@Table(name = "parent")
public class Parent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "total_amount")
    private Double totalAmount;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList;

}
