package xyz.euclia.jaqpotj;

import org.asynchttpclient.Response;
import xyz.euclia.jaqpotj.exception.JaqpotException;
import xyz.euclia.jaqpotj.models.*;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author pantelispanka
 */
public interface Jaqpot extends Closeable {

    /**
     *
     * Get a model by it's idp
     *
     * @param id is the models id
     * @return Future Response get the response when finished.
     * If 200 status you get the model.
     * Other you get an ErrorReport.
     *
     */
    Future<Model> GetModelById(String id, String token) throws JaqpotException;

    /**
     *
     * Get the users models
     *
     * @return Future Response get the response when finished.
     * If 200 status you get the model.
     * Other you get an ErrorReport.
     *
     */
    Future<Models> GetUsersModels(Number start, Number max, String token) throws JaqpotException;


    /**
     *
     * Get an organizations models
     *
     * @param orgId is the organizations id
     * @return Future Response get the response when finished.
     * If 200 status you get the model.
     * Other you get an ErrorReport.
     *
     */
    Future<Models> GetOrgsModels(String orgId, Number start, Number max, String token) throws JaqpotException;

    /**
     *
     * Get an organizations models by tag
     *
     * @param orgId is the organizations id
     * @param tag is a tag
     * @return Future Response get the response when finished.
     * If 200 status you get the model.
     * Other you get an ErrorReport.
     *
     */
     Future<Models> GetOrgsTagModels(String orgId, String tag, Number start, Number max, String token) throws JaqpotException;

    /**
     *
     * Get a feature by it's id
     *
     * @param featId is the organizations i
     * @return Future Response get the response when finished.
     * If 200 status you get the model.
     * Other you get an ErrorReport.
     *
     */
    Future<Feature> GetFeatureById(String featId, String token) throws JaqpotException;


    /**
     *
     * Get a dataset
     *
     * @param datasetId is the dataset's id
     * @return Future Response get the response when finished.
     * If 200 status you get the model.
     * Other you get an ErrorReport.
     *
     */
    Future<Dataset> GetDatasetById(String datasetId, String token) throws JaqpotException;

    /**
     *
     * Get a dataset
     *
     * @param modelId is the models id
     * @param data is a Map with keys the feature names and Object the data values
     * @return Future Response get the response when finished.
     * If 200 status you get the model.
     * Other you get an ErrorReport.
     *
     */
    Future<Prediction> Predict(String modelId, List<Map<String, Object>> data, String token) throws JaqpotException, ExecutionException, InterruptedException;


}