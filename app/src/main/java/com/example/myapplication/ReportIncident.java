package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportIncident extends AppCompatActivity {

    //Declaring variables
    DatePickerDialog incidentDatePicker;
    EditText edtIncidentDate,edtEmpNumber,edtEmpName,edtDepartment,edtPosition;

    TextView txtTitle,txtIncidentDate,txtEmpNumber,txtEmpName,txtGender,txtShift;
    TextView txtDepartment,txtPosition,txtIncidentType,txtInjuredBodyPart;

    RadioButton rdMale,rdFemale;
    Button btnSubmitIncident;

    private Spinner spiShift,spiIncidentType,spiInjuredBodyPart;

    String strIncidentdate,strShift,strIncidentType,strInjuredBodypart,strGender;

    String[] arrShift = {"Shift A","Shift B","Shift C"};
    String[] arrIncidentType = {"Near Miss","First Aid","Medical Aid"};
    List<String> arrBodyParts = new ArrayList<String>();

//Assigning static values to the variable
    static final int CAM_REQUEST = 100;
    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_PERMISSION = 200;
    private String imageFilePath = "";
    //Will save image path to uri variable
    Uri pictureUri;
    @Override
    //onCreate method will call all the other declared methods
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);
        initializeObjectAndValues();
        setUp();
        //Spinner
        addShiftSpinner();
        addIncidentTypeSpinner();
        addInjuredBodypartSpinner();

    }

    //SetUp
    private  void initializeObjectAndValues()
    {
        //EDIT TEXT
        edtEmpNumber = (EditText) findViewById(R.id.edtEmpNumber);
        edtEmpName = (EditText) findViewById(R.id.edtEmpName);
        edtDepartment = (EditText) findViewById(R.id.edtDepartment);
        edtPosition = (EditText) findViewById(R.id.edtPosition);
        //TEXT VIEW
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtIncidentDate = (TextView) findViewById(R.id.txtIncidentDate);
        txtEmpNumber = (TextView) findViewById(R.id.txtEmpNumber);
        txtEmpName = (TextView) findViewById(R.id.txtEmpName);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtShift = (TextView) findViewById(R.id.txtShift);
        txtDepartment = (TextView) findViewById(R.id.txtDepartment);
        txtPosition = (TextView) findViewById(R.id.txtPosition);
        txtIncidentType = (TextView) findViewById(R.id.txtIncidentType);
        txtInjuredBodyPart = (TextView) findViewById(R.id.txtInjuredBodyPart);
        //RADIO BUTTON
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);
        //BUTTON
        btnSubmitIncident = (Button) findViewById(R.id.btnSubmitIncident);

    }
