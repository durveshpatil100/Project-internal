package com.app.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DC_INCOME")
@Data
public class DcIncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomeId;
    private Long caseNum;
    private Double empIncome;
    private Double propertyIncome;
}
