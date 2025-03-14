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

public class Education extends AppCompatActivity {
    Button submit_btn, clear_btn;
    EditText degree,institution,year;
    private void init(){
        submit_btn=findViewById(R.id.education_submit);
        clear_btn=findViewById(R.id.education_clear);
        degree=findViewById(R.id.degree);
        institution=findViewById(R.id.et_institution);
        year=findViewById(R.id.et_year);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();




        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_degree=degree.getText().toString().trim();
                String user_institution=institution.getText().toString().trim();
                String user_year=year.getText().toString().trim();
                if (user_degree.isEmpty()) {
                    Toast.makeText(v.getContext(), "Degree cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user_institution.isEmpty()) {
                    Toast.makeText(v.getContext(), "Institution cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user_year.isEmpty()) {
                    Toast.makeText(v.getContext(), "Year cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i=new Intent();
                i.putExtra("degree",user_degree);
                i.putExtra("institution",user_institution);
                i.putExtra("year",user_year);

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