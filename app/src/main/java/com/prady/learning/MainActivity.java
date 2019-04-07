package com.prady.learning;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    private int  minutes, seconds,milliseconds;
    private long millisecondTime, timeBuff, startTime, updateTime = 0L;
    private Button mStartbutton, mStopButton, mLapButton;
    private TextView mMilliSecondsTextView, mSecondsTextView, mMinutesTextView;
    private FragmentManager fragmentManager;
    private LapListFragment lapListFragment;
    Handler handler;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int)(updateTime/1000);
            minutes = seconds/60;
            seconds = seconds%60;
            milliseconds = (int)(updateTime%1000);
            mMilliSecondsTextView.setText(""+milliseconds);
            mSecondsTextView.setText(""+seconds);
            mMinutesTextView.setText(""+minutes);
            Log.d("HAN","RUNNNING");
            handler.postDelayed(this,0);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
         lapListFragment = (LapListFragment)fragmentManager.findFragmentById(R.id.lapListFragment);
        if(lapListFragment == null)
        {
            lapListFragment = new LapListFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.lapListFragment,lapListFragment)
                    .commit();
        }

        mStartbutton = findViewById(R.id.startButton);
        mStartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        mStopButton =  findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        mLapButton = findViewById(R.id.lapButton);
        mLapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLap();
            }
        });

        mMilliSecondsTextView = findViewById(R.id.milliSecondsTextView);
        mSecondsTextView = findViewById(R.id.secondsTextView);
        mMinutesTextView = findViewById(R.id.minutesTextView);

         handler = new Handler();

    }

    private void startTimer()
    {
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable,0);
    }

    private void stopTimer()
    {
        timeBuff += millisecondTime;
        handler.removeCallbacks(runnable);

    }

    private void addLap()
    {
        lapListFragment.addLap(minutes,seconds,milliseconds);
        Toast.makeText(this,"Lapped time",Toast.LENGTH_SHORT).show();
    }

}