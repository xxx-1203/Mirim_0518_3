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
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer timer;
    RadioGroup rg;
    TimePicker time;
    DatePicker data;
    TextView textResult;
    FrameLayout frame;
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
        rg.setOnCheckedChangeListener(rgListener);
        timer.setOnClickListener(timerListener);
        textResult.setOnLongClickListener(textListener);
        frame =findViewById(R.id.frame);

        data.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int Year, int Month, int Day) {
                selectedYear = Year;
                selectedMonth = Month + 1;
                selectedDay = Day;
            }
        });
        rg.setVisibility(View.INVISIBLE);
        frame.setVisibility(View.INVISIBLE);
    }

    RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            frame.setVisibility(View.VISIBLE);
            time.setVisibility(View.INVISIBLE);
            data.setVisibility(View.INVISIBLE);
            switch (checkedId) {
                case R.id.radio_date:
                    data.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_time:
                    time.setVisibility(View.VISIBLE);
                    break;

            }
        }
    };
    View.OnClickListener timerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rg.setVisibility(view.VISIBLE);
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
            timer.setTextColor(Color.RED);
        }
    };
    View.OnLongClickListener textListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            timer.stop();
            timer.setTextColor(Color.BLUE);
            textResult.setText(selectedYear+"년 "+selectedMonth+"월 "+selectedDay+"일");
            textResult.append(time.getCurrentHour()+"시"+time.getCurrentMinute()+"분 예약완료");
            return true;
        }
    };
}
