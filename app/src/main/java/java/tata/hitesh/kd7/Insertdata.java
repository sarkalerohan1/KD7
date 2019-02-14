package java.tata.hitesh.kd7;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Insertdata extends AppCompatActivity {
    EditText e_name, e_roll, e_marks;
    String name, rollno, marks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdata);
        e_name = findViewById(R.id.name);
        e_roll = findViewById(R.id.roll);
        e_marks = findViewById(R.id.marks);

        kd();

    }

    private boolean kd() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            return false;
        } else {
            return true;
        }

    }

    public void ADD(View view) {

        name = e_name.getText().toString();
        rollno = e_roll.getText().toString();
        marks = e_marks.getText().toString();
        String method = "add";
        InsertDataBackgroundTask backgroundTask = new InsertDataBackgroundTask(this);
        backgroundTask.execute(method, rollno, name, marks);
        finish();
    }
}
