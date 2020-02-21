package ru.spb.dreamwhite.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class Formatter {

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static String formate(String input, String shortCode) throws NumberParseException {

        Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(input, shortCode);
        return phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);

    }

    public static void main(String[] args) throws NumberParseException {
        System.out.println(formate("8812 454 57-6 8", "RU"));
    }
}
