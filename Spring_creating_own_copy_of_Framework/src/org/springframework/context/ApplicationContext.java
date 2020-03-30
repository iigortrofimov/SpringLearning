package org.springframework.context;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.event.ContextClosedEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URISyntaxException;

public class ApplicationContext {
    private BeanFactory beanFactory = new BeanFactory();

    public ApplicationContext(String basePackage) throws ReflectiveOperationException, IOException, URISyntaxException {
        beanFactory.instantiate(basePackage);
        beanFactory.populateProperties();
        beanFactory.injectBeanFactory();
        beanFactory.injectBeanNames();
        beanFactory.initializeBean();
    }

    public void close() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Object bean: beanFactory.getSingletons().values()){
            if (bean instanceof ApplicationListener){
                for(Type type: bean.getClass().getGenericInterfaces()){
                    if(type instanceof ParameterizedType){
                        ParameterizedType parameterizedType = (ParameterizedType) type;
                        Type firstParameter = parameterizedType.getActualTypeArguments()[0];
                        if (firstParameter.equals(ContextClosedEvent.class)){
                            Method method = bean.getClass().getMethod("executeAfterEvent", ContextClosedEvent.class);
                            method.invoke(bean, new ContextClosedEvent());
                        }
                    }
                }
            }
        }
        //beanFactory.close();
    }
}
