package com.example.sheky.service;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.example.sheky.database.AppDatabase;

class DatabaseService {
    private static final DatabaseService instance = new DatabaseService();
    private static final String databaseName = "MOONSDATABASE";

    private void DatabaseService() {
    }

    public static DatabaseService getInstance() {
        return instance;
    }

    public AppDatabase getApplicationDatabase(Context context) {
         return Room.databaseBuilder(context,
                 AppDatabase.class, databaseName)
                 .fallbackToDestructiveMigration()
                 //.addMigrations(FROM_1_TO_2)
                 .build();
    }

    static final Migration FROM_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            //database.execSQL("ALTER TABLE Repo ADD COLUMN createdAt TEXT");
        }
    };
}
