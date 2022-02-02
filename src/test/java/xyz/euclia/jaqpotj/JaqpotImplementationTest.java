package xyz.euclia.jaqpotj;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.Param;
import org.asynchttpclient.Response;
import org.junit.Assert.*;
import org.junit.*;
import xyz.euclia.jaqpotj.JaqpotFactory;
import xyz.euclia.jaqpotj.exception.JaqpotException;
import xyz.euclia.jaqpotj.models.*;
import xyz.euclia.jaqpotj.serializer.Serializer;

public class JaqpotImplementationTest {

    private static Jaqpot client;
    private static Serializer serializer;

    private static String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIweFBKV0lzSFB2OW5fY2pETVVwYkJaLXl6SVBtazVkeVhoX2RjWGRfQkFZIn0.eyJleHAiOjE2MTUzNzMzOTYsImlhdCI6MTYxNTM2OTc5NiwianRpIjoiMzhiNWU3OTctZTExMS00YzVkLWE4MzEtMjViN2E5NzE1OGQ0IiwiaXNzIjoiaHR0cHM6Ly9sb2dpbi5jbG91ZC5uYW5vc29sdmVpdC5ldS9hdXRoL3JlYWxtcy9uYW5vc29sdmVpdCIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIzYWJmODQ4ZC1lMjk3LTRlNjgtODA3Mi0yZGQxY2VkODk1MDUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJtb2RlbHNiYXNlLWFwaSIsInNlc3Npb25fc3RhdGUiOiIxNDZjNjcxOS00YWQ4LTQwZDQtOWFiNS0wNTJjNDU3ZDU3MWQiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIlwiKlwiIiwiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJuYW5vc29sdmVpdF91c2VyIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImFkZHJlc3MiOnt9LCJuYW1lIjoiUGFudGVsaXMgS2FyYXR6YXMiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJwYW50ZWxpc3BhbmthIiwiZ2l2ZW5fbmFtZSI6IlBhbnRlbGlzIiwiZmFtaWx5X25hbWUiOiJLYXJhdHphcyIsImVtYWlsIjoicGFudGVsaXNwYW5rYUBnbWFpbC5jb20ifQ.IgeICYg5p950kiyL5SPeOfTIA61OjioQNA4k6N4hxSTdPDy5Ud24Jmu1IvMqZBYLjk6B02a2EaHgyigRBALUXEsWqv0AUGb4FEyp9Yx7dsurG1Rcjb3ce_V8L8dWfqkBgTytX6tqaPbS1uMBvibgUOpDetBqhNqZuVVf9cmcnMTaHZdSFZTe2iXglxLNJ1jQ7LiNboaaz9ASfB4KDyZx3zWpwx9ZzkCRHOw7YpuqmaAkQBX2V1jMRgL0A4SUm72B-GxO2c4HYnQD49GCkh50Cex51yWYLgo2L50MOdrPFK3LRg-RajSYmc0EEhw3PHBcYwZ_UXzhZaWSPjsVxS0bTQ";


    public JaqpotImplementationTest(){}

