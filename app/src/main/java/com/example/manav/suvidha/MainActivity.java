package com.example.manav.suvidha;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;


public class MainActivity extends ActionBarActivity {

    //Defining global variables here
    SuvidhaApplication svdApplication;
    //ArrayAdapter<Doctor> lvArrayAdapter;
    public static final String CLASS_NAME="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Use application class to maintain global state. */
        svdApplication = (SuvidhaApplication) getApplication();
        //itemList = blApplication.getItemList();

        //Code to add new doctor to the database
        /*
        Doctor doc = new Doctor();
        doc.setAddress("LLR A-111, IIT Kharagpur");
        doc.setName("Prithvish Mukerjee");
        doc.setArea("Genitals");
        doc.save().continueWith(new Continuation<IBMDataObject, Void>() {

            @Override
            public Void then(Task<IBMDataObject> task) throws Exception {
                if (task.isFaulted()) {
                    // Handle errors
                    Log.e(CLASS_NAME,"Exception: "+task.getError().getMessage());
                    return null;
                } else {
                    Doctor myDoc = (Doctor) task.getResult();
                    Log.e(CLASS_NAME,myDoc.getAddress());
                    Log.e(CLASS_NAME,myDoc.getName());
                    Log.e(CLASS_NAME,myDoc.getArea());

                    // Do more work
                }
                return null;
            }
        });
        */

        try {
            //To retrieve from the database
            IBMQuery<Doctor> query = IBMQuery.queryForClass(Doctor.class);

            query.find().continueWith(new Continuation<List<Doctor>, Void>() {

                @Override
                public Void then(Task<List<Doctor>> task) throws Exception {
                    if (task.isFaulted()) {
                        // Handle errors
                    } else {
                        // do more work
                        List<Doctor> objects = task.getResult();
                        for(Doctor doc:objects){
                            Log.e(CLASS_NAME,doc.getName());
                            Log.e(CLASS_NAME,doc.getArea());
                            Log.e(CLASS_NAME,doc.getAddress());
                        }
                    }
                    return null;
                }
            }, Task.UI_THREAD_EXECUTOR);
        } catch (IBMDataException error) {
            Log.e(CLASS_NAME,"Exception : " +error.getMessage());
        }
    }

    /**
     * Called on done and will add item to list.
     *
     * @param  v edittext View to get item from.
     * @throws com.ibm.mobile.services.data.IBMDataException
     */

    /*
    public void createItem(View v) {
        EditText itemToAdd = (EditText) findViewById(R.id.itemToAdd);
        String toAdd = itemToAdd.getText().toString();
        Log.e(CLASS_NAME, "Item to add : " + toAdd);
        Item item = new Item();
        if (!toAdd.equals("")) {
            item.setName(toAdd);
            // Use the IBMDataObject to create and persist the Item object.
            item.save().continueWith(new Continuation<IBMDataObject, Void>() {

                @Override
                public Void then(Task<IBMDataObject> task) throws Exception {
                    // Log if the save was cancelled.
                    if (task.isCancelled()){
                        Log.e(CLASS_NAME, "Exception : Task " + task.toString() + " was cancelled.");
                    }
                    // Log error message, if the save task fails.
                    else if (task.isFaulted()) {
                        Log.e(CLASS_NAME, "Exception : " + task.getError().getMessage());
                    }

                    // If the result succeeds, load the list.
                    else {
                        listItems();
                    }
                    return null;
                }

            });

            // Set text field back to empty after item is added.
            itemToAdd.setText("");
        }
    }*/

}
