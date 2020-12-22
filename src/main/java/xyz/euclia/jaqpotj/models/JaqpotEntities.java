package xyz.euclia.jaqpotj.models;

import java.util.List;

public class JaqpotEntities {

    private List<JaqpotEntity> jaqpotEntities;
    private Integer total;

    public List<JaqpotEntity> jaqpotEntities() {
        return jaqpotEntities;
    }

    public void setJe(List<JaqpotEntity> jaqpotEntities) {
        this.jaqpotEntities = jaqpotEntities;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
