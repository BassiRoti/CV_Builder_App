package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    Button final_submit;

    ActivityResultLauncher<Intent> profile_pic;
    ActivityResultLauncher<Intent> details;
    ActivityResultLauncher<Intent> user_summary;
    ActivityResultLauncher<Intent> education;
    ActivityResultLauncher<Intent> experience;
    ActivityResultLauncher<Intent> certifications;
    ActivityResultLauncher<Intent> references;

    Boolean user_pic;
    Boolean user_details;
    Boolean user_sum;
    Boolean user_ed;


    private void init(){
        pp=findViewById(R.id.profile_pic_id);
        pd=findViewById(R.id.personal_details_id);
        s=findViewById(R.id.summary_id);
        ed=findViewById(R.id.education_id);
        exp=findViewById(R.id.experience_id);
        cert=findViewById(R.id.certifications_id);
        ref=findViewById(R.id.references_id);
        final_submit=findViewById(R.id.final_submit);
        user_pic=false;
        user_details=false;
        user_sum=false;
        user_ed=false;
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
        Intent transition=new Intent(this, Preview.class);
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
                transition.putExtra("image",image.toString());
                user_pic=true;
                //need to set image
            }
            else{
                Toast.makeText(this, "Kindly Upload Image", Toast.LENGTH_SHORT).show();
                return;
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
               String UserGender=getdata.getStringExtra("Gender");
               //fetched user details
//               Toast.makeText(this, "Passing data", Toast.LENGTH_SHORT).show();

               transition.putExtra("UserName",UserName);
               transition.putExtra("User_dob",User_dob);
               transition.putExtra("UserEmail",UserEmail);
               transition.putExtra("UserNumber",UserNumber);
               transition.putExtra("UserGender",UserGender);

               Log.d("DEBUG", "UserName: " + UserName);
               Log.d("DEBUG", "User_dob: " + User_dob);
               Log.d("DEBUG", "UserEmail: " + UserEmail);
               Log.d("DEBUG", "UserNumber: " + UserNumber);
               user_details=true;

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

                transition.putExtra("user_summary_data",user_summary_Data);
                user_sum=true;
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

                transition.putExtra("degree_name",degree_name);
                transition.putExtra("institution_name",institution_name);
                transition.putExtra("degree_year",degree_year);
                user_ed=true;
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

                transition.putExtra("company_title",company_title);
                transition.putExtra("company",company);
                transition.putExtra("company_duration",company_duration);
                transition.putExtra("desc",desc);
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

                transition.putExtra("certs",certs);
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
                String refs=getdata.getStringExtra("ref");

                transition.putExtra("refs",refs);
                //fetched user references
            }
            else{
                Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        final_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "Intent Data: " + transition.getExtras());
                if(user_pic.equals(false) && user_details.equals(false) && user_summary.equals(false) && user_ed.equals(false)){
                    Toast.makeText(HomeActivity.this, "Kindly add all necessary details", Toast.LENGTH_SHORT).show();
                }
                if(user_pic.equals(false)){
                    Toast.makeText(HomeActivity.this, "Kindly Upload Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(user_details.equals(false)){
                    Toast.makeText(HomeActivity.this, "Kindly add Personal Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(user_summary.equals(false)){
                    Toast.makeText(HomeActivity.this, "Kindly add summary", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(user_ed.equals(false)){
                    Toast.makeText(HomeActivity.this, "Kindly add Education", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(transition);
                finish();
            }
        });





    }
}