package ru.spb.dreamwhite.util.emailUtil;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ConfirmRegistrationPointcut {

    @Pointcut ("within (ru.spb.dreamwhite.util.emailUtil..*)")
    public void registrationUrlLayer (){
    }

}
