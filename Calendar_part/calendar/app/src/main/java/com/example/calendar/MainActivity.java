package com.example.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    TextView monthYearText; //년월 텍스트뷰

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //초기화
        monthYearText = findViewById(R.id.monthYearText);
        ImageButton preBtn = findViewById(R.id.pre_btn);
        ImageButton nextBtn = findViewById(R.id.next_btn);
        recyclerView = findViewById(R.id.recyclerView);

        //현재 날짜
        CalendarUtil.selectedDate = Calendar.getInstance();


        //화면 설정
        setMonthView();

        //이전 달 버튼 이벤트
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //-1한 월을 넣어준다. (2월 -> 1월)
                CalendarUtil.selectedDate.add(Calendar.WEEK_OF_MONTH, -1);// -1
                setMonthView();
            }
        });

        //다음 달 버튼 이벤트
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //+1한 월을 넣어준다.(2월 -> 3월)
                CalendarUtil.selectedDate.add(Calendar.WEEK_OF_MONTH, 1); //+1
                setMonthView();
            }
        });
    }//onCreate

    //날짜 타입 설정(4월 2020)
    private String monthYearFromDate(Calendar calendar){

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        String monthYear = year + "년 "+ month + "월 ";

        return monthYear;
    }


    //화면 설정
    private void setMonthView(){

        //년월 텍스트뷰 셋팅
        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));

        //해당 월 날짜 가져오기
        ArrayList<Date> dayList = daysInMonthArray();

        //어뎁터 데이터 적용
        CalendarAdapter adapter = new CalendarAdapter(dayList);

        //레이아웃 설정(열 7개)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);

        //레이아웃 적용
        recyclerView.setLayoutManager(manager);

        //어뎁터 적용
        recyclerView.setAdapter(adapter);
    }

    //날짜 생성
    private ArrayList<Date> daysInMonthArray(){

        ArrayList<Date> dayList = new ArrayList<>();

        //날짜 복사해서 변수 생성
        Calendar monthCalendar = (Calendar) CalendarUtil.selectedDate.clone();


        //1일로 셋팅 (4월 1일)
        monthCalendar.set(Calendar.DAY_OF_WEEK, 1);

        //요일 가져와서 -1 일요일:1, 월요일:2
        int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) -1;

        //날짜 셋팅 (-5일전)
        monthCalendar.add(Calendar.DAY_OF_WEEK, -firstDayOfMonth);

        //42전까지 반복
        while(dayList.size() < 7){

            //리스트에 날짜 등록
            dayList.add(monthCalendar.getTime());

            //1일씩 늘린 날짜로 변경 1일 -> 2일 -> 3일
            monthCalendar.add(Calendar.DAY_OF_WEEK, 1);
        }


        return dayList;
    }
}
