package th.ac.su.bodymassindex_bmi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import th.ac.su.bodymassindex_bmi.model.AddUserActivity;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> data_name, data_id, data_weight, data_height;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() { //กดปุ่ม add_button จะไปยังหน้า AddUseActivity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this); //สร้างฐานข้อมูลไว้ที่หน้า MainActivity

        data_name = new ArrayList<>();
        data_id = new ArrayList<>();        //สร้าง ArrayList ให้กับข้อมูลที่ต้องการเก็บค่า
        data_weight = new ArrayList<>();
        data_height = new ArrayList<>();

        storeDataInArrays();

        userAdapter = new UserAdapter(MainActivity.this,this, data_name, data_id, data_weight,
                data_height);
        recyclerView.setAdapter(userAdapter); //เซ็ตค่าที่จะแสดงลงในลิสต์ (RecyclerView)
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); //เซ็ตหน้า MainActivity ให้แสดงเป็นลิสค์ (RecyclerView)

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                data_name.add(cursor.getString(0));
                data_id.add(cursor.getString(1));
                data_weight.add(cursor.getString(2));
                data_height.add(cursor.getString(3));
            }
        }
    }

}