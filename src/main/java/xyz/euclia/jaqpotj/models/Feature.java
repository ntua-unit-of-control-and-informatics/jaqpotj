package xyz.euclia.jaqpotj.models;


import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author Pantelispanka
 *
 */
public class Feature extends JaqpotEntity{

    private String _id;

    /**
     * Units of measurement.
     */
    private String units;
    /**
     * In case the feature is a prediction feature, this field is used to refer
     * to the original feature that is predicted. This field will point to a
     * URI.
     */
    private String predictorFor;

    /**
     * In case the feature is nominal, this field stores it admissible values.
     * Whether the field is Nominal or Numeric or String is determined by its
     * ontological classes which can be retrieved from its superclass,
     * {@link JaqpotEntity}.
     */
    private Set<String> admissibleValues;

    public Feature() {
    }

    public Feature(String id) {
        super(id);
    }

    public Feature(Feature other) {
        super(other);
        this.admissibleValues = other.admissibleValues != null ? new HashSet<>(other.admissibleValues) : null;
        this.units = other.units;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setAdmissibleValues(Set<String> admissibleValues) {
        this.admissibleValues = admissibleValues;
    }

    public Set<String> getAdmissibleValues() {
        return admissibleValues;
    }

    public String getPredictorFor() {
        return predictorFor;
    }

    public void setPredictorFor(String predictorFor) {
        this.predictorFor = predictorFor;
    }
}
