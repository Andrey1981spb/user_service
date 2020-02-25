package ru.spb.dreamwhite.util.phoneUtil;

import com.google.i18n.phonenumbers.NumberParseException;
import ru.spb.dreamwhite.repository.country.CountryMapStore;

public class CountryHandler {

    public static String countryHandle(String locale) throws NumberParseException {
        String short_code;
        if ((locale != null) & (locale != "")) {
            short_code = CountryMapStore.getShortCode(locale);
        } else {
            short_code = null;
        }
        return short_code;
    }

}
