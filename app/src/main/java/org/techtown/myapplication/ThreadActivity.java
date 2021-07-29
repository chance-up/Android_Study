package org.techtown.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadActivity extends AppCompatActivity {
    TextView textView2;
    TextView textView3;
    TextView textView4;
    EditText editText4;


    MainHandler handler;
    Handler runHandler = new Handler();
    Handler loopHandler = new Handler();

    ProcessThread prThread;

    BackgroundTask task;
    int asyncValue = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        editText4 = findViewById(R.id.editText4);
        textView4 = findViewById(R.id.textView4);

        Button buttonThread = findViewById(R.id.button1);
        buttonThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread1 thread = new BackgroundThread1();
                thread.start();
            }
        });

        Button buttonThread2 = findViewById(R.id.button2);
        buttonThread2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread2 thread = new BackgroundThread2();
                thread.start();
            }
        });
        handler = new MainHandler();

        Button buttonThread3 = findViewById(R.id.button3);
        buttonThread3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread3 thread = new BackgroundThread3();
                thread.start();
            }
        });

        Button buttonThread4 = findViewById(R.id.button4);
        buttonThread4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText4.getText().toString();
                Message msg = Message.obtain();
                msg.obj = input;

                prThread.prHandler.sendMessage(msg);
            }
        });

        prThread = new ProcessThread();

        progressBar = findViewById(R.id.progressBar);

        Button asyncTaskStartBtn =findViewById(R.id.button5);


        asyncTaskStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new BackgroundTask();
                task.execute();
            }
        });

        Button asyncTaskStopBtn =findViewById(R.id.button6);
        asyncTaskStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
            }
        });



    }

    class BackgroundThread1 extends Thread{
        int value = 0;

        public void run(){
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(1000);
                } catch(Exception e){
                    e.printStackTrace();
                }
                value+=1;
                Log.d("ThreadTest","value : "+value);
            }
        }
    }

    class BackgroundThread2 extends Thread{
        int value = 0;

        public void run(){
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(1000);
                } catch(Exception e){
                    e.printStackTrace();
                }

                value+=1;
                Log.d("ThreadTest","value : "+value);

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);

                handler.sendMessage(message);

            }
        }
    }

    class MainHandler extends Handler{

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView2.setText("Value : "+value);
        }
    }

    class BackgroundThread3 extends Thread{
        int value = 0;

        public void run(){
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(1000);
                } catch(Exception e){
                    e.printStackTrace();
                }

                value+=1;
                Log.d("ThreadTest","value : "+value);

                runHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView3.setText("Value : "+value);
                    }
                });
            }
        }
    }


    class ProcessThread extends Thread{
        ProcessHandler prHandler = new ProcessHandler();

        public void run(){
            Looper.prepare();
            Looper.loop();
        }

        class ProcessHandler extends Handler{

            public void handleMessage(Message msg){
                final String output = msg.obj + " from thread.";
                loopHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView4.setText(output);
                    }
                });
            }
        }
    }

    class BackgroundTask extends AsyncTask<Integer,Integer,Integer> {
        protected void onPreExecute() {
            asyncValue = 0;
            progressBar.setProgress(asyncValue);
        }

        protected Integer doInBackground(Integer ... values){
            while(isCancelled() == false){
                asyncValue++;
                if(asyncValue>=100){
                    break;
                }else{
                    publishProgress(asyncValue);
                }

                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
            return asyncValue;
        }
        protected void onProgressUpdate(Integer ... values){
            progressBar.setProgress(values[0].intValue());
        }

        protected void onPostExecute(Integer result){
            progressBar.setProgress(0);
        }

        protected void onCancelled(){
            progressBar.setProgress(0);
        }
    }
}