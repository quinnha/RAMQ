package com.example.ramq.classes.maphelper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DistanceTime {

    protected double getDistanceValue(String jsondata) throws JSONException {
        JSONObject jObject = new JSONObject(jsondata);
        JSONArray routes = jObject.getJSONArray("routes");
        JSONObject jobect2 = routes.getJSONObject(0);
        JSONArray legs = jobect2.getJSONArray("legs");


        JSONObject jobject3 = legs.getJSONObject(0);

        JSONObject distanceInfo = (jobject3.getJSONObject("distance"));


//        System.out.println(distanceInfo.get("text"));
//        Double.parseDouble(distanceInfo.get("value").toString());

        return Double.parseDouble(distanceInfo.get("value").toString());
    }


    protected String getDistanceText(String jsondata) throws JSONException {
        JSONObject jObject = new JSONObject(jsondata);
        JSONArray routes = jObject.getJSONArray("routes");
        JSONObject jobect2 = routes.getJSONObject(0);
        JSONArray legs = jobect2.getJSONArray("legs");


        JSONObject jobject3 = legs.getJSONObject(0);

        JSONObject distanceInfo = (jobject3.getJSONObject("distance"));


//        System.out.println(distanceInfo.get("text"));
//        Double.parseDouble(distanceInfo.get("value").toString());

        return distanceInfo.get("text").toString();
    }


    protected String getTimeText(String jsondata) throws JSONException {
        JSONObject jObject = new JSONObject(jsondata);
        JSONArray routes = jObject.getJSONArray("routes");
        JSONObject jobect2 = routes.getJSONObject(0);
        JSONArray legs = jobect2.getJSONArray("legs");


        JSONObject jobject3 = legs.getJSONObject(0);

        JSONObject timeInfo = (jobject3.getJSONObject("duration"));


        String distanceText = timeInfo.get("text").toString();
//        System.out.println(timeInfo.get("value"));

        return distanceText;
    }

}

