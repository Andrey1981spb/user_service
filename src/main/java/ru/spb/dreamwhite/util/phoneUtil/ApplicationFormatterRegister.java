package ru.spb.dreamwhite.util.phoneUtil;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class ApplicationFormatterRegister implements FormatterRegistrar {
    @Override
    public void registerFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatterForFieldAnnotation(new PhoneFormatAnnotationFormatterFactory());
    }
}
