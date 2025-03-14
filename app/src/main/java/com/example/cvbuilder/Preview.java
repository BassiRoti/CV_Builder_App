package com.example.cvbuilder;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class Preview extends AppCompatActivity {
    ImageView img;
    TextView name, email, phone, dobb,genderr, summary, degree, institute, degreeyear, ref;
    TextView jobtitle,jobcompany, jobduration, jobdesc, certs;

    LinearLayout jobvisibility, certificationvisibility, refvisibility;

    Button share;

    private void init(){
        img=findViewById(R.id.user_profile);
        name=findViewById(R.id.setname);
        email=findViewById(R.id.setemail);
        phone=findViewById(R.id.setnumber);
        dobb=findViewById(R.id.setdob);
        genderr=findViewById(R.id.setgender);
        summary=findViewById(R.id.preview_summary);
        degree=findViewById(R.id.setdegree);
        institute=findViewById(R.id.setinstitution);
        degreeyear=findViewById(R.id.setgradyear);
        ref=findViewById(R.id.setref);

        jobtitle=findViewById(R.id.settitle);
        jobcompany=findViewById(R.id.setcompany);
        jobduration=findViewById(R.id.setduration);
        jobdesc=findViewById(R.id.setdesc);
        certs=findViewById(R.id.setcerts);

        jobvisibility=findViewById(R.id.jobvisibility);
        certificationvisibility=findViewById(R.id.certvisibility);
        refvisibility=findViewById(R.id.refvisibility);

        share=findViewById(R.id.share);

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

        String summary_data=i.getStringExtra("user_summary_data");
        summary.setText(summary_data);

        String dname=i.getStringExtra("degree_name");
        String inst=i.getStringExtra("institution_name");
        String dyear=i.getStringExtra("degree_year");

        degree.setText(dname);
        institute.setText(inst);
        degreeyear.setText(dyear);

        String title=i.getStringExtra("company_title");
        String company=i.getStringExtra("company");
        String duration=i.getStringExtra("company_duration");
        String desc=i.getStringExtra("desc");

        jobtitle.setText(title);
        jobcompany.setText(company);
        jobduration.setText(duration);
        jobdesc.setText(desc);

        String certifications=i.getStringExtra("certs");
        certs.setText(certifications);



        String refer=i.getStringExtra("refs");
        ref.setText(refer);


        if (title == null || title.trim().isEmpty()) {
            jobvisibility.setVisibility(View.GONE);
        }

        if (refer == null || refer.trim().isEmpty()) {
            refvisibility.setVisibility(View.GONE);
        }

        if (certifications == null || certifications.trim().isEmpty()) {
            certificationvisibility.setVisibility(View.GONE);
        }


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("text");
                startActivity(i);
                finish();
            }
        });
    }



}
