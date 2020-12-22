package xyz.euclia.jaqpotj;

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
import xyz.euclia.jaqpotj.exception.JaqpotException;
import xyz.euclia.jaqpotj.models.*;
import xyz.euclia.jaqpotj.serializer.Serializer;

public class JaqpotImplementationTest {

    private static Jaqpot client;
    private static Serializer serializer;

    private static String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ3Ujh3X1lGOWpKWFRWQ2x2VHF1RkswZkctQXROQUJsb3FBd0N4MmlTTWQ4In0.eyJleHAiOjE2MDg2NDA5MzcsImlhdCI6MTYwODYzMzczNywiYXV0aF90aW1lIjoxNjA4NjMzNzM3LCJqdGkiOiI4ZjVkNjdmZC1hYzI1LTQ0YjgtYTVhZC1lMGUyYTY3MmZhMjAiLCJpc3MiOiJodHRwczovL2xvZ2luLmphcXBvdC5vcmcvYXV0aC9yZWFsbXMvamFxcG90IiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjI0MjVkNzYwLTAxOGQtNDA4YS1hZTBiLWNkZTRjNTYzNTRiOSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImphcXBvdC11aS1jb2RlIiwibm9uY2UiOiJlMjAxMzdkZGZiODZhNjQ0OTQzYzM1NjYyMDc3YzhlZmJiRXk4U1g3ZyIsInNlc3Npb25fc3RhdGUiOiI0NjMwNjE0MS1lNmIxLTQ4MGEtOTc0ZS1iMDUwZTFkN2M4ZDgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIicqJyIsIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBqYXFwb3QtYWNjb3VudHMgZW1haWwgcHJvZmlsZSB3cml0ZSByZWFkIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiUGFudGVsaXMgS2FyYXR6YXMiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJwYW50ZWxpc3BhbmthIiwiZ2l2ZW5fbmFtZSI6IlBhbnRlbGlzIiwiZmFtaWx5X25hbWUiOiJLYXJhdHphcyIsImVtYWlsIjoicGFudGVsaXNwYW5rYUBnbWFpbC5jb20ifQ.n1Jc88djouoFw8TeHB5vM0kouoG0Ees3vVJdaTNNUfOXGWK5LbnLf1sSbUjfuMkBZUJCpc5BUYflvjLpYzibjkb_kt8mlWCgUcWV9-Mj0Ps3skG5hrDgCnqVGtrc-9pgB85cleX-uKPmU3WknrlYtZGiBaNt0ud77cqGNlpKcXqy3uuxVrS7fg8pIGkjAHBmQ1yFnQba4OGcxpDQh7AO78hpgkgJ8bimBhEgCaMjbuJlzDyAXRnyGp5TuvM2MPmiKl_KiOl0xB0vChdRuXQhjBE7iFSqnO3srATrcXhWwsdsSmr0nWKFAYOpfBWJyfMWqLQDMrIOLUpZ__eAm4mWBQ";

    public JaqpotImplementationTest(){}

    @BeforeClass
    public static void setUpClass() {
        client = new JaqpotFactory().create("https://api.jaqpot.org",
                        new JaqpotSerializer(new ObjectMapper()));

        serializer = new JaqpotSerializer(new ObjectMapper());
    }

    @Test
    public void testGetModel(){
        try{
            Future<Model> model = client.GetModelById("gQjfUtEHHuwGnnxmlsyl", token);
            Model m = model.get();
            Assert.assertEquals("gQjfUtEHHuwGnnxmlsyl", m.get_id());
            System.out.println("---PRINTING A MODEL---");
            System.out.println(model.get().getIndependentFeatures());
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFeatures(){
        try{
            Future<Model> model = client.GetModelById("gQjfUtEHHuwGnnxmlsyl", token);
            Model m = model.get();
            Assert.assertEquals("gQjfUtEHHuwGnnxmlsyl", m.get_id());
            System.out.println("---PRINTING A MODEL FEATURES---");
            model.get().getIndependentFeatures().forEach(f ->{
                String[] f_ar = f.split("/");
                String fId = f_ar[f_ar.length - 1];
                Future<Feature> feature = client.GetFeatureById(fId, token);
                try {
                    Feature feat = feature.get();
                    System.out.println(feat.getMeta().getTitles().toArray()[0]);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetModels(){
        try{
            Future<Models> model = client.GetUsersModels(0, 10, token);
            Models m = model.get();
            System.out.println("---PRINTING MY MODELS---");
            System.out.println(m.getTotal());
            m.getModels().forEach(mo->{
                System.out.println(mo.get_id());
            });
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOrgsModels(){
        try{
            Future<Models> model = client.GetOrgsModels("joR8745jm3Q8AQrp", 0, 10, token);

            Models m = model.get();
            System.out.println("---PRINTING ORGS MODELS---");
            System.out.println(m.getTotal());
            m.getModels().forEach(mo->{
                System.out.println(mo.get_id());
            });
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDataset(){
        try{
            Future<Dataset> dataset = client.GetDatasetById("A9sALGKPLVoBfRrDbRfTsw", token);
            Dataset d = dataset.get();
            System.out.println("---PRINTING DATASET---");
            System.out.println(d.get_id());
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPredict(){
        try{
            List<Map<String, Object>> datas = new ArrayList<>();
            Map<String, Object> data = new HashMap<>();
            data.put("LFG", 0.1);
            data.put("EQP", 0.12);
            data.put("NEQ", 0.15);
            data.put("GAP", 0.2);
            datas.add(data);
            Future<Prediction>  dataset = client.Predict("vX26QRcZro2mFDi209Mu", datas, token);
            System.out.println(dataset.get().getData());
        }catch (InterruptedException | JaqpotException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
