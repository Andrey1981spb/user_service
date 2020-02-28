package ru.spb.dreamwhite.util.phoneUtil;

import ru.spb.dreamwhite.repository.country.CountryMapStore;

import java.util.Objects;

public class CountryHandler {

    public static String countryHandle(String locale) {
        String short_code;
        if ((locale != null) & (!Objects.equals(locale, ""))) {
            short_code = CountryMapStore.getShortCode(locale);
        } else {
            short_code = null;
        }
        return short_code;
    }
}
