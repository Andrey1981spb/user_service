package ru.spb.dreamwhite.util.phoneUtil;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class PhoneFormatter implements BeanPostProcessor {

    private Object beanIs;

    private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        if (bean.getClass().isAnnotationPresent(ContactNumberFormate.class)) {
            beanIs = bean.getClass();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        String phone = "";
        String locale = "";
        try {
            phone = (String) beanIs.getClass().getDeclaredField("phone").get(bean);
            locale = (String) beanIs.getClass().getDeclaredField("locale").get(bean);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Phonenumber.PhoneNumber phoneNumberProto = null;
        try {
            phoneNumberProto = phoneUtil.parse(phone, locale);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        String formattedPhoneNumber = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);

        if (formattedPhoneNumber.startsWith("+")) {
            formattedPhoneNumber = formattedPhoneNumber.replace("+", "");
        }

        return formattedPhoneNumber;
    }

}
