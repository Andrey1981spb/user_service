package ru.spb.dreamwhite.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.logging.Logger;

public class Formatter {

    private static Logger logger = Logger.getLogger(Formatter.class.getName());
    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static String formate(String input, String shortCode) throws NumberParseException {
        String formattedNumber = null;
        try {
            Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(input, shortCode);
            formattedNumber = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            logger.info(e.getMessage());
        }
        return formattedNumber;
    }

    public static void main(String[] args) throws NumberParseException {
        System.out.println(formate("1-12345", "AT"));
        System.out.println(formate("252-06-69", "RU"));
    }
}
