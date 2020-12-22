package xyz.euclia.jaqpotj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pantelispanka
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Model extends JaqpotEntity{

    private String _id;

    /**
     * List of dependent features of the model.
     */
    private List<String> dependentFeatures;
    /**
     * List of independent features.
     */
    private List<String> independentFeatures;
    /**
     * List of predicted features.
     */
    private List<String> predictedFeatures;

    /**
     * Reliability of the model (ranking).
     */
    private Integer reliability = 0;
    /**
     * URI of the dataset of this model.
     */
    private String datasetUri;
    /**
     * Set of parameters of this model.
     */
    private Map<String, Object> parameters;
    /**
     * Algorithm that was used to create this model.
     */
    private Algorithm algorithm;
    /**
     * The actual model as a string (ASCII).
     */
    private Object actualModel;
    /**
     * PMML representation of the model itself. Equivalent to the actualModel,
     * but in PMML format.
     */
    private Object pmmlModel;
    /**
     * A PMML defining the transformations of input features.
     */

    private Object additionalInfo;

    private String pmmlTransformations;

    private String doaModel;

    private List<String> transformationModels;

    private List<String> linkedModels;

    public Model() {
    }

    public Model(String id) {
        super(id);
    }

    /**
     * Copy-constructor for Model objects.
     *
     * @param other model to be copied
     *
     * @see #getPmmlModel()
     * @see #getPmmlTransformations()
     * @see #getActualModel()
     */
    public Model(Model other) {
        super(other);
        this.algorithm = other.algorithm != null ? new Algorithm(other.algorithm) : null;
        this.datasetUri = other.datasetUri;
        this.dependentFeatures = other.dependentFeatures != null
                ? new ArrayList<>(other.dependentFeatures) : null;
        this.independentFeatures = other.independentFeatures != null
                ? new ArrayList<>(other.independentFeatures) : null;
        this.parameters = other.parameters != null
                ? new HashMap<>(other.parameters) : null;
        this.predictedFeatures = other.predictedFeatures != null
                ? new ArrayList<>(other.predictedFeatures) : null;
        this.reliability = other.reliability;
        this.actualModel = other.actualModel;
        this.pmmlModel = other.pmmlModel;
        this.pmmlTransformations = other.pmmlTransformations;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getDependentFeatures() {
        return dependentFeatures;
    }

    public void setDependentFeatures(List<String> dependentFeatures) {
        this.dependentFeatures = dependentFeatures;
    }

    public List<String> getIndependentFeatures() {
        return independentFeatures;
    }

    public void setIndependentFeatures(List<String> independentFeatures) {
        this.independentFeatures = independentFeatures;
    }

    public List<String> getPredictedFeatures() {
        return predictedFeatures;
    }

    public void setPredictedFeatures(List<String> predictedFeatures) {
        this.predictedFeatures = predictedFeatures;
    }

    public Integer getReliability() {
        return reliability;
    }

    public void setReliability(Integer reliability) {
        this.reliability = reliability;
    }

    public String getDatasetUri() {
        return datasetUri;
    }

    public void setDatasetUri(String datasetUri) {
        this.datasetUri = datasetUri;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Object getActualModel() {
        return actualModel;
    }

    public void setActualModel(Object actualModel) {
        this.actualModel = actualModel;
    }

    public Object getPmmlModel() {
        return pmmlModel;
    }

    public void setPmmlModel(Object pmmlModel) {
        this.pmmlModel = pmmlModel;
    }

    public Object getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Object additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getPmmlTransformations() {
        return pmmlTransformations;
    }

    public void setPmmlTransformations(String pmmlTransformations) {
        this.pmmlTransformations = pmmlTransformations;
    }

    public String getDoaModel() {
        return doaModel;
    }

    public void setDoaModel(String doaModel) {
        this.doaModel = doaModel;
    }

    public List<String> getTransformationModels() {
        return transformationModels;
    }

    public void setTransformationModels(List<String> transformationModels) {
        this.transformationModels = transformationModels;
    }

    public List<String> getLinkedModels() {
        return linkedModels;
    }

    public void setLinkedModels(List<String> linkedModels) {
        this.linkedModels = linkedModels;
    }

}