//setUp method will do all the typecasting and assigning empty string to the variable
    private void setUp()
    {
        strIncidentdate = "";
        strShift = "";
        strIncidentType = "";
        strInjuredBodypart = "";
        strGender ="";
        edtIncidentDate = findViewById(R.id.edtIncidentDate);
        final Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        edtIncidentDate.setText(day + "/" + month + "/" + year);
        strIncidentdate = edtIncidentDate.getText().toString();
        edtIncidentDate.setInputType(InputType.TYPE_NULL);
        edtIncidentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                //edtIncidentDate.setText(day + "/" + month + "/" + year);
                incidentDatePicker = new DatePickerDialog(ReportIncident.this, new DatePickerDialog.OnDateSetListener()
                {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {

                        edtIncidentDate.setText(dayOfMonth + "/" + (month) + "/" + year);
                        strIncidentdate = edtIncidentDate.getText().toString();
                    }
                },year,month,day);
                incidentDatePicker.show();

            }
        });
        strGender = "Male";
        rdMale.setChecked(true);
    }

    //addSpiner method will call spinner object and parallel call all the spinner values from an array
    private void addShiftSpinner()
    {
        spiShift = (Spinner) findViewById(R.id.spiShift);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrShift);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiShift.setAdapter(dataAdapter);
        strShift = arrShift[0];

        spiShift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strShift = arrShift[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strShift = "";
            }
        });
    }

    //using IncidentTypeSpinner method  user cans elect type of incident from spinner listview
    private void addIncidentTypeSpinner()
    {
        spiIncidentType = (Spinner) findViewById(R.id.spiIncidentType);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrIncidentType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiIncidentType.setAdapter(dataAdapter);
        strIncidentType = arrIncidentType[0];
        spiIncidentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strIncidentType = arrIncidentType[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strIncidentType = "";
            }
        });

    }
    //Using injuredBodyPartSpinner method user can select injured vosy part from spinner listview
    private void addInjuredBodypartSpinner()
    {
        spiInjuredBodyPart = (Spinner) findViewById(R.id.spiInjuredBodyPart);
        DatabaseAccess da = DatabaseAccess.getInstance(getApplicationContext());
        da.open();
        Cursor crBodyParts = da.getBodyPartsList();

        if(crBodyParts.getCount() == 0)
        {
            //showMesage("Error","Nothing found");
            return;
        }
        else if (crBodyParts.getCount() <0){
           // showMesage("Error","Nothing found");
        }
        else {
            crBodyParts.moveToFirst();
            while (!crBodyParts.isAfterLast()) {
                arrBodyParts.add(crBodyParts.getString(0));
                crBodyParts.moveToNext();
            }
           // crBodyParts.close();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrBodyParts);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiInjuredBodyPart.setAdapter(dataAdapter);
        strInjuredBodypart = arrBodyParts.get(0).toString();
        da.close();

        // spiInjuredBodyPart will check selected option from listview  if not found then return an error message
        spiInjuredBodyPart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(arrBodyParts.isEmpty() || arrBodyParts == null || arrBodyParts.size() <= 0)
                {
                    Toast.makeText(ReportIncident.this,"No value in array to select",Toast.LENGTH_SHORT).show();
                }
                else{
                    strInjuredBodypart = arrBodyParts.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strInjuredBodypart = "";
            }
        });

    }

    //gender method will return selected gender type
    public void rdGenderSelected(View v)
    {
        if(rdMale.isChecked())
        {
            Toast.makeText(this,"Button Submit clicked",Toast.LENGTH_SHORT).show();
            strGender = "Male";

        }
        else
        {
            Toast.makeText(this,"Button Submit clicked",Toast.LENGTH_SHORT).show();
            strGender = "Female";
        }
    }

    //VALIDATION for all the required fields on incident form
    private  boolean IsAllFieldValid()
    {
        if (edtIncidentDate.getText().length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please select incident date",Toast.LENGTH_SHORT).show();
            return  false;
        }

        else if(strIncidentdate.length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please select incident date",Toast.LENGTH_SHORT).show();
            return  false;
        }

        else if(edtEmpNumber.getText().length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please enter employee number",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if (edtEmpName.getText().length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please enter employee name",Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(rdFemale.isChecked() == false && rdMale.isChecked() == false )
        {
            Toast.makeText(ReportIncident.this,"Please select gender type",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else  if(strShift.length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please select shift",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if(edtDepartment.getText().length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please enter department name",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else  if (edtPosition.getText().length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please enter employee position at work",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if(strIncidentType.length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please select incident type",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if(strInjuredBodypart.length() <= 0)
        {
            Toast.makeText(ReportIncident.this,"Please select injured body part",Toast.LENGTH_SHORT).show();
            return  false;
        }
        return  true;
    }

    //submitIncidentClicked button event will insert all the data in to the database
    public  void btnSubmitIncidentClicked(View v)
    {
        if(IsAllFieldValid())
        {
            //Insert Data into Database table
            String strQuery = "Insert into tbl_IncidentHistory " +
                    "(incident_date,emp_number,emp_name,gender,shift,emp_department,emp_position,incident_type,injured_body_part) values " +
                    "('"+strIncidentdate+"','"+edtEmpNumber.getText().toString()+"','"+edtEmpName.getText().toString()+"','"+strGender+"','"+strShift+"','"+edtDepartment.getText().toString()+"','"+edtPosition.getText().toString()+"','"+strIncidentType+"','"+strInjuredBodypart+"')";

            DatabaseAccess da = DatabaseAccess.getInstance(getApplicationContext());
            da.open();
            da.insertIncidentRecord(strQuery);
            da.close();
            openCamera();
        }
    }

    //onCamera method will first grant and add permission for camera
    private void openCamera(){
        if (ContextCompat.checkSelfPermission(ReportIncident.this,Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(ReportIncident.this,
                    Manifest.permission.CAMERA))
            {
                Toast.makeText(ReportIncident.this,"Permission not given",Toast.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(ReportIncident.this, new String[] {Manifest.permission.CAMERA}, CAM_REQUEST);
            }

        }
        //if camera action is permitted then image will be capture an image path will store in to Uri variable
        else
        {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            String pictureName = getPictureName();
            File imagefile = new File(pictureDirectory,pictureName);
            pictureUri = Uri.fromFile(imagefile);

            camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
            if(camera_intent.resolveActivity(getPackageManager()) != null)
            {
                startActivityForResult(camera_intent,REQUEST_IMAGE);
            }

        }

    }

    //generating a unique string name to save an image
    private String getPictureName()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestmp = sdf.format(new Date());
        return "newImage"+timestmp+".jpg";
    }

    //onRequestPermissionsResult will call while camera permission will be changed
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CAM_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Thanks for granting Permission", Toast.LENGTH_SHORT).show();
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                    String pictureName = getPictureName();
                    File imagefile = new File(pictureDirectory,pictureName);
                    pictureUri = Uri.fromFile(imagefile);

                    camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(camera_intent,REQUEST_IMAGE);
                }
                else
                {
                    Toast.makeText(ReportIncident.this,"Permission not given",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    //when user will click an image then onActivityResult method will call and store all
    // the incident detail with an image in email and we can send that email on given hr email address
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAM_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"tmathew@conestogac.on.ca"});
            i.putExtra(Intent.EXTRA_SUBJECT, "HR Incident Reporting");

            String strBody = "Employee Incident Report"
                                +   "\n Incident Date: " + strIncidentdate
                                + "\n Employee Number: " + edtEmpNumber.getText().toString()
                                + "\n Employee Name: " + edtEmpName.getText().toString()
                                + "\n Employee Department: " + edtDepartment.getText().toString()
                                + "\n Employee Position: " + edtPosition.getText().toString()
                                + "\n Gender: " + strGender
                                + "\n Incident Type: " + strIncidentType
                                + "\n Injured Body part: " + strInjuredBodypart;
            i.putExtra(Intent.EXTRA_TEXT   , strBody);
            if(pictureUri != null){
                i.setType("application/image");
                i.putExtra(Intent.EXTRA_STREAM, pictureUri);
            }
            try
            {
                startActivity(Intent.createChooser(i, "Send mail..."));
            }
            catch (android.content.ActivityNotFoundException ex)
            {
                Toast.makeText(ReportIncident.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
