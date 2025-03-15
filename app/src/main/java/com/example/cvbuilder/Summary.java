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

public class Summary extends AppCompatActivity {
    EditText summary;
    Button submit_btn, clear_btn;
    private void init(){
        summary=findViewById(R.id.user_summary_id);
        submit_btn=findViewById(R.id.summary_submit);
        clear_btn=findViewById(R.id.summary_clear);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String summary_data=summary.getText().toString().trim();
                Intent i=new Intent();
                i.putExtra("summ",summary_data);
                if (summary_data.isEmpty()) {
                    Toast.makeText(v.getContext(), "This field is mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(summary_data.length()<20){
                    Toast.makeText(Summary.this, "Write more", Toast.LENGTH_SHORT).show();
                    return;
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