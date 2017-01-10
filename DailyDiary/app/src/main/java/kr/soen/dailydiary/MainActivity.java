package kr.soen.dailydiary;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,IntroActivity.class));

        Button writeDailyDiary = (Button)findViewById(R.id.button);
        writeDailyDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("onClick","CallWriteDiaryActivity");
                Intent intentWriteDiaryActivity = new Intent(MainActivity.this,WriteDiaryActivity.class);
                startActivity(intentWriteDiaryActivity);
            }
        });

        Button myDiary = (Button)findViewById(R.id.button2);
        myDiary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("onClick", "CallMydiary");
                Intent intentMydiary = new Intent(MainActivity.this, Mydiary.class);
                startActivity(intentMydiary);
            }
            });

        }


    }



