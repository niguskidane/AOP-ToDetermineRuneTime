package com.nk.aoptodetermineruntime.AopExample;

import java.lang.annotation.*;

/*
Custom Annotation Example
 */

@Retention(RetentionPolicy.RUNTIME)
//@Inherited
@Target(ElementType.METHOD)
public @interface LogRunningTime {

}
