package com.sky.dev.etkan.Core.parsers;

import com.sky.dev.etkan.Core.models.PointsModel;
import com.sky.dev.etkan.Core.models.StudentModel;
import com.tradinos.network.TradinosParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentPointsParser implements TradinosParser<ArrayList<PointsModel>> {
    @Override
    public ArrayList<PointsModel> Parse(String text) throws JSONException {

        JSONArray jsonArray = new JSONArray(text);
        ArrayList<PointsModel> pointsModelArrayList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject pointDetails = jsonArray.getJSONObject(i);
            pointsModelArrayList.add(new PointsModel(pointDetails.getString("id"), pointDetails.getString("materials")
                    , pointDetails.getString("studentPoint")
                    , pointDetails.getString("materialPoint")));
        }
        return pointsModelArrayList;
    }
}
