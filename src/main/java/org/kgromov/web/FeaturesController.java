package org.kgromov.web;

import org.kgromov.togglz.features.TemperatureMetricsFeature;
import org.kgromov.togglz.service.FeatureToggleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static org.kgromov.togglz.features.DateFormatFeature.CUSTOM_DATE_FEATURE;
import static org.kgromov.togglz.features.DateFormatFeature.ISO_LOCAL_DATE_FEATURE;

@RestController
@RequestMapping("/features")
public class FeaturesController {
    private final FeatureToggleService toggleService;

    public FeaturesController(FeatureToggleService toggleService) {
        this.toggleService = toggleService;
    }

    @GetMapping("/format")
    public String getCurrentDate() {
        LocalDate now = LocalDate.now();
        if (toggleService.isEnabled(CUSTOM_DATE_FEATURE)) {
            return now.format(CUSTOM_DATE_FEATURE.getFormatter());
        } else {
            return now.format(ISO_LOCAL_DATE_FEATURE.getFormatter());
        }
    }

    @GetMapping("/greetings")
    public String sayGoodbyeOrHello() {
        return toggleService.isEnabled("GOODBYE") ? "Bye" : "Hello";
    }

    @GetMapping("/temperature")
    public String currentTemperature() {
        int currentTemp = ThreadLocalRandom.current().nextInt(-50, 50);
        return toggleService.isEnabled(TemperatureMetricsFeature.CELSIUS)
                ? currentTemp + " C" : (currentTemp * 9 / 5 + 32) + " F";
     }
}
