package com.example.carpooldummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.*;

public class RideInProgress extends AppCompatActivity {

    DriverAccount driver;
    Ride Selected_Ride;
    RatingBar rateBar;
    TextView rideProgress, rateRide, leaveComment;
    EditText Input_Comment;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_in_progress);
        init();
        driver.Reduce_Seats();
        rateBar.setStepSize(0.1f);
        ProgressBarAnimation anim = new ProgressBarAnimation(pb, 0, 500);

        int position = getIntent().getIntExtra("position", 0);

        for (Ride v : PassengerDash.All_Rides_Array){
            if(v.get_ID() == position){
                Selected_Ride = v;
                driver = v.getDriver();
            }
        }
        rateBar.setRating(driver.getRating());
        Toast.makeText(this, "" + driver.getName(), Toast.LENGTH_LONG).show();

        rateBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                driver.addRating(rating);
                Toast.makeText(RideInProgress.this, "" + driver.getRating(), Toast.LENGTH_LONG).show();

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
                rateBar.setVisibility(View.VISIBLE);
                leaveComment.setVisibility(View.VISIBLE);
                Input_Comment.setVisibility(View.VISIBLE);
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

        ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
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

    private void init(){
        pb = findViewById(R.id.loading_drive);
        rateBar = findViewById(R.id.rateBar);
        leaveComment = findViewById(R.id.leave_comment);
        Input_Comment = findViewById(R.id.input_comment);
        rideProgress = findViewById(R.id.rideProgressView);
        rateRide = findViewById(R.id.rateRideView);

        leaveComment.setVisibility(View.INVISIBLE);
        Input_Comment.setVisibility(View.INVISIBLE);
        rateRide.setVisibility(View.INVISIBLE);
        rateBar.setVisibility(View.INVISIBLE);
    }
}
