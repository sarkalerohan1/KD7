package java.tata.hitesh.kd7;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class InsertDataBackgroundTask extends AsyncTask<String, Void, String> {

    Context ctx;

    public InsertDataBackgroundTask(Insertdata insertData) {
        this.ctx = insertData;
    }

    protected String doInBackground(String... params) {

        String reg_url = "https://rohansarkale.000webhostapp.com/add2.php";
        //http://192.168.0.102/project/add.php
        String method = params[0];
        if (method.equals("add")) {

            String name = params[1];
            String rollno = params[2];
            String marks = params[3];


            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("rollno", "UTF-8") + "=" + URLEncoder.encode(rollno, "UTF-8") + "&" +
                        URLEncoder.encode("marks", "UTF-8") + "=" + URLEncoder.encode(marks, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration success";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

}