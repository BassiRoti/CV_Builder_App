package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PersonalDetails extends AppCompatActivity {
    EditText name,dob,email,number;
    RadioGroup rgrp;
    Button submit_btn, clear_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();




        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName=name.getText().toString().trim();
                String User_dob=dob.getText().toString().trim();
                String UserEmail=email.getText().toString().trim();
                String UserNumber=number.getText().toString().trim();

                if (UserName.isEmpty()) {
                    Toast.makeText(v.getContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (User_dob.isEmpty()) {
                    Toast.makeText(v.getContext(), "Date of Birth cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (UserEmail.isEmpty()) {
                    Toast.makeText(v.getContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (UserNumber.isEmpty()) {
                    Toast.makeText(v.getContext(), "Number cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(UserName.length()<4){
                    Toast.makeText(PersonalDetails.this, "Username length should be atleast 4", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(User_dob.length()!=4){
                    Toast.makeText(PersonalDetails.this, "Enter valid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!UserEmail.contains("@")){
                    Toast.makeText(PersonalDetails.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(UserNumber.length()!=11){
                    Toast.makeText(PersonalDetails.this, "Enter valid number", Toast.LENGTH_SHORT).show();
                    return;
                }

                int garbage=rgrp.getCheckedRadioButtonId();
                String gender="";
                if(garbage!=-1) {
                    RadioButton btn = findViewById(garbage);
                    gender = btn.getText().toString().trim();
                }
                else{
                    Toast.makeText(v.getContext(), R.string.kindly_select_gender, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i=new Intent();
                i.putExtra("UserName",UserName);
                i.putExtra("User_dob",User_dob);
                i.putExtra("UserEmail",UserEmail);
                i.putExtra("UserNumber",UserNumber);
                i.putExtra("Gender",gender);

                setResult(RESULT_OK,i);
                finish();
            }
        });

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });




    }

    private void init(){
        name=findViewById(R.id.user_name_id);
        dob=findViewById(R.id.user_dob_id);
        email=findViewById(R.id.user_email_id);
        number=findViewById(R.id.user_phone_id);
        rgrp=findViewById(R.id.gender_grp_id);
        submit_btn=findViewById(R.id.submit);
        clear_btn=findViewById(R.id.clear);
    }

}