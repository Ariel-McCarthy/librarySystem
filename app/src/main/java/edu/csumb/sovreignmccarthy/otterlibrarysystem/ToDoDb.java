package edu.csumb.sovreignmccarthy.otterlibrarysystem;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Task.class}, version = 3, exportSchema = false)
public abstract class ToDoDb extends RoomDatabase
{
    private static ToDoDb sInstance;
    public abstract TaskDao task();

    public static synchronized ToDoDb getInstance(Context context)
    {
        if(sInstance == null)
        {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), ToDoDb.class,"todo.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }
}