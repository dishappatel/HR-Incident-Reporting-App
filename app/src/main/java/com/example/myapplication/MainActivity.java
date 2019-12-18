package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    Intent intentReportIncident,intentViewIncident;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentReportIncident = new Intent(MainActivity.this,ReportIncident.class);
        intentViewIncident = new Intent(MainActivity.this,ViewIncident.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return  true;
    }
    // when user will click on menu item onOptionsItemSelected method will call
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.mnuReportIncident:
                startActivity(intentReportIncident);
                return true;
            // break;
            case R.id.mnuViewIncident:
                startActivity(intentViewIncident); return true;
            //break;

            default:
                return  super.onOptionsItemSelected(item);

        }
        // return super.onOptionsItemSelected(item);
    }
}
