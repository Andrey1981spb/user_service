package ru.spb.dreamwhite.util.phoneUtil;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.beans.BeanWrapperImpl;
import ru.spb.dreamwhite.repository.country.CountryMapStore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class ContactNumberValidator implements
        ConstraintValidator<ContactNumberConstraint, Object> {

    private static Logger logger = Logger.getLogger(ContactNumberValidator.class.getName());

    private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    private String number;
    private String locale;

    Object numberValue;
    Object localeValue;

    @Override
    public void initialize(ContactNumberConstraint constraintAnnotation) {
        this.number = constraintAnnotation.number();
        this.locale = constraintAnnotation.locale();
    }

    @Override
    public boolean isValid(Object contactBean, ConstraintValidatorContext constraintValidatorContext) {
        numberValue = new BeanWrapperImpl(contactBean)
                .getPropertyValue(number);
        localeValue = new BeanWrapperImpl(contactBean)
                .getPropertyValue(locale);
        String short_code;
        Boolean isValid;
        if ((localeValue != null) & (localeValue != "")) {
            short_code = CountryMapStore.getShortCode((String) localeValue);
            isValid = validateAndFormatPhoneNumber((String) numberValue, short_code, contactBean);
        } else {
            Iterator<Map.Entry<String, String>> entries = CountryMapStore.getCountryShortcodes().entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                Boolean valid = validateAndFormatPhoneNumber((String) numberValue, entry.getValue(), contactBean);
                if (valid) return valid;
            }
            return false;
        }
        return isValid;
    }

    private boolean validateAndFormatPhoneNumber(String inputPhoneNumber, String shortCode, Object contactBean) {

        logger.info("Processing phone number: " + inputPhoneNumber + " with short code: " + shortCode);

        Phonenumber.PhoneNumber phoneNumberProto;
        boolean isValidNumber = false;

        try {
            phoneNumberProto = phoneUtil.parse(inputPhoneNumber, shortCode);

            boolean isValid = phoneUtil.isValidNumber(phoneNumberProto);
            isValidNumber = isValid;

            logger.info("Is phone number valid: " + isValid);

            if (phoneNumberProto.hasCountryCode()) {
                logger.info("Country code is present: " + phoneNumberProto.getCountryCode());
            } else {
                logger.info("Country code is not present.");
            }

            if (phoneNumberProto.hasNationalNumber()) {
                logger.info("National number is present: " + phoneNumberProto.getNationalNumber());
            } else {
                logger.info("National number is not present.");
            }

            String formattedPhoneNumber = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);

            if (formattedPhoneNumber.startsWith("+")) {
                formattedPhoneNumber = formattedPhoneNumber.replace("+", "");
            }

            Field phoneField = contactBean.getClass().getDeclaredField("phone");
            phoneField.set(contactBean, formattedPhoneNumber);
            phoneField.getName();

        } catch (NumberParseException | NoSuchFieldException | IllegalAccessException e) {
            logger.info(e.getMessage());
        }

        return isValidNumber;
    }

}
