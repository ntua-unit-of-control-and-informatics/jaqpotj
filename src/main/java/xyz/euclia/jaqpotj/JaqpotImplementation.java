package xyz.euclia.jaqpotj;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Param;
import xyz.euclia.jaqpotj.adapter.DatasetAdapterFactory;
import xyz.euclia.jaqpotj.consumers.DatasetConsumer;
import xyz.euclia.jaqpotj.consumers.FeatureConsumer;
import xyz.euclia.jaqpotj.consumers.ModelConsumer;
import xyz.euclia.jaqpotj.consumers.TaskConsumer;
import xyz.euclia.jaqpotj.exception.JaqpotException;
import xyz.euclia.jaqpotj.models.*;
import xyz.euclia.jaqpotj.serializer.Serializer;
import xyz.euclia.jaqpotj.serializer.TypeReference;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class JaqpotImplementation implements Jaqpot{

    private final Serializer serializer;
    private final AsyncHttpClient httpClient;

    private final ModelConsumer modelConsumer;
    private final FeatureConsumer featureConsumer;
    private final DatasetConsumer datasetConsumer;
    private final TaskConsumer taskConsumer;

    public JaqpotImplementation(
            AsyncHttpClient client,
            Serializer serializer,
            ModelConsumer modelConsumer,
            FeatureConsumer featureConsumer,
            DatasetConsumer datasetConsumer,
            TaskConsumer taskConsumer) {
        this.httpClient = client;
        this.serializer = serializer;
        this.modelConsumer = modelConsumer;
        this.taskConsumer = taskConsumer;
        this.featureConsumer = featureConsumer;
        this.datasetConsumer = datasetConsumer;
    }


    @Override
    public Future<Model> GetModelById(String id, String token) throws JaqpotException {
        TypeReference<Model> c =new TypeReference<Model>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        };
        return this.modelConsumer.getWithId(id, token, c);
    }

    @Override
    public Future<Models> GetUsersModels(Number start, Number max, String token) throws JaqpotException {
        Param p1 = new Param("start", start.toString());
        Param p2 = new Param("max", max.toString());
        ArrayList<Param> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        return this.modelConsumer.getModelsWithParams(ps, token);
    }

    @Override
    public Future<Models> GetOrgsModels(String orgId, Number start, Number max, String token) throws JaqpotException {
        Param p = new Param("organization", orgId);
        Param p1 = new Param("start", start.toString());
        Param p2 = new Param("max", max.toString());
        ArrayList<Param> ps = new ArrayList<>();
        ps.add(p);
        ps.add(p1);
        ps.add(p2);
        return this.modelConsumer.getModelsWithParams(ps, token);
    }

    @Override
    public Future<Models> GetOrgsTagModels(String orgId, String tag, Number start, Number max, String token) throws JaqpotException {
        Param p = new Param("organization", orgId);
        Param p1 = new Param("tag", tag);
        Param p2 = new Param("start", start.toString());
        Param p3 = new Param("max", max.toString());
        ArrayList<Param> ps = new ArrayList<>();
        ps.add(p);
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        return this.modelConsumer.getModelsWithParams(ps, token);
    }

    @Override
    public Future<Feature> GetFeatureById(String featId, String token) throws JaqpotException {
        TypeReference<Feature> c =new TypeReference<Feature>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
        return this.featureConsumer.getWithId(featId, token, c);
    }

    @Override
    public Future<Dataset> GetDatasetById(String datasetId, String token) throws JaqpotException {
        TypeReference<Dataset> c =new TypeReference<Dataset>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
        List<Param> params = new ArrayList<>();
        Param p1 = new Param("dataEntries", "true");
        Param p2 = new Param("rowStart", "0");
        Param p3 = new Param("rowMax", "500");
        params.add(p1);
        params.add(p2);
        params.add(p3);
        return this.datasetConsumer.getWithIdAndParams(datasetId, params, token, c);
    }

    @Override
    public Future<Prediction>  Predict(String modelId, List<Map<String, Object>> data, String token) throws JaqpotException, ExecutionException, InterruptedException {
        TypeReference<Model> c =new TypeReference<Model>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
        Model m = this.modelConsumer.getWithId(modelId, token, c).get();
        LinkedHashMap<String, Object> additionalInfo = (LinkedHashMap<String, Object>) m.getAdditionalInfo();
        LinkedHashMap<String, String> indF = (LinkedHashMap<String, String>) additionalInfo.get("independentFeatures");
        DatasetAdapterFactory df = new DatasetAdapterFactory();
        Dataset dataset = df.createFromMap(indF, data);
        TypeReference<Dataset> d =new TypeReference<Dataset>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
        String datas = this.serializer.write(dataset);
        Dataset datasetNew = this.datasetConsumer.post(datas, token, d).get();
        String datasetId = datasetNew.get_id();
        Task task = this.modelConsumer.predict(modelId, datasetId, token).get();
        Future<Prediction>  datF = this.handletask(task,modelId, token);
        return datF;
    }

    private Future<Prediction> handletask(Task task,String modelId,String token) throws ExecutionException, InterruptedException {
        Future f = null;
        Float percentage = null;
        TypeReference<Dataset> d =new TypeReference<Dataset>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
        try{
            percentage = task.getPercentageCompleted();
        }catch (NullPointerException e){
            TimeUnit.MILLISECONDS.sleep(80);
            return this.handletask(task, modelId, token);

        }
        if(percentage == null || percentage < 100.0){
            TimeUnit.MILLISECONDS.sleep(280);
            TypeReference<Task> c =new TypeReference<Task>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            };
            Task taskn = this.taskConsumer.getWithId(task.get_id(), token, c).get();
            return this.handletask(taskn,modelId, token);
        }else if(percentage == 100.0){
            try{
                ErrorReport er = task.getErrorReport();
                throw new JaqpotException(er.getMessage());
            }catch (NullPointerException e){
                List<Param> params = new ArrayList<>();
                Param p1 = new Param("dataEntries", "true");
                Param p2 = new Param("rowStart", "0");
                Param p3 = new Param("rowMax", "500");
                params.add(p1);
                params.add(p2);
                params.add(p3);
                Dataset datasetPr = this.datasetConsumer.getWithIdAndParams(task.getResult().split("/")[1], params, token, d).get();
                Prediction pred = new Prediction();
                pred.setDatasetId(datasetPr.get_id());
                pred.setModelId(modelId);
                Map<String, String> predMK = new HashMap<>();
                Map<String, String> valsK = new HashMap<>();
                datasetPr.getFeatures().forEach(featureInfo -> {
                    if(featureInfo.getCategory() != null && featureInfo.getCategory().toString() == "PREDICTED"){
                        predMK.put(featureInfo.getKey(), featureInfo.getName());
                    }else{
                        valsK.put(featureInfo.getKey(), featureInfo.getName());
                    }
                });
                List<Map<String, Object>> predictions = new ArrayList<>();
                List<Map<String, Object>> values = new ArrayList<>();


                datasetPr.getDataEntry().forEach(dataEntry -> {
                    Map<String, Object> prediction = new HashMap<>();
                    Map<String, Object> value = new HashMap<>();
                    predMK.entrySet().forEach(pk ->{
                        prediction.put(pk.getValue(), dataEntry.getValues().get(pk.getKey()));
                    });

                    valsK.entrySet().forEach(pk ->{
                        value.put(pk.getValue(), dataEntry.getValues().get(pk.getKey()));
                    });
                    predictions.add(prediction);
                    values.add(value);
                });
                pred.setData(values);
                pred.setPredictions(predictions);
                return  CompletableFuture.completedFuture(pred);
//                return this.datasetConsumer.getWithId(task.getResult().split("/")[1], token, d);
            }

        }
        return null;
//        return this.datasetConsumer.getWithId(task.getResult().split("/")[1], token, d);
    }


    private Task getTask(String taskId, String token) throws ExecutionException, InterruptedException {
        TypeReference<Task> c =new TypeReference<Task>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
        Task task = this.taskConsumer.getWithId(taskId, token, c).get();
        return task;
    }

    @Override
    public void close() throws IOException {

    }
}
