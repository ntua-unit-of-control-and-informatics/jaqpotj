package xyz.euclia.jaqpotj.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Pantelispanka
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataEntry{

    String _id;

//    Substance compound;

    TreeMap<String, Object> values;

    String datasetId;

    EntryId entryId;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public EntryId getEntryId() {
        return entryId;
    }

    public void setEntryId(EntryId entryId) {
        this.entryId = entryId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

//    public Substance getCompound() {
//        return compound;
//    }
//
//    public void setCompound(Substance compound) {
//        this.compound = compound;
//    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(TreeMap<String, Object> values) {
        this.values = values;
    }

//    @Override
//    public String toString() {
//        return "DataEntry{" + "compound=" + compound + ", values=" + values + '}';
//    }

}
