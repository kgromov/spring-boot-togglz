package org.kgromov.togglz.features;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;

public enum TemperatureMetricsFeature implements Feature {

    @EnabledByDefault
    CELSIUS,
    FAHRENHEIT
}
