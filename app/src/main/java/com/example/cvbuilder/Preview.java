package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Preview extends AppCompatActivity {
    ImageView img;
    TextView name, email, phone, dobb,genderr;

    private void init(){
        img=findViewById(R.id.user_profile);
        name=findViewById(R.id.setname);
        email=findViewById(R.id.setemail);
        phone=findViewById(R.id.setnumber);
        dobb=findViewById(R.id.setdob);
        genderr=findViewById(R.id.setgender);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        Intent i=getIntent();
        String pic=i.getStringExtra("image");
        Uri picc=Uri.parse(pic);

        img.setImageURI(picc);


        String username=i.getStringExtra("UserName");
        String Email=i.getStringExtra("UserEmail");
        String number=i.getStringExtra("UserNumber");
        String dob=i.getStringExtra("User_dob");
        String gender=i.getStringExtra("UserGender");

        name.setText(username);
        email.setText(Email);
        phone.setText(number);
        dobb.setText(dob);
        genderr.setText(gender);



    }
}