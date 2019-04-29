package com.example.carpooldummyapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Recycler_View_Adapter extends RecyclerView.Adapter<Recycler_View_Adapter.MyViewHolder> implements Filterable {


    Context context;

    public Recycler_View_Adapter(List<Ride> list, Context context) {
        this.list = list;
        List_Full = new ArrayList<>(list);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(list.get(position).getDeparture_Location());
        holder.description.setText(list.get(position).getArrival_Location());
        holder.imageView.setImageResource(R.drawable.bmw);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookingRide.class);
                intent.putExtra(PassengerDash.DEPARTURE_LOCATION, list.get(position).getDeparture_Location());
                intent.putExtra(PassengerDash.ARRIVAL_LOCATION, list.get(position).getArrival_Location());
                intent.putExtra(PassengerDash.DEPARTURE_TIME, list.get(position).getDeparture_Time());
                intent.putExtra(PassengerDash.ARRIVAL_TIME, list.get(position).getArrival_Time());
                intent.putExtra("position", (list.get(position)).get_ID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public Filter getFilter() {
        return filteredList;
    }

    List<Ride> list;
    List<Ride> List_Full;
    private Filter filteredList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Ride> filterList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filterList.addAll(List_Full);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Ride r : List_Full){
                    if(r.toString().toLowerCase().contains(filterPattern)){
                        filterList.add(r);
                    }
                }
            }
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView description;
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
