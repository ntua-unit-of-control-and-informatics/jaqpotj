package xyz.euclia.jaqpotj.models;

import java.util.List;


/**
 *
 * @author Pantelispanka
 *
 */
public class Trained {
    private Object rawModel;
    private Object pmmlModel;
    private Object additionalInfo;
    List<String> dependentFeatures;
    List<String> independentFeatures;
    List<String> predictedFeatures;
    private List<String> runtime;
    private List<String> implementedWith;
    private List<String> title;
    private List<String> description;
    private List<String> algorithm;
    private boolean batched;


    public Object getRawModel() {
        return rawModel;
    }

    public void setRawModel(Object rawModel) {
        this.rawModel = rawModel;
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

    public List<String> getRuntime() {
        return runtime;
    }

    public void setRuntime(List<String> runtime) {
        this.runtime = runtime;
    }

    public List<String> getImplementedWith() {
        return implementedWith;
    }

    public void setImplementedWith(List<String> implementedWith) {
        this.implementedWith = implementedWith;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDiscription(List<String> description) {
        this.description = description;
    }

    public List<String> getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(List<String> algorithm) {
        this.algorithm = algorithm;
    }

    public List<String> getDependentFeatures() {
        return dependentFeatures;
    }

    public void setDependentFeatures(List<String> dependentFeatures) {
        this.dependentFeatures = dependentFeatures;
    }

    public boolean isBatched() {
        return batched;
    }

    public void setBatched(boolean batched) {
        this.batched = batched;
    }
}
