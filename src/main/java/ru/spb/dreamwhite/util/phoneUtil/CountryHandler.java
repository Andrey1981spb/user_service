package ru.spb.dreamwhite.util.phoneUtil;

import ru.spb.dreamwhite.repository.country.CountryMapStore;

public class CountryHandler {

    public static String countryHandle(String locale) {
        String short_code;
        if ((locale != null) & (!locale.equals(""))) {
            short_code = CountryMapStore.getShortCode(locale);
        } else {
            short_code = null;
        }
        return short_code;
    }
}
