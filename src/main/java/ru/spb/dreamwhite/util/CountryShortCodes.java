package ru.spb.dreamwhite.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountryShortCodes {

    public static final Map<String, String> COUNTRY_SHORTCODES = new LinkedHashMap<>();

    static {
        COUNTRY_SHORTCODES.put("Россия", "RU");
        COUNTRY_SHORTCODES.put("Беларусь", "BY");
        COUNTRY_SHORTCODES.put("Казахстан", "KZ");
    }

    public static String getShortCode(String countryTitle) {
        return COUNTRY_SHORTCODES.get(countryTitle);
    }

}
