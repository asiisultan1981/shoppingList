package com.android.shoppinglist.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities =  {Category.class, Items.class}, version = 1,exportSchema = false)
public abstract class  AppDatabase extends RoomDatabase {

        public static final String DATABASE_NAME = "AppDB";
        public static final int NUM_OF_THREADS = 4;
        public abstract  ShoppingListDao shoppingListDao();
        private static volatile AppDatabase INSTANCE;

        public static final ExecutorService databaseWriterExecutor
                = Executors.newFixedThreadPool(NUM_OF_THREADS);

        public static final Callback sRoomDatabaseCallback =
                new Callback() {

                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        databaseWriterExecutor.execute(() -> {

                            // invoke dao
                            ShoppingListDao shoppingListDao = INSTANCE.shoppingListDao();

                        });
                    }

                };

        public static AppDatabase getDBinstance(final Context context) {
            if (INSTANCE == null) {
                synchronized (AppDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, DATABASE_NAME)
//                                .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

    }
