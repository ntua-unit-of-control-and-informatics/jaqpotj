package xyz.euclia.jaqpotj.models;

import java.util.List;

public class Models {
    private List<Model> models;
    private Integer total;


    public Models(){}

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
