package com.example.ssjianyu.sqlcontack;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    DatabaseHelper myDb;
    EditText editname,editphone,editemail,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewupdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);



        editname=(EditText)findViewById(R.id.editText_name);
        editphone=(EditText)findViewById(R.id.editText_phone);
        editemail=(EditText)findViewById(R.id.editText_email);
        editTextId=(EditText)findViewById(R.id.editText_id);
        btnAddData=(Button)findViewById(R.id.button_add);
        btnviewAll=(Button)findViewById(R.id.button_viewAll);
        btnviewupdate=(Button)findViewById(R.id.button_update);
        btnDelete=(Button)findViewById(R.id.button_delete);
        AddData();
        Review();
        UpdateData();
        DeleteData();
    }



    public void UpdateData(){
        btnviewupdate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                      boolean isUpdate=myDb.updateData(editTextId.getText().toString(),
                              editname.getText().toString(),editphone.getText().toString(),
                              editemail.getText().toString());
                        if(isUpdate==true)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

                    }

                }
        );
    }

    public  void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editname.getText().toString(),
                                editphone.getText().toString(),
                                editemail.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void Review(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Cursor res=myDb.getAllData();
                        if(res.getCount()==0){
                            showmessage("Error","Nothing Found");
                            return;

                        }

                        StringBuffer buffer=new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("id :"+ res.getString(0)+"\n");
                            buffer.append("name :"+ res.getString(1)+"\n");
                            buffer.append("phone :"+ res.getString(2)+"\n");
                            buffer.append("email :"+ res.getString(3)+"\n\n");

                        }
                        showmessage("DATA",buffer.toString());
                    }
                }
        );
    }

    public void showmessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Integer deleteRows=myDb.deleteData(editTextId.getText().toString());
                            if(deleteRows>0)
                                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();

                        }
                    }



        );
    }
}
