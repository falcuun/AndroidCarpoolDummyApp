package com.example.carpooldummyapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class Recycler_View_Adapter extends RecyclerView.Adapter<Recycler_View_Adapter.MyViewHolder> implements Filterable {

    private Context context;

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

        holder.departure_location.setText(list.get(position).getDeparture_Location());
        holder.departure_time.setText(list.get(position).getDeparture_Time());

        holder.arrival_location.setText(list.get(position).getArrival_Location());
        holder.arrival_time.setText(list.get(position).getArrival_Time());

        holder.driver_name.setText(list.get(position).getDriver().getName());
        holder.display_rating.setRating(list.get(position).getDriver().getRating());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 3;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmw, options);
        holder.imageView.setImageBitmap(bitmap);

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

    private List<Ride> list;
    private List<Ride> List_Full;
    private Filter filteredList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Ride> filterList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filterList.addAll(List_Full);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Ride r : List_Full) {
                    if (r.toString().toLowerCase().contains(filterPattern)) {
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
            list.addAll((List) results.values);
            if (list.size() == 0) {
                Toast.makeText(context, "There is no result matching that search", Toast.LENGTH_SHORT).show();
            }
            notifyDataSetChanged();
        }
    };

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView departure_location, arrival_location, departure_time, arrival_time, driver_name;
        RatingBar display_rating;
        ProgressBar progressbar;
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            departure_location = itemView.findViewById(R.id.departing_location);
            arrival_location = itemView.findViewById(R.id.arriving_location);
            departure_time = itemView.findViewById(R.id.departing_time);
            arrival_time = itemView.findViewById(R.id.arriving_time);
            driver_name = itemView.findViewById(R.id.name_field);
            display_rating = itemView.findViewById(R.id.display_rating);
            imageView = itemView.findViewById(R.id.imageView);
            //progressbar = itemView.findViewById(R.id.progressbar);
            //progressbar.setProgress(progressbar.getMax());
        }
    }

    private static class LongOperation extends AsyncTask<ImageView, Void, String> {


        @Override
        protected String doInBackground(ImageView... imageViews) {

            imageViews[0].setImageResource(R.drawable.bmw);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        }


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}



