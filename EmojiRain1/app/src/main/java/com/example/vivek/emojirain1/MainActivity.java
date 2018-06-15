package com.example.vivek.emojirain1;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.plattysoft.leonids.ParticleSystem;


//TODO reduce the density of emojis
//TODO add functionality of Direction of rain in rainEmoji method so that emoji can flow from any direction
//TODO Rain emojis in random size
public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {

    private int flag;
    Button happy;
    Button sad;
    Button love;
    Button angry;
    Button enthusiastic;
    Button birthday;
    private MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        happy = findViewById(R.id.happy);
        sad = findViewById(R.id.sad);
        love = findViewById(R.id.love);
        enthusiastic = findViewById(R.id.enthusiastic);
        birthday = findViewById(R.id.birthday);
        angry = findViewById(R.id.angry);

        happy.setOnClickListener(this);
        sad.setOnClickListener(this);
        love.setOnClickListener(this);
        angry.setOnClickListener(this);
        enthusiastic.setOnClickListener(this);
        birthday.setOnClickListener(this);

    }

    private void rainEmoji(int[] theme, long timeToLife, int maxParticles) {


        for (int emojiID : theme) {
            new ParticleSystem(this, maxParticles, emojiID, timeToLife)
                    .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 180)
                    .setScaleRange(0.5f, 1.7f)
                    .setAcceleration(0.00005f, 90)
                    .emit(100, 50, 20, 3000);

        }

    }


    @Override
    public void onClick(View view) {
        Thread rain = new Thread(this);
        flag = 0;
        switch (view.getId()) {
            case R.id.happy: {
                flag = 1;
                rain.start();

                rainEmoji(Constants.HAPPY_THEME, 10000, 60);


                break;
            }
            case R.id.sad: {

                flag = 2;

                rain.start();
                rainEmoji(Constants.SAD_THEME, 10000, 60);

                break;
            }
            case R.id.love: {
                flag = 3;
                rain.start();


                rainEmoji(Constants.LOVE_THEME, 10000, 40);


                break;
            }
            case R.id.angry: {
                flag = 4;
                rain.start();
                rainEmoji(Constants.ANGRY_THEME, 10000, 40);
                break;
            }
            case R.id.enthusiastic: {
                flag = 5;
                rain.start();


                rainEmoji(Constants.ENTHUSIASTIC_THEME, 10000, 40);

                break;
            }
            case R.id.birthday: {
                flag = 6;
                rain.start();

                rainEmoji(Constants.BIRTHDAY_THEME, 10000, 40);

                break;
            }

        }
    }


    @Override
    public void run() {

        if (flag == 1)
            mp = MediaPlayer.create(this, R.raw.person_cheering_jett_rifkin);
        else if (flag == 2)
            mp = MediaPlayer.create(this, R.raw.love_theme);
        else if (flag == 3)
            mp = MediaPlayer.create(this, R.raw.love_theme);
        else if (flag == 4)
            mp = MediaPlayer.create(this, R.raw.love_theme);
        else if (flag == 5)
            mp = MediaPlayer.create(this, R.raw.curb_your_enthusiasm);
        else
            mp = MediaPlayer.create(this, R.raw.happy_birthday_to_you_sound);

        mp.start();


        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mp.stop();


    }


}



