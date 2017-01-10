package kr.soen.dailydiary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class WriteActivity extends AppCompatActivity {

    private EditText title;
    private EditText diary;
    String date;
    private TextView text1;
    MyApplication m_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        m_app = (MyApplication)getApplicationContext();

        Intent intent = getIntent();
        date = intent.getStringExtra("date").toString();

        text1 = (TextView)findViewById(R.id.write_text1);
        text1.setText(date);

        //date 스트링으로 만든 후 ~~
        title = (EditText)findViewById(R.id.write_editTitle);
        diary = (EditText)findViewById(R.id.write_editDiary);

        m_app.dbController.open();
        Cursor cursor = m_app.dbController.fetchBook(date);
        if(cursor.getCount() == 0){

        }
        else {
            Log.d("BYUNG","colum count =" +cursor.getColumnCount());
            Log.d("BYUNG","diary = " +cursor.getString(2));
            title.setText(cursor.getString(1));
            diary.setText(cursor.getString(2));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        switch (item.getItemId()){
            case R.id.store_btn://저장 버튼을 눌렀을때
                String diaryTitle = title.getText().toString();
                Log.d("lolo",title.getText().toString());
                String writing = diary.getText().toString();

                //DB에 쓰기

                Cursor cursor = m_app.dbController.fetchBook(date);
                if(cursor.getCount() == 0){
                    m_app.dbController.createDiary(date,diaryTitle,writing);
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();

                }
                else {
                    m_app.dbController.updateBook(date, diaryTitle, writing);
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();

                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private String convertIntToDay(int i){
        String day = "";
        switch (i){

            case 1:
                day = "일요일";
                break;
            case 2:
                day = "월요일";
                break;
            case 3:
                day = "화요일";
                break;
            case 4:
                day = "수요일";
                break;
            case 5:
                day = "목요일";
                break;
            case 6:
                day = "금요일";
                break;
            case 7:
                day = "토요일";
                break;

        }
        return day;
    }


}

