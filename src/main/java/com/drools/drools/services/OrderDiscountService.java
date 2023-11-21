package com.drools.drools.services;

import com.drools.drools.dtos.OrderDiscount;
import com.drools.drools.dtos.OrderRequest;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrderDiscountService {
    @Autowired
    private KieSession kieSession;

    public OrderDiscount orderDiscount(OrderRequest orderRequest){
        OrderDiscount orderDiscount = new OrderDiscount();
//        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("orderDiscount", orderDiscount);
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderDiscount;

    }
}
