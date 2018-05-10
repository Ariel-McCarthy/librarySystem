package edu.csumb.sovreignmccarthy.otterlibrarysystem;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task
{
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name="title")
    private String mTitle;

    public int getId()
    {
        return mId;
    }

    public void setId(int id)
    {
        mId = id;
    }

    public Task(String title)
    {
        mTitle = title;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }
    @Override
    public String toString()
    {
        return mTitle;
    }
}
