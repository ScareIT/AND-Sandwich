package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String placeOfOrigin;
        String description;
        String imageUrl;
        String mainName;
        JSONArray altNames;
        JSONArray ingredients;

        try {
            JSONObject jSandwich = new JSONObject(json);
            JSONObject jNames = jSandwich.getJSONObject("name");
            mainName = jNames.getString("mainName");
            altNames = jNames.getJSONArray("alsoKnownAs");

            placeOfOrigin = jSandwich.getString("placeOfOrigin");
            description = jSandwich.getString("description");
            imageUrl = jSandwich.getString("image");
            ingredients = jSandwich.getJSONArray("ingredients");
        } catch (JSONException e) {
            return null;
        }

        if(placeOfOrigin.equals("")) {
            placeOfOrigin = "Not available";
        }

        List<String> altNamesList = new ArrayList<String>();
        for (int i=0;i<altNames.length();i++){
            try {
                altNamesList.add(altNames.getString(i));
            } catch (JSONException e) {
                altNamesList.add("Strange name...");
            }
        }

        List<String> ingredientsList = new ArrayList<String>();
        for (int i=0;i<ingredients.length();i++){
            try {
                ingredientsList.add(ingredients.getString(i));
            } catch (JSONException e) {
                ingredientsList.add("Strange ingredient...");
            }
        }




        return new Sandwich(mainName, altNamesList, placeOfOrigin, description, imageUrl, ingredientsList);
    }
}
