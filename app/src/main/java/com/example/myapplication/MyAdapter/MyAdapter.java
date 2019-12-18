package com.example.myapplication.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Model.Employee;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Employee> arrEmpList;


    public MyAdapter(Context context, ArrayList<Employee> arrEmpList)
    {
        this.context = context;
        this.arrEmpList = arrEmpList;
    }

    //GetCount return number of rows displayed in list view.
    @Override
    public int getCount() {
        return this.arrEmpList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public Object getItem(int position)
    {
        return this.arrEmpList.get(position);
    }


    //Accessing custom view to show in listview and assigning each row value
    //get view will be used to access any custom view.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflator.inflate(R.layout.customlistviewrow,null);
        TextView txtName = (TextView)convertView.findViewById(R.id.txtCustomName);
        TextView txtDepartment = (TextView)convertView.findViewById(R.id.txtCustomDepartment);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        Employee emp = arrEmpList.get(position);

        txtTitle.setText(emp.getEmp_Name().substring(0,1).toUpperCase());
        txtName.setText(emp.getEmp_Name());
        txtDepartment.setText(emp.getEmp_Department());
        return convertView;
    }
}
