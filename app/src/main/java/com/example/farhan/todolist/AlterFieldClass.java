package com.example.farhan.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterFieldClass extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private Button svbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);
        svbtn = (Button) findViewById(R.id.svbtn);
        mDatabaseHelper = new DatabaseHelper(this);
        svbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = ((EditText) findViewById(R.id.message)).getText().toString();



                    // AddData(messageText);
                    //Boolean bool = mDatabaseHelper.addData(messageText);
                    Intent intent = new Intent();
                    intent.putExtra(Intent_Constants.INTENT_MESSAGE_FIELD_2, messageText);
                    setResult(Intent_Constants.INTENT_RESULT_CODE_2, intent);
                    finish();

            }
        });






    }

}
