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
                Toast.makeText(this, "Error Uploading Image", Toast.LENGTH_SHORT).show();
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
               Toast.makeText(this, "Error Getting Personal Details", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "Error Getting Summary", Toast.LENGTH_SHORT).show();
            }
        });

    }
}