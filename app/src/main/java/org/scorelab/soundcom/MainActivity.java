package org.scorelab.soundcom;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView startBtn;
    TextView textView4;
    private int seconds=0;
    private boolean startRun;

    TextView imageViewScore2;
    ImageButton imageButtonStart;

    Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageViewScore= (TextView) findViewById(R.id.imageViewScore2);
        imageButtonStart =(ImageButton) findViewById(R.id.imageButtonStart) ;

        Typeface font = Typeface.createFromAsset(getAssets(), "RursusCompactMono.ttf");
        ((TextView)findViewById(R.id.imageViewScore2)).setTypeface(font);

        getSupportActionBar().hide();
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            startRun=savedInstanceState.getBoolean("startRun");
        }

        done =(Button) findViewById(R.id.done);
       // startBtn = (ImageView)findViewById(R.id.imageButtonStop1);
        imageButtonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent svc=new Intent(getBaseContext(), RecordingService.class);
                startService(svc);

                Timer();
                startRun=true;


                finish(); //because I don't want to close the UI after service started
            }
        });
    }
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("startRun", startRun);
    }
    private void Timer(){
     //   final TextView timeView = (TextView)findViewById(R.id.textView4);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                done.setText(time);

                if(startRun){
                    seconds++;
                }

                handler.postDelayed(this, 100);
            }
        });

    }
}
