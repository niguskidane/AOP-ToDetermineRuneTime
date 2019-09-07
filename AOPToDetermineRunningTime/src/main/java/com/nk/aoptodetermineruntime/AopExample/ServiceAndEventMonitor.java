package com.nk.aoptodetermineruntime.AopExample;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;


@Aspect
@Configuration
public class ServiceAndEventMonitor {

    private Logger logger= LoggerFactory.getLogger(ServiceAndEventMonitor.class);

    private SaveEvent getEventType(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();
        return method.getAnnotation(SaveEvent.class);
    }

    //Not Completed Yet

}
