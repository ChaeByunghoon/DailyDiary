package kr.soen.dailydiary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class WriteDiaryActivity extends AppCompatActivity {

    private EditText title;
    private EditText diary;
    String date;
    private TextView text1;
    MyApplication m_app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        m_app = (MyApplication)getApplicationContext();

        text1 = (TextView)findViewById(R.id.text1);
        Calendar cal = Calendar.getInstance();
        text1.setText(cal.get(Calendar.YEAR) + "년"+(cal.get(Calendar.MONTH)+1) +"월" + cal.get(Calendar.DAY_OF_MONTH)+"일"+convertIntToDay(cal.get(Calendar.DAY_OF_WEEK)));
        date = text1.getText().toString();
        //date 스트링으로 만든 후 ~~
        title = (EditText)findViewById(R.id.editTitle);
        diary = (EditText)findViewById(R.id.editDiary);

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
                String date = text1.getText().toString();
                //DB에 쓰기

                Cursor cursor = m_app.dbController.fetchBook(date);
                if(cursor.getCount() == 0){
                    m_app.dbController.createDiary(date,diaryTitle,writing);
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();

                }
                else {
                    m_app.dbController.updateBook(date, diaryTitle, writing);
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                    //Log.d("BYUNG","diary = " +cursor.getString(1));
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
