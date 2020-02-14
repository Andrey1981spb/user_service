package ru.spb.dreamwhite.util.phoneValid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spb.dreamwhite.service.country.CountryService;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class CountryMapAdmin {

    private static Logger logger = Logger.getLogger(ContactNumberValidator.class.getName());

    @Autowired
  CountryService countryService;

    @PostConstruct
    public void fillMap() {
        countryService.getAll();
        logger.info("CountryMap is successfully filled");
    }

}
