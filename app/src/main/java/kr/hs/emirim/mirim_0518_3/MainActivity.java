package kr.hs.emirim.mirim_0518_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer timer;
    RadioGroup rg;
    TimePicker time;
    CalendarView data;
    TextView textResult;
    int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timer);
        rg = findViewById(R.id.rg);
        time = findViewById(R.id.time);
        data = findViewById(R.id.date);
        textResult = findViewById(R.id.text_result);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnDone = findViewById(R.id.btn_done);
        btnStart.setOnClickListener(btnListener);
        btnDone.setOnClickListener(btnListener);
        rg.setOnCheckedChangeListener(rgListener);
        data.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int Year, int Month, int Day) {
                selectedYear = Year;
                selectedMonth = Month+1;
                selectedDay = Day;
            }
        });
        time.setVisibility(View.INVISIBLE);
        data.setVisibility(View.INVISIBLE);
        timer.setBase(SystemClock.elapsedRealtime());
    }
    RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            time.setVisibility(View.INVISIBLE);
            data.setVisibility(View.INVISIBLE);
            switch (checkedId){
                case R.id.radio_date:
                    data.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_time:
                    time.setVisibility(View.VISIBLE);
                    break;

            }
        }
    };
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    timer.setTextColor(Color.RED);
                    break;
                case  R.id.btn_done:
                    timer.stop();
                    timer.setTextColor(Color.BLUE);
                    textResult.setText(selectedYear+"년 "+selectedMonth+"월 "+selectedDay+"일");
                    textResult.append(time.getCurrentHour()+"시"+time.getCurrentMinute()+"분 예약완료");
                    break;
            }
        }
    };
}