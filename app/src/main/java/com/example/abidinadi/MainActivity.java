package com.example.abidinadi;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case(R.id.main_find_route_btn):
                intent = new Intent(this, activity_find_place.class);
                startActivity(intent);
                break;
            case (R.id.main_add_place_btn):
                intent = new Intent(this, activity_add_place.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
    }
}
