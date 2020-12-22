package xyz.euclia.jaqpotj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dataset extends JaqpotEntity{


    public enum DescriptorCategory {

        EXPERIMENTAL("Experimental data", "Nanomaterial properties derived from experiments"),
        IMAGE("ImageAnalysis descriptors", "Descriptors derived from analyzing substance images by the ImageAnalysis software."),
        GO("GO descriptors", "Descriptors derived by proteomics data."),
        MOPAC("Mopac descriptors", "Descriptors derived by crystallographic data."),
        CDK("CDK descriptors", "Descriptors derived from cdk software."),
        PREDICTED("Predicted descriptors", "Descriptors derived from algorithm predictions."),
        FORPREDICTION("Created for prediction", "Dataset created and is temp for prediction");

        private final String name;

        private final String description;

        private DescriptorCategory(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum DatasetExistence {

        UPLOADED("Uploaded", "Dataset uploaded from user"),
        CREATED("Created", "Dataset created from outer source"),
        TRANFORMED("Transformed", "Dataset transformed"),
        PREDICTED("Predicted", "Dataset is a result of a prediction"),
        DESCRIPTORSADDED("Descriptors added", "Dataset has added descriptors"),
        FROMPRETRAINED("Pretrained empty", "Dataset empty for pretrained model"),
        FORPREDICTION("Created for prediction", "Dataset created and is temp for prediction");

        private final String name;
        private final String description;

        private DatasetExistence(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

    }

    private String _id;

    private String datasetURI;

    private String byModel;

    private List<DataEntry> dataEntry;

    private Set<FeatureInfo> features;

    private String datasetPic;

    private Integer totalRows;
    private Integer totalColumns;

    private Set<DescriptorCategory> descriptors;
    private DatasetExistence existence;

    private Boolean onTrash;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDatasetURI() {
        return datasetURI;
    }

    public void setDatasetURI(String datasetURI) {
        this.datasetURI = datasetURI;
    }

    public String getByModel() {
        return byModel;
    }

    public void setByModel(String byModel) {
        this.byModel = byModel;
    }

    public List<DataEntry> getDataEntry() {
        return dataEntry;
    }

    public void setDataEntry(List<DataEntry> dataEntry) {
        this.dataEntry = dataEntry;
    }

    public Set<FeatureInfo> getFeatures() {
        return features;
    }

    public void setFeatures(Set<FeatureInfo> features) {
        this.features = features;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(Integer totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Set<DescriptorCategory> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(Set<DescriptorCategory> descriptors) {
        this.descriptors = descriptors;
    }

    public String getDatasetPic() {
        return datasetPic;
    }

    public void setDatasetPic(String datasetPic) {
        this.datasetPic = datasetPic;
    }

    public DatasetExistence getExistence() {
        return existence;
    }

    public void setExistence(DatasetExistence existence) {
        this.existence = existence;
    }

    public Boolean getOnTrash() {
        return onTrash;
    }

    public void setOnTrash(Boolean onTrash) {
        this.onTrash = onTrash;
    }

    @Override
    public String toString() {
        return "Dataset{" + "datasetURI=" + datasetURI + ", dataEntry=" + dataEntry + '}';
    }

}
