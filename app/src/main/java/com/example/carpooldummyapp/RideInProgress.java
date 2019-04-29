package com.example.carpooldummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RideInProgress extends AppCompatActivity {

    DriverAccount driver;
    Ride Selected_Ride;
    TextView rideProgress, rateRide;
    Button ratePlus, rateMinus;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_in_progress);
        pb = findViewById(R.id.loading_drive);
        ProgressBarAnimation anim = new ProgressBarAnimation(pb, 0, 500);

        int position = getIntent().getIntExtra("position", 0);

        for (Ride v : PassengerDash.All_Rides_Array){
            if(v.get_ID() == position){
                Selected_Ride = v;
                driver = v.getDriver();
            }
        }

        Toast.makeText(this, "" + driver.getAccount_type().name(), Toast.LENGTH_LONG).show();

        rideProgress = findViewById(R.id.rideProgressView);
        rateRide = findViewById(R.id.rateRideView);

        ratePlus = findViewById(R.id.RatePlus);
        rateMinus = findViewById(R.id.RateMinus);

        rateRide.setVisibility(View.INVISIBLE);
        ratePlus.setVisibility(View.INVISIBLE);
        rateMinus.setVisibility(View.INVISIBLE);

        ratePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RideInProgress.this, "You Rate this Ride Good", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        rateMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RideInProgress.this, "You Rate this Ride Bad", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        anim.setDuration(5000);
        pb.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rateRide.setVisibility(View.VISIBLE);
                ratePlus.setVisibility(View.VISIBLE);
                rateMinus.setVisibility(View.VISIBLE);

                rideProgress.setVisibility(View.INVISIBLE);
                pb.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float to;

        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }

    }
}
