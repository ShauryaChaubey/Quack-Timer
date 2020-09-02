package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Boolean countStarter = false;
    Button button;
    CountDownTimer countDownTimer;

    public void reset()
    {
        button.setText("Start!");
        textView.setText("00:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        countStarter = false;
    }

    public void startTimer(View view) {
        if(countStarter)
        {
            reset();
        }
        else
        {
            countStarter = true;
            seekBar.setEnabled(false);
            button.setText("Stop!");
            countDownTimer = new CountDownTimer(seekBar.getProgress()*900+100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.animals038);
                    mediaPlayer.start();
                    reset();
                }
            }.start();
        }

    }
    public void updateTimer(int secondsLeft){
        int minute = secondsLeft/60;
        int second = secondsLeft - (minute*60);
        String secondString = Integer.toString(second);
        String minuteString = Integer.toString(minute);
        if(second <= 9)
        {
            secondString = "0" + secondString;
        }
        if(minute <= 9)
        {
            minuteString = "0" + minuteString;
        }
        textView.setText(minuteString +":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        seekBar.setMax(3600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
