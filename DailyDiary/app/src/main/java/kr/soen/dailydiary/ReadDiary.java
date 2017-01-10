package kr.soen.dailydiary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReadDiary extends AppCompatActivity {

    TextView readDate;
    TextView showTitle;
    TextView showDiary;
    String date;
    MyApplication m_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_diary);

        m_app = (MyApplication)getApplicationContext();

        showTitle = (TextView)findViewById(R.id.showTitle);
        showDiary = (TextView)findViewById(R.id.showDiary);

        Intent intent = getIntent();
        date = intent.getStringExtra("date").toString();

        readDate = (TextView)findViewById(R.id.readDate);
        readDate.setText(date);

        m_app.dbController.open();
        Cursor cur = m_app.dbController.fetchBook(date);//커서 리턴
        //KEY_DATE,KEY_TITLE,KEY_DIARY   0 =date , 1 title 2diary
        if(cur.getCount() == 0){

        }
        else {
            //다이어리 setText
            showTitle.setText(cur.getString(1));
            showDiary.setText(cur.getString(2));
        }
        m_app.dbController.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        switch (item.getItemId()){
            case R.id.modifyButton:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
