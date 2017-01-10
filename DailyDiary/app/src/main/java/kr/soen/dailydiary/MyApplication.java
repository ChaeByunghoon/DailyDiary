package kr.soen.dailydiary;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by 채병훈 on 2016-10-05.
 */

public class MyApplication extends Application {

    DbController dbController;

    @Override
    public void onCreate(){
        dbController = new DbController(getApplicationContext());
    }

    @Override
    public void onConfigurationChanged(Configuration newconfig){
        super.onConfigurationChanged(newconfig);
    }


}
