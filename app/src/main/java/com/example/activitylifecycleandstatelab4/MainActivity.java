package com.example.activitylifecycleandstatelab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.activitylifecycleandstatelab4.Model.Dessert;
import com.example.activitylifecycleandstatelab4.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG ="MAIN_ACTIVITY";

    private final static String KEY_REVENUE ="revenue_key";
    private final static String KEY_DESSERT_SOLD ="dessert_sold_key";

    private int revenue = 0;
    private int dessertSold =0;

    private List<Dessert> dessertList = returnDesertList();

    private Dessert currentDessert = dessertList.get(0);

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDessertClicked();
            }
        });
        /**
         * Once configuration change happens, or any event that triggers the execution of onCreate() method happens
         * if onSavedInstance method is overwritten and value is provided then, retrieval can be done via the provided
         * block of code.
         */
        if(savedInstanceState!=null)
        {
            Log.d(TAG, "onCreate: I'm in");
            revenue=savedInstanceState.getInt(KEY_REVENUE,0);
            dessertSold=savedInstanceState.getInt(KEY_DESSERT_SOLD, 0);
            showCurrentDessert();
        }
        Log.d(TAG, "onCreate Called. ");

        binding.setRevenue(revenue);
        binding.setAmountSold(dessertSold);

        binding.dessertButton.setImageResource(currentDessert.getImageId());
        setTitle(R.string.title);
    }

    /**
     *
     * @param outState : Bundle that stores value for later retrieval.
     * Function called if configuration changes happens which redraws the entire layout
     * also calling the onCreate method once again.
     *
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Called");

        outState.putInt(KEY_REVENUE, revenue);
        outState.putInt(KEY_DESSERT_SOLD, dessertSold);
    }


    /**
     * Life cycle methods.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart called.");
    }

    /**
     * To increase the textView of revenue and dessertSold
     */
    private void onDessertClicked()
    {
        revenue+=currentDessert.getPrice();
        dessertSold++;

        binding.setRevenue(revenue);
        binding.setAmountSold(dessertSold);

        showCurrentDessert();

    }

    /**
     * Menu methods
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.shareMenuButton:
                onShare();
                break;
        }
        return true;
    }

    //implicit intent passing for sharing.
    public void onShare()
    {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setText(getString(R.string.share_text, dessertSold, revenue))
                .setType("text/plain")
                .getIntent();

        try{
            startActivity(shareIntent);
        }catch(ActivityNotFoundException exc)
        {
            Toast.makeText(MainActivity.this, "Sharing not available.", Toast.LENGTH_SHORT);
        }
    }


    /*
    * To show which dessert to show.
    *
    */

    private void showCurrentDessert(){

        Dessert newDessert = dessertList.get(0);
        for(Dessert dessert : dessertList)
        {
            if(dessertSold>= dessert.getStartProductionAmount())
                newDessert=dessert;
            else break;
        }

        if(newDessert!=currentDessert){
            currentDessert = newDessert;
            binding.dessertButton.setImageResource(newDessert.getImageId());
        }


    }



    //Returns the list of desserts.
    private List<Dessert> returnDesertList()
    {
        List<Dessert> dessertList = new ArrayList<Dessert>();
        dessertList.add(new Dessert(R.drawable.cupcake, 5, 0));
        dessertList.add(new Dessert(R.drawable.donut, 10, 5));
        dessertList.add(new Dessert(R.drawable.eclair, 15, 20));
        dessertList.add(new Dessert(R.drawable.froyo, 30, 50));
        dessertList.add(new Dessert(R.drawable.gingerbread, 50, 100));
        dessertList.add(new Dessert(R.drawable.honeycomb, 100, 200));
        dessertList.add(new Dessert(R.drawable.icecreamsandwich, 500, 500));
        dessertList.add(new Dessert(R.drawable.jellybean, 1000, 1000));
        dessertList.add(new Dessert(R.drawable.kitkat, 2000, 2000));
        dessertList.add(new Dessert(R.drawable.lollipop, 3000, 4000));
        dessertList.add(new Dessert(R.drawable.marshmallow, 4000, 8000));
        dessertList.add(new Dessert(R.drawable.nougat, 5000, 16000));
        dessertList.add(new Dessert(R.drawable.oreo, 6000, 20000));

        return dessertList;

    }
}