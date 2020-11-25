package th.ac.su.bodymassindex_bmi.model;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import th.ac.su.bodymassindex_bmi.MyDatabaseHelper;
import th.ac.su.bodymassindex_bmi.R;

public class AddUserActivity extends AppCompatActivity {

    EditText name_input, id_input, weight_input, height_input;
    Button calButton;
    RecyclerView recyclerView;
    double resultBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        name_input = findViewById(R.id.name_edit_text);
        id_input = findViewById(R.id.id_edit_text);
        weight_input = findViewById(R.id.weight_edit_text);
        height_input = findViewById(R.id.height_edit_text);
        calButton = findViewById(R.id.cal_button);
        recyclerView = findViewById(R.id.recyclerView);

        Button bmichartButton = findViewById(R.id.bmi_chart_button);
        bmichartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddUserActivity.this, BmiChartActivity.class);
                startActivity(intent);
            }
        });

        calButton.setOnClickListener(new View.OnClickListener() { //เมื่อกดปุ่ม calButton จะทำการคำนวณหาค่า BMI และทำการแสดงผลออกมาในรูปแบบ Dialog
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddUserActivity.this);
                myDB.addData(name_input.getText().toString().trim(),
                        weight_input.getText().toString().trim(),
                        height_input.getText().toString().trim());

                String weightText = weight_input.getText().toString(); //นำค่าที่รับมาแปลงให้อยู่ในรูปตัวอักษร
                double w = Double.parseDouble(weightText); //แปลงค่าน้ำหนักที่ใส่จากตัวอักษรเป็นทศนิยม

                String heightText = height_input.getText().toString(); //นำค่าที่รับมาแปลงให้อยู่ในรูปตัวอักษร
                double h = Double.parseDouble(heightText);//แปลงค่านส่วนสูงที่ใส่จากตัวอักษรเป็นทศนิยม

                resultBMI = w/((h*0.01)*2);
                String result = String.format(Locale.getDefault(), "%.2f", resultBMI);

                AlertDialog.Builder dialog = new AlertDialog.Builder(AddUserActivity.this);
                dialog.setTitle("Body Mass Index");

                if(resultBMI < 18.5){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : น้ำหนักน้อย/ผอม");
                }else if (resultBMI>=18.5 && resultBMI<=22.90){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : ปกติ(สุขภาพดี)");
                }else if(resultBMI>=23 && resultBMI<=24.90){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : ท้วม/โรคอ้วนระดับ1");
                }else if (resultBMI>=25 && resultBMI<=29.90){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : อ้วน/โรคอ้วนระดับ2");
                }else if (resultBMI >= 30){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : อ้วนมาก/โรคอ้วนระดับ3");
                }

                dialog.setNegativeButton("OK!", null);
                dialog.show();

            }
        });

        Button exitButton = findViewById(R.id.exitChart_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(AddUserActivity.this);
                dialog.setTitle("Exit Body Mass Index");
                dialog.setMessage("Are you sure you want to exit?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // จะไม่ปิดจนกว่จะกดปุ่ม yes
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("No", null);
                dialog.show();
            }

        });

    }
}