package com.nk.aoptodetermineruntime.AopExample;

import com.nk.aoptodetermineruntime.AopExample.common.EriJmsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;


@Aspect
@Configuration
public class ServiceAndEventMonitor {

    //@Autowired
   // EriJmsService jmsService;

    private Logger logger= LoggerFactory.getLogger(ServiceAndEventMonitor.class);

    private SaveEvent getEventType(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();
        return method.getAnnotation(SaveEvent.class);
    }

    //Not Completed Yet

    @Around("@annotation(SaveEvent), returning=result")
    public Object around(ProceedingJoinPoint pJoinPoint) throws Throwable {
        logger.info("<<<<<<<<<<<<<<<<<-------------------Save Event Started--------------------------->>>>>>>>>>>>>>>>"+"\n");
        Object request=pJoinPoint.getArgs()[0];

        SaveEvent event = getEventType(pJoinPoint);

       // logger.debug("ServiceMonitor : Event Type "+ event.value());
        logger.info("ServiceMonitor : Event Type "+ event.value());
        logger.info("jmsService.sendEriJms( EriMessageBuilder.prepareJmsMessage( "+event.value()+", "+ request.getClass().getSimpleName()+", "+0+"), "+request+")");

        Object result=pJoinPoint.proceed();

        if(event.replay()){
            logger.info("jmsService.sendEriJms( EriMessageBuilder.prepareJmsMessage( "+event.value()+", "+ request.getClass().getSimpleName()+", "+100+"), "+result+")");
            logger.info(" <<<<<<<<<<<<<<<<<< ServiceMonitor: Send ERI JMS finished "+ event.value()+" >>>>>>>>>>>>>>>>>>>>>>>>>"+"\n");
            //logger.debug("ServiceMonitor: Send ERI JMS finished "+ event.value());

            logger.info("<<<<<<<<<<<<<<<<<-------------------Save Event Finished --------------------------->>>>>>>>>>>>>>>>");

        }
        return result;
    }

}
