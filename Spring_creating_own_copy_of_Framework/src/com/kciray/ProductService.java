package com.kciray;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.stereotype.Service;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import javax.annotations.PreDestroy;


@Service
public class ProductService implements InitializingBean, ApplicationListener<ContextClosedEvent> {
    //@Resource
    @Autowired
    private PromotionsService promotionsService;

    public PromotionsService getPromotionsService(){
        return promotionsService;
    }

    public void setPromotionsService(PromotionsService promotionsService){
        this.promotionsService = promotionsService;
    }

    @Override
    public void afterPropertiesSet() {
        //реализация
    }

    @PreDestroy
    public void beforeClose(){
        System.out.println("using @PreDestroy");
    }

    @Override
    public void executeAfterEvent(ContextClosedEvent event) {

        System.out.println("Closed Event");
    }
}
