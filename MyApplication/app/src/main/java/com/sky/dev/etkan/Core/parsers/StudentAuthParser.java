package com.sky.dev.etkan.Core.parsers;

import com.sky.dev.etkan.Core.models.StudentModel;
import com.tradinos.network.TradinosParser;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentAuthParser implements TradinosParser<StudentModel> {
    @Override
    public StudentModel Parse(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);

        return new StudentModel(jsonObject.getInt("user_key"),jsonObject.getString("first_name")
                ,jsonObject.getString("last_name"),jsonObject.getString("father_name"),
                jsonObject.getString("address"),jsonObject.getString("email"));
    }
}
