package com.drools.drools.controller;

import com.drools.drools.dtos.OrderDiscount;
import com.drools.drools.dtos.OrderRequest;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
//@RequiredArgsConstructor
public class OrderDiscountController {
//    @Autowired
//    private OrderDiscountService orderDiscountService;
    @Autowired
    private KieSession kieSession;
    @PostMapping(path = "discount")
    public OrderDiscount getOrderDiscount(@RequestBody  OrderRequest orderRequest){
        OrderDiscount orderDiscount = new OrderDiscount();
//        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("orderDiscount", orderDiscount);
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderDiscount;

//        return orderDiscountService.orderDiscount(orderRequest);
    }
}
