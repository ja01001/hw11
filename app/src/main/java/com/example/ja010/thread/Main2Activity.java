package com.example.ja010.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    TextView t1;
    EditText et1;
    ImageView img;
    Task task;
    int imgafter = 0;
    int count =0;
    int imgarr[] ={R.drawable.start,R.drawable.ham,R.drawable.chicken,R.drawable.pizza,R.drawable.rice};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1  = (TextView)findViewById(R.id.second);// 초가 바뀔때
        et1 = (EditText)findViewById(R.id.et);
        img = (ImageView)findViewById(R.id.im);


    }
    public void clcl(View v){
        if(v.getId() == R.id.im){

            Drawable tempimg =img.getDrawable();
            Drawable temR =getApplicationContext().getResources().getDrawable(R.drawable.start);
            Bitmap timgp = ((BitmapDrawable)tempimg).getBitmap();
            Bitmap tempRes = ((BitmapDrawable)temR).getBitmap();
            if(timgp.equals(tempRes)){
                task = new Task();
                task.execute(0);
            }
            else{
                task.cancel(true);
                task=null;
            }
        }
        else if(v.getId()== R.id.btn1){
            img.setImageResource(imgarr[0]);
            count =0;
            t1.setText("");

        }
    }
    class Task extends AsyncTask<Integer,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            t1.setText("시작부터 0초");
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            for(int x = 0; x<101;x++){
                if(isCancelled()==true)
                    return null;
                try {
                    Thread.sleep(1000);
                    publishProgress(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int number = Integer.parseInt(et1.getText().toString());
            count = values[0];
            int n = (int)((Math.random()*3))+1;
            if(values[0]==0){
                img.setImageResource(imgarr[n]);
                imgafter =n;
                t1.setText("시작부터"+values[0]+"초");
            }
            else if(values[0]%number !=0){
                t1.setText("시작부터"+values[0]+"초");
            }
            else if(values[0]%number ==0){
                t1.setText("시작부터"+values[0]+"초");
                img.setImageResource(imgarr[n]);
                imgafter = n;
            }
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if(imgafter==1){
                t1.setText("햄버거 선택 ("+count+"초)");
            }
            else if (imgafter ==2){
                t1.setText("치킨 선택 ("+count+"초)");
            }
            else if(imgafter==3){
                t1.setText("피자 선택 ("+count+"초)");
            }
            else if(imgafter==4){
                t1.setText("밥 선택 ("+count+"초)");
            }

        }
    }
}
