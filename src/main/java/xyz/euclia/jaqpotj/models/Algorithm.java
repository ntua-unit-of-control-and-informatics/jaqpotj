package xyz.euclia.jaqpotj.models;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pantelispanka
 *
 */
public class Algorithm extends JaqpotEntity{


    /**
     * Algorithm's parameters.
     */
    private Set<Parameter> parameters;
    /**
     * Users' implementations are ranked by other users.
     */
    private int ranking;

    private String _id;

    private MetaInfo meta;

    private String trainingService;
    private String predictionService;
    private String reportService;

    public Algorithm() {
    }

    public Algorithm(String id) {
        super(id);
    }

    public Algorithm(Algorithm other) {
        super(other);
        this.parameters = other.parameters != null ? new HashSet<>(other.parameters) : null;
        this.ranking = other.ranking;
        this.trainingService = other.trainingService;
        this.predictionService = other.predictionService;
        this.reportService = other.reportService;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }


    public String getTrainingService() {
        return trainingService;
    }

    public void setTrainingService(String trainingService) {
        this.trainingService = trainingService;
    }

    public String getPredictionService() {
        return predictionService;
    }

    public void setPredictionService(String predictionService) {
        this.predictionService = predictionService;
    }

    public String getReportService() {
        return reportService;
    }

    public void setReportService(String reportService) {
        this.reportService = reportService;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public void setMeta(MetaInfo meta) {
        this.meta = meta;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
