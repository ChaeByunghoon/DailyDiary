package kr.soen.dailydiary;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * Created by 채병훈 on 2016-10-03.
 */

public class DbController {
    public static final String KEY_DATE = "date";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DIARY = "diary";

    private static final String DATABASE_NAME ="diary.db";
    private static final String DATABASE_TABLE ="data";
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_CREATE = "create table data (date text not null, title text not null, diary text not null);";

    private final Context mContext;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        }
         public void onCreate(SQLiteDatabase db){
         db.execSQL(DATABASE_CREATE);
         }
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

             db.execSQL("DROP TABLE IF EXISTS data");
             onCreate(db);

         }

    }
    public DbController(Context context){
        this.mContext = context;  //생성자
    }

    public DbController open() throws SQLException{
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        mDbHelper.close();
    }

    public long createDiary(String date, String title, String diary){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DIARY, diary);
        return mDb.insert(DATABASE_TABLE, null, initialValues);

      /*  Cursor cur = fetchBook(date);
        if(cur.getCount() == 0){
            mDb.rawQuery("INSERT INTO "+DATABASE_TABLE+"("+KEY_DATE+","+KEY_TITLE+","+KEY_DIARY+") VALUES(\'"+date+"\',\'"+title+"\',\'"+diary+"\')",null);
        }else{
            mDb.rawQuery("UPDATE "+DATABASE_TABLE + " set "+KEY_TITLE + " = \'"+title + "\' , "+KEY_DIARY+" = \'"+diary+"\' WHERE "+KEY_DATE+" = \'"+date+"\'",null);
        }*/
        //return 0;

    }
    public boolean deleteBook(String date){
        return mDb.delete(DATABASE_TABLE,"date",new String[] {date}) > 0;
    }
    public Cursor fetchAllBook(){//모든 레코드 반환
        return mDb.query(DATABASE_TABLE, new String[]{KEY_DATE,KEY_TITLE,KEY_DIARY},null,null,null,null,null);
    }
    public Cursor fetchBook(String date) throws SQLException{
        //Cursor mCursor = mDb.query(true, DATABASE_TABLE,new String[]{KEY_DATE,KEY_TITLE,KEY_DIARY}, KEY_DATE + "=" +date,null,null,null,null,null);
        Cursor mCursor = mDb.rawQuery("SELECT * FROM \'"+DATABASE_TABLE +"\' WHERE "+KEY_DATE + " = \'"+date+"\'",null);
       mCursor.moveToFirst();

        // if(mCursor !=null)
         //   mCursor.moveToFirst();
        return mCursor;
    }
    public boolean updateBook(String date, String title, String diary){
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,title);
        values.put(KEY_DIARY,diary);

        return mDb.update(DATABASE_TABLE, values,KEY_DATE +" = \'"+date+"\'",null) > 0;
    }


}
