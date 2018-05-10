package edu.csumb.sovreignmccarthy.otterlibrarysystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView mTaskListView;
    private ArrayAdapter<Task> mAdapter;
//    private List<Task> mDummyTasks;
    private ToDoDb db;
    private List<Task> mRealTasks;
    //Task otherTask = new Task("sleep");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = ToDoDb.getInstance(this);
        mRealTasks = db.task().getAllTasks();

//        db.task().addTask(otherTask);
//        db.task().addTask(new Task("sleep"));

//        mDummyTasks = new ArrayList<>();
//        mDummyTasks.add(new Task("wake up"));
//        mDummyTasks.add(new Task("go to school"));
//        mDummyTasks.add(new Task("do homework"));

        mTaskListView = findViewById(R.id.list_todo);

        mAdapter = new ArrayAdapter<>(this, R.layout.item_todo, R.id.task_title, mRealTasks);

        mTaskListView.setAdapter(mAdapter);

        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.d("Main Activity", "Add new task");

        View mView = getLayoutInflater().inflate(R.layout.task_dialog, null);

        final EditText mAddDialog = mView.findViewById(R.id.new_task);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add new task")
                .setMessage("What's next?")
                .setView(mView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        String task = String.valueOf(mAddDialog.getText());

                        // mDummyTasks.add(new Task(task));
                        db.task().addTask(new Task(task));
                        mAdapter.notifyDataSetChanged(); //

                        Log.d("Dialog interface", task); //
                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();

        return true;
    }

    private void updateUI()
    {
        mRealTasks/*or mDummyTasks*/ = db.task().getAllTasks();

        if(mAdapter == null)
        {
            mAdapter = new ArrayAdapter<Task>(this, R.layout.item_todo, R.id.task_title, mRealTasks/*or mDummyTasks*/);
            mTaskListView.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.clear();
            mAdapter.addAll(mRealTasks/*or mDummyTasks*/);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void deleteTask(View view)
    {
        View parent = (View) view.getParent();
        TextView taskTextView = parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        db.task().deleteByTitle(task);
        updateUI();
    }

    public void createUser(View view)
    {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    public void holdBook(View view)
    {
        Intent intent = new Intent(this, Book.class);
        startActivity(intent);
    }


    public void cancelBook(View view)
    {
        Intent intent = new Intent(this, Book.class);
        startActivity(intent);
    }

    public void manageSystem(View view)
    {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
