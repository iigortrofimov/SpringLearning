package com.kciray;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.CustomPostProcessor;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws ReflectiveOperationException, IOException, URISyntaxException {

/*        BeanFactory beanFactory = new BeanFactory();
        beanFactory.addPostProcessor(new CustomPostProcessor());
        beanFactory.instantiate("com.kciray");
        ProductService productService = (ProductService) beanFactory.getBean("productService");
        System.out.println(productService);
        PromotionsService promotionsService = (PromotionsService) beanFactory.getBean("promotionsService");
        System.out.println(promotionsService);

        beanFactory.populateProperties();
        System.out.println(productService.getPromotionsService());

        beanFactory.injectBeanNames();
        System.out.println("Bean name is " + promotionsService.getBeanName());

        beanFactory.injectBeanFactory();
        System.out.println("BeanFactory is" + promotionsService.getBeanFactory());

        beanFactory.initializeBean();
        beanFactory.close();*/

        ApplicationContext context = new ApplicationContext("com.kciray");
        context.close();
    }
}
