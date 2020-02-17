package ru.spb.dreamwhite.util.phoneUtil;

import org.springframework.format.Formatter;
import ru.spb.dreamwhite.model.User;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneFormatter implements Formatter<String> {

    @Override
    public String parse(String phoneNum, Locale locale) throws ParseException {
        return this.formate(phoneNum);
    }

    @Override
    public String print(String phone, Locale locale) {
        return this.formate(phone);
    }

    private String formate(String text){
        text = text.trim();
        String regex = "^\\(?(\\+*1)?[-.\\s*]?([0-9]{3})\\)?[-.\\s*]?([0-9]{3})[-.\\s*]?([0-9]{4})$";
        Matcher matcher = Pattern.compile(regex).matcher(text);
        User user = new User();
        user.setPhone(matcher.toString());
        return matcher.toString();
    }


}
