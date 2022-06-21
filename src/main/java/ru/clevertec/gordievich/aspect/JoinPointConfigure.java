package ru.clevertec.gordievich.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class JoinPointConfigure {

    @Pointcut("@annotation(ru.clevertec.gordievich.aspect.annotation.Log)")
    public void loggerPointCut(){

    }
}
