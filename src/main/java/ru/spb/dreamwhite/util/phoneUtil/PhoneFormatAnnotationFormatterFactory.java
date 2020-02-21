package ru.spb.dreamwhite.util.phoneUtil;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class PhoneFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<ContactNumberFormat>, Serializable {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(String.class);
    }

    @Override
    public Parser<?> getParser(ContactNumberFormat contactNumberFormat, Class<?> fieldType) {
        return new PhoneFormatter();
    }

    @Override
    public Printer<?> getPrinter(ContactNumberFormat contactNumberFormat, Class<?> fieldType) {
        return new PhoneFormatter();
    }

}
