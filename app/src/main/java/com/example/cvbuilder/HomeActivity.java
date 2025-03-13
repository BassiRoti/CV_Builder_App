package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    Button pp,pd,s,ed,exp,cert,ref;
    ActivityResultLauncher<Intent> profile_pic;
    ActivityResultLauncher<Intent> details;
    ActivityResultLauncher<Intent> user_summary;

    ActivityResultLauncher<Intent> education;

    ActivityResultLauncher<Intent> experience;

    ActivityResultLauncher<Intent> certifications;

    ActivityResultLauncher<Intent> references;

    private void init(){
        pp=findViewById(R.id.profile_pic_id);
        pd=findViewById(R.id.personal_details_id);
        s=findViewById(R.id.summary_id);
        ed=findViewById(R.id.education_id);
        exp=findViewById(R.id.experience_id);
        cert=findViewById(R.id.certifications_id);
        ref=findViewById(R.id.references_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                profile_pic.launch(i);
            }
        });

        profile_pic=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Uri image=result.getData().getData();
                //need to set image
            }
            else{
                Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,PersonalDetails.class);
                details.launch(i);
            }
        });

        details=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
           if(result.getResultCode()==RESULT_OK && result.getData()!=null){
               Intent getdata=result.getData();
               String UserName=getdata.getStringExtra("UserName");
               String User_dob=getdata.getStringExtra("User_dob");
               String UserEmail=getdata.getStringExtra("UserEmail");
               String UserNumber=getdata.getStringExtra("UserNumber");
               //fetched user details

           }
           else{
               Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
           }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this, Summary.class);
                user_summary.launch(i);
            }
        });

        user_summary=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent getdata=result.getData();
                String user_summary_Data=getdata.getStringExtra("summ");
                //fetched user summary


            }
            else{
                Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this, Education.class);
                education.launch(i);
            }
        });

        education=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent getdata=result.getData();
                String degree_name=getdata.getStringExtra("degree");
                String institution_name=getdata.getStringExtra("institution");
                String degree_year=getdata.getStringExtra("year");

                //fetched user education
            }
            else{
                Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this, Experience.class);
                experience.launch(i);
            }
        });

        experience=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent getdata=result.getData();
                String company_title=getdata.getStringExtra("title");
                String company=getdata.getStringExtra("company");
                String company_duration=getdata.getStringExtra("duration");
                String desc=getdata.getStringExtra("desc");

                //fetched user experience
            }
            else{
                Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        cert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this, Certifications.class);
                certifications.launch(i);
            }
        });

        certifications=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent getdata=result.getData();
                String certs=getdata.getStringExtra("certifications");

                //fetched user certifications
            }
            else{
                Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this, References.class);
                references.launch(i);
            }
        });

        references=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                Intent getdata=result.getData();
                String certs=getdata.getStringExtra("ref");

                //fetched user references
            }
            else{
                Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            }
        });





    }
}