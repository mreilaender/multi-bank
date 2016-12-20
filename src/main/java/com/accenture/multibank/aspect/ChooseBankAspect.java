package com.accenture.multibank.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author manuel
 * @version 12/20/16
 */
@Aspect
public class ChooseBankAspect {
    @Pointcut("execution(* com.accenture.multibank.controller.Bankcontroller.book(..))")
    public void chooseWhichBankToUse(){};

    @Around("chooseWhichBankToUse()")
    public Object determineBank(ProceedingJoinPoint joinPoint) {
        return null;
    }
}
