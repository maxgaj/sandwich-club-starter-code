package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichData = new JSONObject(json);
            JSONObject nameJSONObject = sandwichData.getJSONObject("name");
            JSONArray akaJSONArray = nameJSONObject.getJSONArray("alsoKnownAs");
            JSONArray ingredientsJSONArray = sandwichData.getJSONArray("ingredients");

            String mainName = nameJSONObject.getString("mainName");
            List<String> alsoKnownAs = parseJSONArrayToList(akaJSONArray);
            String placeOfOrigin = sandwichData.getString("placeOfOrigin");
            String description = sandwichData.getString("description");
            String image = sandwichData.getString("image");
            List<String> ingredients = parseJSONArrayToList(ingredientsJSONArray);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> parseJSONArrayToList(JSONArray array){
        List<String> list = new ArrayList<>();
        int arrayLength = array.length();
        if (arrayLength > 0) {
            for (int i = 0; i < arrayLength; i++) {
                try {
                    list.add(array.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static String parseListToString(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        int listSize = list.size();
        for (int i=0; i<listSize; i++){
            stringBuilder.append("- ");
            stringBuilder.append(list.get(i));
            if (i != listSize-1){
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
