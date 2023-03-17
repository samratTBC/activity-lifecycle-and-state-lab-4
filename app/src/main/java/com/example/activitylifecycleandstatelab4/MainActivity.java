package com.example.activitylifecycleandstatelab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
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

    private int revenue = 0;
    private int dessertSold =0;

    private List<Dessert> dessertList;

    private Dessert currentDessert;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        dessertList = returnDesertList();
        currentDessert = dessertList.get(0);

        binding.dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDessertClicked();
            }
        });

        binding.setRevenue(revenue);
        binding.setAmountSold(dessertSold);

        binding.dessertButton.setImageResource(currentDessert.getImageId());


    }

    private void onDessertClicked()
    {
        revenue+=currentDessert.getPrice();
        dessertSold++;

        binding.setRevenue(revenue);
        binding.setAmountSold(dessertSold);

        showCurrentDessert();

    }

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

    private void showCurrentDessert(){

        Dessert newDessert = dessertList.get(0);
        for(Dessert dessert : dessertList)
        {
            if(dessertSold>= currentDessert.getStartProductionAmount())
                newDessert=dessert;
            binding.dessertButton.setImageResource(newDessert.getImageId());
        }


    }

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