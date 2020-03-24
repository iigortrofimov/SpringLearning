package org.springframework.beans.factory.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// помечаются зависимости
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
