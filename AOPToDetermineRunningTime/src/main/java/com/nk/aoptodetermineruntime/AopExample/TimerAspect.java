package com.nk.aoptodetermineruntime.AopExample;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration

public class TimerAspect {

    Logger logger= LoggerFactory.getLogger(TimerAspect.class);

    @Around("@annotation(LogRunningTime), returning=result")
    public Object logRunningTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

      logger.info(proceedingJoinPoint.getTarget().getClass().getSimpleName()+ " ------>>>>>> " +proceedingJoinPoint.getSignature().getName()+" method has begun" );

      long startingTime=System.currentTimeMillis();

      Object result=proceedingJoinPoint.proceed();

      long endTime=System.currentTimeMillis();

      logger.info(proceedingJoinPoint.getTarget().getClass().getSimpleName()+ " ------>>>>>> " +proceedingJoinPoint.getSignature().getName()+" method finished in "+(endTime-startingTime) +" ms" );

      return result;

    }


}
