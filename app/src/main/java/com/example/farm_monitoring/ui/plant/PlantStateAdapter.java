package com.example.farm_monitoring.ui.plant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.model.PlantState;

import java.util.ArrayList;
import java.util.List;

public class PlantStateAdapter extends RecyclerView.Adapter<PlantStateAdapter.ViewHolder>{
    TextView plant_name, start_date, end_date;
    List<PlantState> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recyclerview_content_plant, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlantState plantStateData = items.get(position);
        plant_name.setText(plantStateData.getPlant_name());
        start_date.setText(plantStateData.getStart_date());
        end_date.setText(plantStateData.getEnd_date());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            plant_name = itemView.findViewById(R.id.plant_name);
            start_date = itemView.findViewById(R.id.start_date);
            end_date = itemView.findViewById(R.id.end_date);

        }
    }
    public void addData(String plant_name, String start_date, String end_date){
        PlantState item = new PlantState();

        item.setPlant_name(plant_name);
        item.setStart_date(start_date);
        item.setEnd_date(end_date);

        items.add(item);
        notifyDataSetChanged();
    }
}
