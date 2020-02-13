package ru.spb.dreamwhite.repository.country;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountryMapStore {

    public static  Map<String, String> COUNTRY_SHORTCODES = new LinkedHashMap<>();

    public static String getShortCode(String countryTitle) {
        return COUNTRY_SHORTCODES.get(countryTitle);
    }

    public static void setCountryShortcodes(Map<String, String> countryShortcodes) {
        COUNTRY_SHORTCODES.putAll(countryShortcodes);
    }

    public static Map<String, String> getCountryShortcodes() {
        return COUNTRY_SHORTCODES;
    }

    public static void main(String[] args) {
        System.out.println(COUNTRY_SHORTCODES.get("Эквадор"));
    }
}
