package ru.spb.dreamwhite.util.phoneUtil;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

public class PhoneFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<ContactNumberFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        //  return Collections.<Class<?>>singleton(String.class);
        Set<Class<?>> setTypes = new HashSet<Class<?>>();
        setTypes.add(String.class);
        return setTypes;
    }

    @Override
    public Printer<?> getPrinter(ContactNumberFormat contactNumberFormat, Class<?> aClass) {
        return new PhoneFormatter();
    }

    @Override
    public Parser<?> getParser(ContactNumberFormat contactNumberFormat, Class<?> aClass) {
        return new PhoneFormatter();
    }

}
