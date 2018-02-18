package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView knownAsLabel;
    private TextView knownAs;
    private TextView origin;
    private TextView description;
    private TextView ingredients;

    private Sandwich sandwich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        if(this.sandwich.getAlsoKnownAs().size() > 0) {
            String knowAs = "";
            for (String knowAsLine :
                    this.sandwich.getAlsoKnownAs()) {
                knowAs += knowAsLine + "\n";
            }
            this.knownAs = findViewById(R.id.also_known_tv);
            this.knownAs.setText(knowAs);
        }
        else {
            this.knownAsLabel = findViewById(R.id.also_known_label_tv);
            this.knownAsLabel.setVisibility(View.GONE);
        }

        String ingredient = "";
        for (String ingredientLine:
                this.sandwich.getIngredients()) {
            ingredient += ingredientLine + "\n";
        }
        this.ingredients = findViewById(R.id.ingredients_tv);
        this.ingredients.setText(ingredient);

        this.origin = findViewById(R.id.origin_tv);
        this.origin.setText(sandwich.getPlaceOfOrigin() + "\n");

        this.description = findViewById(R.id.description_tv);
        this.description.setText(sandwich.getDescription() + "\n");
    }
}
