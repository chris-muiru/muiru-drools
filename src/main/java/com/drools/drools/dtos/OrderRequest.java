package com.drools.drools.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class OrderRequest{
    private String customerNumber;
    private String customerAge;
    private Integer amount;
    private CustomerType customerType;
}

