package com.example.myapplication;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;

    InputStream is = null;
    String result = null;
    String line = null;
    String message ="xDxdxdx";
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://groupproj.cba.pl/ops/signin_user.php";
        String reg_url = "http://groupproj.cba.pl/ops/signup_user.php";
        if(type.equals("login")) {
            message ="ez";
            try {
                String login = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("login","UTF-8")+"="+URLEncoder.encode(login,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                StringBuilder sb = new StringBuilder();
                while((line = bufferedReader.readLine())!= null) {
                    sb.append(line + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                result = sb.toString();
                Log.e("pass 2", "connection success" + result);
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                Log.e("Fail 1", e.toString());
            } catch (IOException e) {
                Log.e("Fail 2", e.toString());
            }
        }else if(type.equals("register")){
            try {
                String username = params[1];
                String password = params[2];
                String email = params[3];
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                StringBuilder sb = new StringBuilder();
                while((line = bufferedReader.readLine())!= null) {
                    sb.append(line + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                result = sb.toString();
                Log.e("pass 2", "connection success" + result);
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                Log.e("Fail 1", e.toString());
            } catch (IOException e) {
                Log.e("Fail 2", e.toString());
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {try
    {
        JSONObject json_data = new JSONObject(result);
        message = (json_data.getString("message"));
        Log.e("pass 3",message);
    }
    catch(Exception e)
    {
        Log.e("Fail 3", e.toString());
        message +=":("+result;
    }
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}