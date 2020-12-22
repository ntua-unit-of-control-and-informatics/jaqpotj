package xyz.euclia.jaqpotj.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Pantelispanka
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureInfo {

    private String URI;
    private String name;
    private String units;
    private Map<String, Object> conditions;
    private Dataset.DescriptorCategory category;
    private String ont;
    private String key;

    public FeatureInfo() {
    }

    public FeatureInfo(String URI, String name) {
        this.URI = URI;
        this.name = name;
    }

    public String getOnt() {
        return ont;
    }

    public void setOnt(String ont) {
        this.ont = ont;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    public Dataset.DescriptorCategory getCategory() {
        return category;
    }

    public void setCategory(Dataset.DescriptorCategory category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.URI);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FeatureInfo other = (FeatureInfo) obj;
        if (!Objects.equals(this.URI, other.URI)) {
            return false;
        }
        return true;
    }
}
