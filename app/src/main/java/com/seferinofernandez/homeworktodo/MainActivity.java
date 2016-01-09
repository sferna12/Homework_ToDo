package com.seferinofernandez.homeworktodo;
import android.app.DialogFragment;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements HomeworkDialogFragment.DialogListener {

    AssignList userList;
    private ListView listView;
    AssignAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readItems();
        itemsAdapter = new AssignAdapter(this, userList.getMyList());
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);
        itemsAdapter.notifyDataSetChanged();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
                // When clicked, show a remove button
                userList.getMyList().remove(position);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }

        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onAddItem(View v) {
        //Display the dialog when button pressed
        DialogFragment dialog = new HomeworkDialogFragment();
        dialog.show(getFragmentManager(), "myDialog");
    }
    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button, add and update listview.
        String userHomework = ((EditText)dialog.getDialog().findViewById(R.id.tvName)).getText().toString();
        String userDueDate = ((EditText)dialog.getDialog().findViewById(R.id.tvDueDate)).getText().toString();
        String userClassName = ((EditText)dialog.getDialog().findViewById(R.id.tvClassName)).getText().toString();
        if(!userHomework.isEmpty() || !userDueDate.isEmpty() || !userClassName.isEmpty()) {
            userList.add(new Assignment(userHomework, userDueDate, userClassName));
        }
        dialog.dismiss();
        itemsAdapter.notifyDataSetChanged();
        writeItems();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        dialog.dismiss();
    }

    //Reads file or creates one if file not found
    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            userList = new AssignList(new ArrayList(FileUtils.readLines(todoFile)));
        } catch (IOException e) {
            userList = new AssignList();
        }
    }

    //Writes item to file
    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, userList.getMyList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
