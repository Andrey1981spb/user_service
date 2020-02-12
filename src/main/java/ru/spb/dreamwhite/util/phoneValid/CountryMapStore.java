package ru.spb.dreamwhite.util.phoneValid;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountryMapStore {

    public static  Map<String, String> COUNTRY_SHORTCODES = new LinkedHashMap<>();

    /*
    static {
        COUNTRY_SHORTCODES.put("Россия", "RU");
        COUNTRY_SHORTCODES.put("Беларусь", "BY");
        COUNTRY_SHORTCODES.put("Казахстан", "KZ");
    }
     */

    public static String getShortCode(String countryTitle) {
        return COUNTRY_SHORTCODES.get(countryTitle);
    }

    public static void setCountryShortcodes(Map<String, String> countryShortcodes) {
        COUNTRY_SHORTCODES.putAll(countryShortcodes);
    }

    public static void main(String[] args) {
        System.out.println(COUNTRY_SHORTCODES.get("Эквадор"));
    }
}
