package xyz.euclia.jaqpotj.models;

import java.util.*;

public class Prediction {

    private String modelId;
    private String datasetId;
    private List<Map<String, Object>> data;
    private List<Map<String, Object>> predictions;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public List<Map<String, Object>> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Map<String, Object>> predictions) {
        this.predictions = predictions;
    }
}
