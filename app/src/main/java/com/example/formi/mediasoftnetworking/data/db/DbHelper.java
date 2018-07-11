package com.example.formi.mediasoftnetworking.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.formi.mediasoftnetworking.domain.model.Response;
import com.example.formi.mediasoftnetworking.domain.model.User;
import com.example.formi.mediasoftnetworking.other.Constants;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Constants.DataBase.DATABASE_NAME, null, Constants.DataBase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.DataBase.Queries.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DataBase.Queries.DROP_TABLE);
        this.onCreate(db);
    }

    public void addToDataBase(User user){
        if(user.getUser().get(0).getFirstName() == null
                || user.getUser().get(0).getLastName() == null
                || user.getUser().get(0).getImgURL() == null){
            return;
        }

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(Constants.DataBase.Queries.SELECT_ALL_FROM_DB, null);
        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_ID));
                if(user.getUser().get(0).getId() == id){
                    return;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        ContentValues cv = new ContentValues();

        long userID = user.getUser().get(0).getId();
        String userFirstName = user.getUser().get(0).getFirstName();
        String userLastName = user.getUser().get(0).getLastName();
        String userImgURL = user.getUser().get(0).getImgURL();

        cv.put(Constants.DataBase.TableUsers.COLUMN_ID, userID);
        cv.put(Constants.DataBase.TableUsers.COLUMN_FIRST_NAME, userFirstName);
        cv.put(Constants.DataBase.TableUsers.COLUMN_LAST_NAME, userLastName);
        cv.put(Constants.DataBase.TableUsers.COLUMN_IMAGE_URL, userImgURL);
        db.insert(Constants.DataBase.TableUsers.TABLE_NAME, null, cv);

        db.close();
    }

    public void deleteFromDataBase(long userID){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constants.DataBase.TableUsers.TABLE_NAME, Constants.DataBase.TableUsers.COLUMN_ID + "=" + userID, null);

        db.close();
    }

    public List<User> getUserList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> users = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.DataBase.TableUsers.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do {
                User user = new User();

                long id = cursor.getLong(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_LAST_NAME));
                String imgURL = cursor.getString(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_IMAGE_URL));

                Response response = new Response();
                response.setId(id);
                response.setFirstName(firstName);
                response.setLastName(lastName);
                response.setImgURL(imgURL);

                List<Response> userList = new ArrayList<>();
                userList.add(response);

                user.setUser(userList);
                users.add(user);
            }while(cursor.moveToNext());
        }

        if(!cursor.isClosed()){
            cursor.close();
        }
        db.close();
        return users;
    }

    public void showDataToLogs(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.DataBase.TableUsers.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_LAST_NAME));
                String imgURL = cursor.getString(cursor.getColumnIndex(Constants.DataBase.TableUsers.COLUMN_IMAGE_URL));

                Log.i(Constants.LOG_TAG, " id - " + id + "\n firstName - " + firstName + "\n lastName - " + lastName + "\n imgURL - " + imgURL + "\n\n\n");
            }while(cursor.moveToNext());
        }

        if(!cursor.isClosed()){
            cursor.close();
        }
        db.close();
    }
}
