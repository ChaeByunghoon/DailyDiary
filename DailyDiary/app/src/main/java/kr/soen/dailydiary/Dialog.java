package kr.soen.dailydiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Dialog extends Activity implements View.OnClickListener {

    String date;
    String TAG = "MYAPP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button writeButton = (Button)findViewById(R.id.button3);
        Button readButton = (Button)findViewById(R.id.button4);

        writeButton.setOnClickListener(this);
        readButton.setOnClickListener(this);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        Log.d(TAG,"data data = "+date);

    }

    public void onClick(View v){

        switch (v.getId()){

            case R.id.button3:
                Intent writeActivity = new Intent(Dialog.this, WriteActivity.class);
                writeActivity.putExtra("date",date);
                startActivity(writeActivity);
                break;
            case R.id.button4://ReadButton case
                Intent readDiary = new Intent(Dialog.this, ReadDiary.class);
                readDiary.putExtra("date",date);
                startActivity(readDiary);
                break;

        }

    }



}
