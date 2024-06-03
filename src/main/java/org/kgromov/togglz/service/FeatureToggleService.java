package org.kgromov.togglz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

@Service
public class FeatureToggleService {
    private static final Logger logger = LoggerFactory.getLogger(FeatureToggleService.class);
    private final FeatureManager manager;

    public FeatureToggleService(FeatureManager manager) {
        this.manager = manager;
    }

    public boolean isEnabled(String featureName) {
        return this.isEnabled(new NamedFeature(featureName));
    }

    public boolean isEnabled(Feature feature) {
        boolean active = manager.isActive(feature);
        boolean enabled = manager.getFeatureState(feature).isEnabled();
        logger.info("Feature = {}: active = {}, enabled = {}", feature.name(), feature.isActive(), enabled);
        return enabled;
    }

    public List<String> getFeatures() {
        return ofNullable(manager.getFeatures())
                .stream()
                .flatMap(Collection::stream)
                .map(Feature::name)
                .distinct()
                .sorted()
                .toList();
    }
}
