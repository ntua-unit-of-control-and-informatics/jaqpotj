package xyz.euclia.jaqpotj.adapter;

import xyz.euclia.jaqpotj.models.DataEntry;
import xyz.euclia.jaqpotj.models.Dataset;
import xyz.euclia.jaqpotj.models.EntryId;
import xyz.euclia.jaqpotj.models.FeatureInfo;

import java.util.*;

public class DatasetAdapterFactory {

    public Dataset createFromMap(LinkedHashMap<String, String> independentFeatures, List<Map<String, Object>> datas){
        Dataset d = new Dataset();
        Map<String, String> featKeyVal = new HashMap<>();
        int i = 0;
        Set<FeatureInfo> fis = new HashSet<FeatureInfo>();
        for(Map.Entry<String, String> indf:independentFeatures.entrySet()){
            FeatureInfo fi = new FeatureInfo();
            fi.setName(indf.getValue());
            fi.setURI(indf.getKey());
            fi.setKey(Integer.toString(i));
            featKeyVal.put(indf.getValue(), Integer.toString(i));
            fis.add(fi);
            i += 1;
        }
        d.setFeatures(fis);

        List<DataEntry> lde = new ArrayList<>();
        for(Map<String, Object> data: datas){
            EntryId ei= new EntryId();
            ei.setName("From Java client");
            DataEntry de = new DataEntry();
            de.setEntryId(ei);
            TreeMap<String, Object> values = new TreeMap<>();
            for(Map.Entry<String, Object> val : data.entrySet()){
                values.put(featKeyVal.get(val.getKey()), val.getValue());
            }
            de.setValues(values);
            lde.add(de);
        }
        d.setDataEntry(lde);
        return d;
    }
}
