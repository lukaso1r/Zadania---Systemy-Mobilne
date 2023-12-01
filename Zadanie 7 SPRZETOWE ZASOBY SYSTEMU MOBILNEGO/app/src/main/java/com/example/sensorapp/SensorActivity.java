package com.example.sensorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    SensorManager sensorManager;
    List<Sensor> sensorList;
    RecyclerView recyclerView;
    SensorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_activity);

        recyclerView = findViewById(R.id.sensor_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if (adapter == null){
            adapter = new SensorAdapter(sensorList);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }


    }


    //      SENSOR HOLDER START -------------------------------------------------------------------------------------------------------------
    private class SensorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ZMIENIĆ NAZWY ZMIENNYCH NA TAKIE JAK W PUNKCIE 7 ######################################################################################################################
        private ImageView iconImageView;
        private TextView nameTextView;
        private Sensor sensor;
        public SensorHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sensor_list_item, parent, false));
            itemView.setOnClickListener(this);
            //Long Click Listener
            itemView.setOnLongClickListener(v -> {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SensorActivity.this);
                dialogBuilder.setMessage(sensor.getVendor() + "\n\n" + sensor.getMaximumRange())
                        .setTitle(R.string.sensor_details_alert_title)
//                        tutaj jest testowy napis
                        .setPositiveButton("Ok", (dialog, id) -> {

                        });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                return true;
            });

            iconImageView = itemView.findViewById(R.id.sensor_icon);
            nameTextView = itemView.findViewById(R.id.sensor_name);
        }

        public void bind(Sensor sensor) {
            this.sensor = sensor;
            iconImageView.setImageResource(R.drawable.ic_sensor);
            nameTextView.setText(sensor.getName());
            if(sensor.getType() == Sensor.TYPE_PRESSURE || sensor.getType() == Sensor.TYPE_LIGHT) {
                nameTextView.setBackgroundColor(Color.YELLOW);
            }
            else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                nameTextView.setBackgroundColor(Color.GREEN);
            } else {
                nameTextView.setBackgroundColor(Color.LTGRAY);
            }
        }

        @Override
        public void onClick(View v) {
            if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                Intent intent = new Intent(SensorActivity.this, LocationActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SensorActivity.this, SensorDetailsActivity.class);
                intent.putExtra("sensor_index", sensorList.indexOf(sensor));
                startActivity(intent);
            }
        }
    }

//    SENSOR HOLDER END -------------------------------------------------------------------------------------------------------------

//    SENSOR ADAPTER START -------------------------------------------------------------------------------------------------------------
    private class SensorAdapter extends RecyclerView.Adapter<SensorHolder> {
        private List<Sensor> sensors;
        public SensorAdapter(List<Sensor> sensors) {
            this.sensors = sensors;
        }

        @NonNull
        @Override
        public SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(SensorActivity.this);
            return new SensorHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SensorHolder holder, int position) {
            Sensor sensor = sensors.get(position);
            holder.bind(sensor);
        }

        @Override
        public int getItemCount() {
            return sensors.size();
        }
    }
//    SENSOR ADAPTER END -------------------------------------------------------------------------------------------------------------


}