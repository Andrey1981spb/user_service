package ru.spb.dreamwhite.util.phoneUtil;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberFormat {
}
