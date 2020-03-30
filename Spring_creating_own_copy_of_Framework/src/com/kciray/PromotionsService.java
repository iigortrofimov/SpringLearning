package com.kciray;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.stereotype.Service;

import javax.annotations.PostConstruct;

@Service
public class PromotionsService implements BeanNameAware, BeanFactoryAware, DisposableBean {
    private String beanName;
    private BeanFactory beanFactory;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory  = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void destroy() {
        System.out.println("using interface 'Disposable'");
    }

    @PostConstruct
    public void before(){
        System.out.println("using @PostConstruct");
    }
}
