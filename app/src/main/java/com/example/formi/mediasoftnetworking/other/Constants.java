package com.example.formi.mediasoftnetworking.other;

import android.provider.BaseColumns;

public class Constants {

    public static final String LOG_TAG = "mDb";

    public static class DataBase implements BaseColumns {
        public static final String DATABASE_NAME = "vk";
        public static final int DATABASE_VERSION = 1;

        public static class TableUsers {
            public static final String TABLE_NAME = "users";

            public static final String COLUMN_ID = "userID";
            public static final String COLUMN_FIRST_NAME = "first_name";
            public static final String COLUMN_LAST_NAME = "last_name";
            public static final String COLUMN_IMAGE_URL = "image_url";
        }

        public static class Queries{
            public static final String CREATE_TABLE = "CREATE TABLE " + DataBase.TableUsers.TABLE_NAME
                    + "(" + DataBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DataBase.TableUsers.COLUMN_ID + " INTEGER not null, "
                    + DataBase.TableUsers.COLUMN_FIRST_NAME + " TEXT not null, "
                    + DataBase.TableUsers.COLUMN_LAST_NAME + " TEXT not null, "
                    + DataBase.TableUsers.COLUMN_IMAGE_URL + " TEXT not null" + ");";

            public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DataBase.TableUsers.TABLE_NAME;
            public static final String SELECT_ALL_FROM_DB = "SELECT * FROM " + TableUsers.TABLE_NAME;
        }
    }

    public static class VkApiConstants{
        public static final String BASE_URL = "https://api.vk.com";
        public static final String SERVER_ACCESS_TOKEN = "cfecf67ecfecf67ecfecf67e7bcf884c04ccfeccfecf67e94c81aea5f009109d7e7ae22";
        public static final String USER_ACCESS_TOKEN = "2cb3b8a8b7eb18c6250d46cadce5c3fc76620fc6d7b1215f8f9d4c1558cd5f3d241bcd55e30cd2d4f542e";
        public static final String FIELDS = "photo_200"; // Можно добавить поля
        public static final String VERSION = "5.8";
    }

}