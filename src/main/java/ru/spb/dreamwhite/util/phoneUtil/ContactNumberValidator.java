package ru.spb.dreamwhite.util.phoneUtil;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.spb.dreamwhite.repository.country.CountryMapStore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class ContactNumberValidator implements
        ConstraintValidator<ContactNumberConstraint, Object> {

    private static Logger logger = Logger.getLogger(ContactNumberValidator.class.getName());

    private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    private String number;
    private String short_code;

    @Override
    public void initialize(ContactNumberConstraint constraintAnnotation) {
        this.number = constraintAnnotation.number();
        this.short_code = constraintAnnotation.sh();
    }

    @Override
    public boolean isValid(Object contactField, ConstraintValidatorContext constraintValidatorContext) {
        Object numberValue = new BeanWrapperImpl(contactField)
                .getPropertyValue(number);
        Object localeValue = new BeanWrapperImpl(contactField)
                .getPropertyValue(short_code);
        Boolean isValid;

        logger.info("SHORT_CODE IN VALIDATOR IS " + localeValue);

        if ((localeValue != null) & (localeValue != "")) {
            isValid = validateAndFormatPhoneNumber((String) numberValue, (String) localeValue);
        } else {
            Iterator<Map.Entry<String, String>> entries = CountryMapStore.getCountryShortcodes().entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                Boolean valid;
                valid = validateAndFormatPhoneNumber((String) numberValue, entry.getValue());
                if (valid) return valid;
            }
            return false;
        }
        return isValid;
    }

    private boolean validateAndFormatPhoneNumber(String inputPhoneNumber, String shortCode) {

        logger.info("Processing phone number: " + inputPhoneNumber + " with short code: " + shortCode);

        Phonenumber.PhoneNumber phoneNumberProto;
        boolean isValid = false;

        try {
            phoneNumberProto = phoneUtil.parse(inputPhoneNumber, shortCode);

            try{isValid = phoneUtil.isValidNumber(phoneNumberProto);}
            catch (ConstraintViolationException e){
                e.getMessage();
            }

            logger.info(phoneNumberProto + " valid is " + isValid);

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

        } catch (NumberParseException e) {
            logger.info(e.getMessage());
        }

        return isValid;
    }

}
