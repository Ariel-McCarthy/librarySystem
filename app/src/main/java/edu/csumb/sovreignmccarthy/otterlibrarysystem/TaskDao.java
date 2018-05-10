package edu.csumb.sovreignmccarthy.otterlibrarysystem;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TaskDao
{
    @Insert
    public void addTask(Task task);

//    @Insert
//    public void newUser(User user);

//    @Insert
//    public void newBook(Book book);

    @Query("SELECT * FROM tasks")
    public List<Task> getAllTasks();

//    @Query("SELECT * FROM books")
//    public List<Book> getAllBooks();

    @Query("DELETE FROM tasks WHERE title = :taskString")
    public void deleteByTitle(String taskString);
}
