package ru.spb.dreamwhite.util.phoneUtil;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class PhoneFormatter implements Formatter<String> {

    /*
    private javax.swing.text.MaskFormatter formatter;

    public PhoneFormatter(String mask) {
        try {
            this.formatter = new javax.swing.text.MaskFormatter(mask);
            this.formatter.setValueContainsLiteralCharacters(false);

        } catch (ParseException e) {
            throw new IllegalStateException("Parse exception for " + mask, e);
        }
    }
     */

    @Override
    public String parse(String phoneNum, Locale locale) throws ParseException {
       return phoneNum.trim();
      //  return this.formate(phoneNum);
    }

    @Override
    public String print(String phone, Locale locale) {
        return phone.trim();
    }

}
