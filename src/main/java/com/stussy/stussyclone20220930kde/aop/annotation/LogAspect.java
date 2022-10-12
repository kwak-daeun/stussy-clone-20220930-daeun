package com.stussy.stussyclone20220930kde.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //프로그램 실행중에 쓰는 annotaion
@Target({ElementType.TYPE, ElementType.METHOD})// 클래스, 메소드 위에 요소 달면된다
public @interface LogAspect {
}