    @BeforeClass
    public static void setUpClass() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        client = new JaqpotFactory().create("https://modelsbase.cloud.nanosolveit.eu/modelsbase",
                new JaqpotSerializer(objectMapper));
//        client = new JaqpotFactory().create("https://modelsbase.cloud.nanosolveit.eu/modelsbase", new JaqpotSerializer(objectMapper));

//        client = new JaqpotFactory().create("https://api.jaqpot.org/jaqpot",
//                new JaqpotSerializer(objectMapper));

//        client = new JaqpotFactory().create("https://api.jaqpot.org/jaqpot",
//                        new JaqpotSerializer(new ObjectMapper()));

//        serializer = new JaqpotSerializer(new ObjectMapper());
    }


    @Test
    public void testLogin(){
        try{
            Future<Auth> auth = client.Login("pantelispanka", "kapan2");
            Auth m = auth.get();
            token = auth.get().getAuthToken();
            System.out.println("---PRINTING A TOKEN---");
            System.out.println(m.getAuthToken());
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }


//    @Test
//    public void testGetModel(){
//        try{
//            Future<Model> model = client.GetModelById("gQjfUtEHHuwGnnxmlsyl", token);
//            Model m = model.get();
//            Assert.assertEquals("gQjfUtEHHuwGnnxmlsyl", m.get_id());
//            System.out.println("---PRINTING A MODEL---");
//            System.out.println(model.get().getIndependentFeatures());
//        }catch (InterruptedException | JaqpotException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetFeatures(){
//        try{
//            Future<Model> model = client.GetModelById("gQjfUtEHHuwGnnxmlsyl", token);
//            Model m = model.get();
//            Assert.assertEquals("gQjfUtEHHuwGnnxmlsyl", m.get_id());
//            System.out.println("---PRINTING A MODEL FEATURES---");
//            model.get().getIndependentFeatures().forEach(f ->{
//                String[] f_ar = f.split("/");
//                String fId = f_ar[f_ar.length - 1];
//                Future<Feature> feature = client.GetFeatureById(fId, token);
//                try {
//                    Feature feat = feature.get();
//                    System.out.println(feat.getMeta().getTitles().toArray()[0]);
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//            });
//        }catch (InterruptedException | JaqpotException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetModels(){
//        try{
//            Future<Models> model = client.GetUsersModels(0, 10, token);
//            Models m = model.get();
//            System.out.println("---PRINTING MY MODELS---");
//            System.out.println(m.getTotal());
//            m.getModels().forEach(mo->{
//                System.out.println(mo.get_id());
//            });
//        }catch (InterruptedException | JaqpotException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetOrgsModels(){
//        try{
//            Future<Models> model = client.GetOrgsModels("joR8745jm3Q8AQrp", 0, 10, token);
//
//            Models m = model.get();
//            System.out.println("---PRINTING ORGS MODELS---");
//            System.out.println(m.getTotal());
//            m.getModels().forEach(mo->{
//                System.out.println(mo.get_id());
//            });
//        }catch (InterruptedException | JaqpotException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetDataset(){
//        try{
//            Future<Dataset> dataset = client.GetDatasetById("A9sALGKPLVoBfRrDbRfTsw", token);
//            Dataset d = dataset.get();
//            System.out.println("---PRINTING DATASET---");
//            System.out.println(d.get_id());
//        }catch (InterruptedException | JaqpotException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testPredict(){
//        try{
//            List<Map<String, Object>> datas = new ArrayList<>();
//            Map<String, Object> data = new HashMap<>();
//            data.put("LFG", 0.1);
//            data.put("EQP", 0.12);
//            data.put("NEQ", 0.15);
//            data.put("GAP", 0.2);
//            datas.add(data);
//            Future<Prediction>  dataset = client.Predict("vX26QRcZro2mFDi209Mu", datas, token);
//            System.out.println(dataset.get().getData());
//        }catch (InterruptedException | JaqpotException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testPredict(){
//        try{
//            List<Map<String, Object>> datas = new ArrayList<>();
//            Map<String, Object> data = new HashMap<>();
//            data.put("LFG", 0.1);
//            data.put("EQP", 0.12);
//            data.put("NEQ", 0.15);
//            data.put("GAP", 0.2);
//            datas.add(data);
//            Future<Prediction>  dataset = client.Predict("vX26QRcZro2mFDi209Mu", datas, token);
//            System.out.println(dataset.get().getData());
//        }catch (InterruptedException | JaqpotException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }


    @Test
    public void testPredict(){
        try{
//            List<Map<String, Object>> datas = new ArrayList<>();
//            Map<String, Object> data = new HashMap<>();
//            data.put("weight", 20);
//            data.put("size", 10);
//            data.put("sim.start", 0);
//            data.put("sim.end", 8);
//            data.put("sim.step", 0.1);
//
//            List<Integer> ec = new ArrayList<>();
//            ec.add(10);
//            ec.add(15);
//            ec.add(0);
//            data.put("exposure.concentration", ec);
//
//            List<Integer> et = new ArrayList<>();
//            et.add(0);
//            et.add(1);
//            et.add(2);
//            data.put("exposure.time", et);

            Future<Auth> authF = client.Login("pantelispanka", "kapan2");

            Auth auth = authF.get();
            String tokenGot = auth.getAuthToken();

            List<Map<String, Object>> datas = new ArrayList<>();
            Map<String, Object> data = new HashMap<>();
            data.put("weight", 20);
            data.put("size", 10);
            data.put("sim.start", 0);
            data.put("sim.end", 8);
            data.put("sim.step", 0.1);

            List<Integer> ec = new ArrayList<>();
            ec.add(10);
            ec.add(15);
            ec.add(0);
            data.put("exposure.concentration", ec);

            List<Integer> et = new ArrayList<>();
            et.add(0);
            et.add(1);
            et.add(2);
            data.put("exposure.time", et);

            datas.add(data);
            Future<Prediction>  dataset = client.Predict("BLO6obkeJCXwLQypfNoR", datas, tokenGot);
            Prediction p = dataset.get();
            System.out.println(p.getPredictions());
            System.out.println(dataset.get().getData());
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    
    @Test
    public void testPredictNtua(){
        try{

            Future<Auth> authF = client.Login("pantelispanka", "kapan2");

            Auth auth = authF.get();
            String tokenGot = auth.getAuthToken();

            List<Map<String, Object>> datas = new ArrayList<>();
            Map<String, Object> data = new HashMap<>();
            data.put("Coating", 1);
            data.put("Corresponding sphere diameter (nm)", 21.0966);
            data.put("Diameter  (TEM-STEM)", 21.0966);
            data.put("Energy Band Gap", 1.46);
            data.put("Geometric Surface Area (nm2)", 1398.217746);
            data.put("MW", 107.8682);
            data.put("Noxygen", 0);
            data.put("Type of Coating (Anionic, Cationic, Neutral)", 2);
            data.put("x", 1.93);
            data.put("Σx/nO", 1.93);
            
            Map<String, Object> data2 = new HashMap<>();
            data2.put("Coating", 1);
            data2.put("Corresponding sphere diameter (nm)", 21.83523);
            data2.put("Diameter  (TEM-STEM)", 15.8000);
            data2.put("Energy Band Gap", 3.200000);
            data2.put("Geometric Surface Area (nm2)", 1497.840000);
            data2.put("MW", 172.1150);
            data2.put("Noxygen", 2.0);
            data2.put("Type of Coating (Anionic, Cationic, Neutral)", 2);
            data2.put("x", 1.120);
            data2.put("Σx/nO", 0.56000);
            
            datas.add(data);
            datas.add(data2);
            Future<Prediction>  dataset = client.Predict("I6f4mcZXXjLpNiQ6HIHA", datas, tokenGot);
            Prediction p = dataset.get();
            System.out.println(p.getPredictions());
            System.out.println(dataset.get().getData());
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testPredictQsar(){
        try{

            Future<Auth> authF = client.Login("pantelispanka", "kapan2");

            Auth auth = authF.get();
            String tokenGot = auth.getAuthToken();

            List<Map<String, Object>> datas = new ArrayList<>();
            Map<String, Object> data = new HashMap<>();
            data.put("Coating", 1);
            data.put("Corresponding sphere diameter (nm)", -0.366706);
            data.put("Diameter  (TEM-STEM)", -0.409623);
            data.put("Energy Band Gap", -0.846215);
            data.put("Geometric Surface Area (nm2)", -0.185454);
            data.put("Type of Coating (Anionic, Cationic, Neutral)", 0);
            data.put("Σx/nO", 1.237053);
            
            
            Map<String, Object> data2 = new HashMap<>();
            data2.put("Coating", 1);
            data2.put("Corresponding sphere diameter (nm)", -0.364133);
            data2.put("Diameter  (TEM-STEM)", -0.427879);
            data2.put("Energy Band Gap", -0.247625);
            data2.put("Geometric Surface Area (nm2)", -0.185429);
            data2.put("Type of Coating (Anionic, Cationic, Neutral)", 0);
            data2.put("Σx/nO", -0.726641);
            
            datas.add(data);
            datas.add(data2);
            Future<Prediction>  dataset = client.Predict("FADKIH6f5B7IEEL5rHX2", datas, tokenGot);
            Prediction p = dataset.get();
            System.out.println(p.getPredictions());
            System.out.println(dataset.get().getData());
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
