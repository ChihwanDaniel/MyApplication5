package com.example.prinston.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);
    }

    public void onButton1Clicked(View view){
        String filename = et.getText().toString();
        if(filename.length() > 0){
            openPDF(filename.trim());
        }else{
            Toast.makeText(getApplicationContext(),"얌마, PDF파일 이름을 입력하라고 짜샤",Toast.LENGTH_LONG).show();
        }
    }

    public void openPDF(String filename){
        String sdcardFolder = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filepath = sdcardFolder + File.separator + filename;
        File file = new File(filepath);
        Toast.makeText(this,sdcardFolder,Toast.LENGTH_LONG).show();

        if(file.exists()){
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri,"application/pdf");

            try{
                startActivity(intent);
            }catch(ActivityNotFoundException e){
                Toast.makeText(this,"PDF 볼 뷰어가 없잖아!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"어이,PDF파일을 찾을 수 없거든? 나 놀리냐?",Toast.LENGTH_LONG).show();
        }
    }
}
