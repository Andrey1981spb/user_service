package ru.spb.dreamwhite.util.phoneValid;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.spb.dreamwhite.model.Country;
import ru.spb.dreamwhite.repository.country.JdbcCountryRepository;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CountryMapAdmin {

    private static final Map<String, String> countryMap = new LinkedHashMap<>();

    public static void fillMap() {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            JdbcCountryRepository jdbcCountryRepository = appCtx.getBean(JdbcCountryRepository.class);
            List<Country> countryList = jdbcCountryRepository.getAll();

            for (Country country : countryList) {
                countryMap.put(country.getTitle(), country.getCode());
                //      System.out.println(country.getTitle() + country.getCode());
            }

            CountryMapStore.setCountryShortcodes(countryMap);

        }
    }

    public static void main(String[] args) {
        fillMap();
    }

}
