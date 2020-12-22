package xyz.euclia.jaqpotj.models;

import java.util.List;

public class Parameter extends JaqpotEntity{

    /**
     * The scope of a parameter can either be {@link #MANDATORY Mandatory} or
     * {@link #OPTIONAL Optional}.
     */
    public enum Scope {

        /**
         * If a parameter is tagged as 'Optional' then the client does not need
         * to provide its value explicitly but instead a default value will be
         * used.
         */
        OPTIONAL,
        /**
         * A parameter is mandatory when the user has to provide it's value and
         * no default values can be assigned to it.
         */
        MANDATORY;
    };



    /**
     * Name of the parameter.
     */
    private String name;
    /**
     * Parameter's value. Default value if it is a parameter of an algorithm, or
     * actual value if it is the parameter of a model.
     */
    private Object value;
    /**
     * Scope of the parameter (optional/mandatory).
     */
    private Scope scope;

    private List<Object> allowedValues;

    private Object minValue;
    private Object maxValue;

    private Integer minArraySize;
    private Integer maxArraySize;

    private String description;

    public Parameter() {
    }

    public Parameter(String id) {
        super(id);
    }

    public Parameter(Parameter other) {
        super(other);
        this.name = other.name;
        this.scope = other.scope;
        this.value = other.value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public List<Object>     getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<Object> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public Object getMinValue() {
        return minValue;
    }

    public void setMinValue(Object minValue) {
        this.minValue = minValue;
    }

    public Object getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Object maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getMinArraySize() {
        return minArraySize;
    }

    public void setMinArraySize(Integer minArraySize) {
        this.minArraySize = minArraySize;
    }

    public Integer getMaxArraySize() {
        return maxArraySize;
    }

    public void setMaxArraySize(Integer maxArraySize) {
        this.maxArraySize = maxArraySize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
