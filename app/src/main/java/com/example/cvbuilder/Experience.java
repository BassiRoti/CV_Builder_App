package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Experience extends AppCompatActivity {

    Button submit_btn, clear_btn;
    EditText title,company,duration,description;


    private void init(){
        submit_btn=findViewById(R.id.exp_submit);
        clear_btn=findViewById(R.id.exp_clear);
        title=findViewById(R.id.job_title);
        company=findViewById(R.id.company);
        duration=findViewById(R.id.job_duration);
        description=findViewById(R.id.job_description);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();




        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_title=title.getText().toString().trim();
                String user_company=company.getText().toString().trim();
                String user_duration=duration.getText().toString().trim();
                String user_desc=description.getText().toString().trim();
                Intent i=new Intent();
                i.putExtra("title",user_title);
                i.putExtra("company",user_company);
                i.putExtra("duration",user_duration);
                i.putExtra("desc",user_desc);

                if (user_title.isEmpty() || user_company.isEmpty() || user_duration.isEmpty() || user_desc.isEmpty()) {
                    Toast.makeText(v.getContext(), "Fill in all details or cancel", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(user_title.length()<3 || user_company.length()<3 || user_desc.length()<9){
                    Toast.makeText(Experience.this, "Enter Valid Fields", Toast.LENGTH_SHORT).show();
                }

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



}