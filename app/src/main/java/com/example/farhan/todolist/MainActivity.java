package com.example.farhan.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String messageText;
    DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    int pos;
//    String counter;
//    String excounter;
//    SharedPreferences sp;
//    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos= position;
               // arrayList.remove(position);
               // arrayAdapter.notifyDataSetChanged();
                Intent tent = new Intent();
                tent.setClass(MainActivity.this, AlterFieldClass.class);
                startActivityForResult(tent, Intent_Constants.INTENT_REQUEST_CODE_2);


            }
        });
    }


//        sp = PreferenceManager.getDefaultSharedPreferences(this);
//        edit = sp.edit();

//        try{
//            Scanner sc = new Scanner(openFileInput("ToDo.txt"));
//            while(sc.hasNextLine()){
//                String data = sc.nextLine();
//                arrayAdapter.add(data);
//            }
//            sc.close();
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//    }

//
//    @Override
//    public void onBackPressed(){
////        try{
////            PrintWriter pw = new PrintWriter(openFileOutput("ToDo.txt", Context.MODE_PRIVATE));
////            for(String data : arrayList){
////                pw.println(data);
////            }
////            pw.close();
////        }catch (FileNotFoundException e){
////            e.printStackTrace();
////        }
//  //      finish();
//    }




    public void onClick(View v){

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, EditFieldClass.class);
        startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_CODE);

    }
    public void saveList(View v){
        mDatabaseHelper.clearData();
        for(String i : arrayList){
            boolean fake= mDatabaseHelper.addData(i);
        }
    }

    public void loadList(View v){
        //Log.d(TAG, "loadList: Filling list View with databased table");

        Cursor data = mDatabaseHelper.getData();
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.deferNotifyDataSetChanged();
        while(data.moveToNext()){
            arrayList.add(data.getString(1));
        }
        listView.deferNotifyDataSetChanged();
        listView.setAdapter(arrayAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode==Intent_Constants.INTENT_REQUEST_CODE){
           messageText = data.getStringExtra(Intent_Constants.INTENT_MESSAGE_FIELD);
           arrayList.add(messageText);
           arrayAdapter.notifyDataSetChanged();
       }
        if(resultCode==Intent_Constants.INTENT_REQUEST_CODE_2){
            messageText = data.getStringExtra(Intent_Constants.INTENT_MESSAGE_FIELD_2);
            if(messageText.equals("")){
                arrayList.remove(pos);
            }
            else {
                arrayList.set(pos, messageText);
            }
            arrayAdapter.notifyDataSetChanged();
        }

    }

//    public void savePrefClicked(View view){
//
//        sp.edit().clear();
//        counter = "";
//        for(String i : arrayList){
//            counter=counter+"x";
//
//            edit.putString(counter, i);
//            edit.commit();
//        }
//
//    }
//
//    public void loadPrefClicked(View view){
//
//        counter = "";
//        excounter = "x";
//        while(!(sp.getString(excounter, "A").equals("A"))){
//            counter=counter+"x";
//            excounter=excounter+"x";
//            arrayList.clear();
//            arrayList.add(sp.getString(counter, "IM CONFUSED"));
//        }
//        arrayAdapter.notifyDataSetChanged();
//
//    }
}
