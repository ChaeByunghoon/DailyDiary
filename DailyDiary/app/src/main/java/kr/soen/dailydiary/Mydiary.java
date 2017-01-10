package kr.soen.dailydiary;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;


public class Mydiary extends Activity {

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiary);


        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year,int month,int dayOfMonth){

                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(year,month,dayOfMonth);
                int dayOfWeek = calendar1.get(calendar1.DAY_OF_WEEK);
                date = year+"년"+(month+1)+"월"+dayOfMonth+"일"+convertIntToDay(dayOfWeek);
              Intent dialog = new Intent(Mydiary.this, Dialog.class);
                dialog.putExtra("date",date);
                startActivity(dialog);
            }
        });
    }

    private String convertIntToDay(int i) {
        String day = "";
        switch (i) {

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
