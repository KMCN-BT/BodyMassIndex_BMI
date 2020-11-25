package th.ac.su.bodymassindex_bmi.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import th.ac.su.bodymassindex_bmi.R;

public class BmiChartActivity extends AppCompatActivity {

    Button exit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);

        exit_button = findViewById(R.id.exitChart_button);

        exit_button.setOnClickListener(new View.OnClickListener() { //กดปุ่ม exit_button จะไปยังหน้า AddUserActivity
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (BmiChartActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }
}