package org.springframework.beans.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Resource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.stereotype.Service;

import javax.annotations.PostConstruct;
import javax.annotations.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class BeanFactory {

    private Map<String, Object> singletons = new HashMap<>();
    private List<BeanPostProcessor> postProcessors = new ArrayList<>();

    public Object getBean(String beanName){
        return singletons.get(beanName);
    }

    public void instantiate(String basePackage) throws IOException, URISyntaxException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String path = basePackage.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()){
            URL resource = resources.nextElement();
            File file = new File(resource.toURI());
            for (File classFile: file.listFiles()){
                String fileName = classFile.getName(); //ProductService.class
                if (fileName.endsWith(".class")){
                    String className =  fileName.substring(0, fileName.lastIndexOf("."));
                    Class classObject = Class.forName(basePackage + "." + className);
                    if (classObject.isAnnotationPresent(Service.class)){
                        System.out.println("Service: " + classObject);
                        Object instance = classObject.newInstance();
                        String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                        singletons.put(beanName, instance);
                    }
                }
            }
        }
    }

    public void populateProperties() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (Object object: singletons.values()){
            for (Field field: object.getClass().getDeclaredFields()){
                if (field.isAnnotationPresent(Autowired.class)){
                    for (Object dependency: singletons.values()){
                        if (dependency.getClass().equals(field.getType())){
                            String setterName = "set" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
                            System.out.println("Setter name is " + setterName);
                            Method setter = object.getClass().getMethod(setterName, dependency.getClass());
                            setter.invoke(object, dependency);
                        }
                    }
                } if (field.isAnnotationPresent(Resource.class)){
                    for (String beanName: singletons.keySet()){
                        if (field.getName().equals(beanName)){
                            String setterName = "set" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
                            System.out.println("Setter name is " + setterName);
                            Method setter = object.getClass().getMethod(setterName, singletons.get(beanName).getClass());
                            setter.invoke(object, singletons.get(beanName));
                        }
                    }
                }
            }
        }
    }

    public void injectBeanNames(){
        for (String name: singletons.keySet()){
            Object bean = singletons.get(name);
            if (bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(name);
            }
        }
    }

    public void injectBeanFactory(){
        for (Object bean: singletons.values()){
            if (bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }
    }

    public void initializeBean(){
        for (String beanName:singletons.keySet()){
            Object bean = singletons.get(beanName);
            for (BeanPostProcessor postProcessor: postProcessors){
                postProcessor.postProcessBeforeInitialization(bean, beanName);
            }
            beforeInitialize(bean);
            if (bean instanceof InitializingBean){
                ((InitializingBean) bean).afterPropertiesSet();
            }
            for (BeanPostProcessor postProcessor: postProcessors){
                postProcessor.postProcessAfterInitialization(bean, beanName);
            }
        }
    }

    public Map<String, Object> getSingletons() {
        return singletons;
    }

    public void addPostProcessor(BeanPostProcessor postProcessor){
        postProcessors.add(postProcessor);
    }

    public void close()  {
        for (Object bean: singletons.values()){
            for (Method method: bean.getClass().getMethods()){
                if (method.isAnnotationPresent(PreDestroy.class)){
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (bean instanceof DisposableBean){
                ((DisposableBean)bean).destroy();
            }
        }
    }

    public void beforeInitialize(Object bean){
        for (Method method: bean.getClass().getMethods()){
            if (method.isAnnotationPresent(PostConstruct.class)){
                try {
                    method.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
