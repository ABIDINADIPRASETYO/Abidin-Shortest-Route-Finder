package com.example.abidinadi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class activity_place_list extends AppCompatActivity {

    private ArrayList<Place> selectedPlaces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        selectedPlaces = getSelectedPlaces();
        setList();
    }

    public ArrayList<Place> getSelectedPlaces(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if(bundle != null){
            return bundle.getParcelableArrayList("selectedPlaces");
        }
        else{
            return new ArrayList<>();
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case (R.id.button_add_place):
                if(selectedPlaces.size() < 9){
                    intent = new Intent(this, activity_find_place.class);
                    intent.putExtra("bundle", saveSelectedPlaces());
                    startActivity(intent);
                    break;
                }
                else {
                    Toast.makeText(this, getString(R.string.message_you_cant_select_more_places),
                            Toast.LENGTH_SHORT).show();
                    break;
                }


            case (R.id.button_find_route):
                intent = new Intent(this, activity_map.class);
                intent.putExtra("places", saveSelectedPlaces());
                startActivity(intent);
                break;
        }
    }

    public void setList(){
        ListView placesList = (ListView) findViewById(R.id.selected_places_list);
        MyListAdapter adapter = new MyListAdapter(selectedPlaces, R.layout.selected_places_list_element, this);
        placesList.setAdapter(adapter);
    }

    public Bundle saveSelectedPlaces(){
        Bundle places = new Bundle();
        places.putParcelableArrayList("selectedPlaces", selectedPlaces);
        return places;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.warning));
        builder.setMessage(getString(R.string.dialog_message));

        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}
