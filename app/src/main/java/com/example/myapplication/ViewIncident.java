package com.example.myapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Employee;
import com.example.myapplication.MyAdapter.MyAdapter;

import java.util.ArrayList;

public class ViewIncident extends AppCompatActivity {

    ListView lstIncidentHistory;
    private ArrayAdapter adptListViewProgramCourse;
    ArrayList<Employee> arrIncidentList = new ArrayList<Employee>();

    TextView txtNotFound;

    MyAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incident);

        lstIncidentHistory = (ListView) findViewById(R.id.lstIncidentHistory);

        txtNotFound = (TextView) findViewById(R.id.txtNotFound);

        txtNotFound.setVisibility(View.INVISIBLE);
        //getIncidentHistoryList();
        arrIncidentList = getAllData();
        addListToListView();
        //ListView OnClick Event
        lstIncidentHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("Id: "+ arrIncidentList.get(position).getId() +".\n\n");
                    buffer.append("Name: "+ arrIncidentList.get(position).getEmp_Name() +".\n\n");
                    buffer.append("Department: "+ arrIncidentList.get(position).getEmp_Department()+".\n\n");
                    buffer.append("Employee Number: "+ arrIncidentList.get(position).getEmp_Number()+".\n\n");
                    buffer.append("Gender: "+ arrIncidentList.get(position).getGender()+".\n\n");
                    buffer.append("Incident Date: "+ arrIncidentList.get(position).getIncidentDate()+".\n\n");
                    buffer.append("Incident Type: "+ arrIncidentList.get(position).getIncident_Type()+".\n\n");
                    buffer.append("Injured Body Part: "+ arrIncidentList.get(position).getInjured_Body_Part()+".\n\n");
                    buffer.append("Shift: "+ arrIncidentList.get(position).getShift()+".\n\n");
                    showMesage("Employee Details",buffer.toString());
                //Toast.makeText(ViewIncident.this,"fsdfsd", LENGTH_SHORT).show();
            }
        });
    }

//using getAllData method will get all the user details from the database
    public ArrayList<Employee> getAllData()
    {
        ArrayList<Employee> emplist = new ArrayList<>();
        DatabaseAccess da = DatabaseAccess.getInstance(getApplicationContext());
        da.open();
        Cursor crBodyParts = da.getAllIncidentHistory();

        //crBodyParts.moveToFirst();

        //creating a buffer from database to display in message .
       while(crBodyParts.moveToNext()) {
            Integer id = crBodyParts.getInt(0);   //0 is the number of id column in your database table
            String title = crBodyParts.getString(1);
            String incidentdate = crBodyParts.getString(2);
            String empNumber = crBodyParts.getString(3);
            String empName = crBodyParts.getString(4);
            String gender = crBodyParts.getString(5);
            String shift = crBodyParts.getString(6);
            String empDepartment = crBodyParts.getString(7);
            String position = crBodyParts.getString(8);
            String incidenttype = crBodyParts.getString(9);
            String injuredbodypart = crBodyParts.getString(10);
            Employee newDog = new Employee(id,title,incidentdate,empNumber,empName,gender,shift,empDepartment,position,incidenttype,injuredbodypart);
            emplist.add(newDog);
        }
        return emplist;
    }

    //Adding DATA to listview if data not available then hide list view and show not found message to user.
    private void addListToListView(){
        if(arrIncidentList.size() > 0){
            txtNotFound.setVisibility(View.GONE);
            lstIncidentHistory = (ListView) findViewById(R.id.lstIncidentHistory);

            myadapter = new MyAdapter(this,arrIncidentList);

            lstIncidentHistory.setAdapter(myadapter);
            myadapter.notifyDataSetChanged();
            //adptListViewProgramCourse = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrIncidentList);
            //lstIncidentHistory.setAdapter(adptListViewProgramCourse);
        }
        else{

            txtNotFound.setVisibility(View.VISIBLE);
        }

    }


    //show message with string passed in arguments.
    public void showMesage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
