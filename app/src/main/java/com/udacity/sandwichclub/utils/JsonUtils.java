package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jSandwich = new JSONObject(json);
        } catch (JSONException e) {
            return null;
        }

        return new Sandwich("Panino", Arrays.asList("sup1", "sup2", "sup3"), "Ïtaglia", "Descrizione", "Immagine", Arrays.asList("pomodoro", "cane", "gatto"));
    }
}
