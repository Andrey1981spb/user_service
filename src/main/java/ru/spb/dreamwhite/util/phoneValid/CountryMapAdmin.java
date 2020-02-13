package ru.spb.dreamwhite.util.phoneValid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spb.dreamwhite.service.country.CountryService;

import javax.annotation.PostConstruct;

@Component
public class CountryMapAdmin {

    @Autowired
  CountryService countryService;

    @PostConstruct
    public void fillMap() {
        countryService.getAll();
    }

}
