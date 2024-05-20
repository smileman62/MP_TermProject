package com.example.calendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{

    ArrayList<Date> dayList;

    public CalendarAdapter(ArrayList<Date> dayList) {
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        //날짜 변수에 담기
        Date monthDate = dayList.get(position);
        Calendar currentDate;

        currentDate = Calendar.getInstance();

        //달력 초기화
        Calendar dateCalendar = Calendar.getInstance();

        dateCalendar.setTime(monthDate);

        //현재 년 월
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int currentMonth = currentDate.get(Calendar.MONTH)+1;
        int currentYear = currentDate.get(Calendar.YEAR);

        //넘어온 데이터
        int displayDay = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);

        //비교해서 년, 월 같으면 진한색 아니면 연한색으로 변경
        if(displayMonth == currentMonth && displayYear == currentYear){

            holder.parentView.setBackgroundColor(Color.parseColor("#D5D5D5"));

            //날짜까지 맞으면 색상 표시
            holder.itemView.setBackgroundColor(Color.parseColor("#CEFBC9"));

            if(displayDay == currentDay){
                holder.itemView.setBackgroundColor(Color.parseColor("#f9fbc9"));
            }

        }else{

            holder.parentView.setBackgroundColor(Color.parseColor("#F6F6F6"));
        }

        //날짜 변수에 담기
        int dayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);

        holder.dayText.setText(String.valueOf(dayNo));


        //텍스트 색상 지정
        if( (position + 1) % 7 == 0){ //토요일 파랑

            holder.dayText.setTextColor(Color.BLUE);

        }else if( position == 0 || position % 7 == 0){ //일요일 빨강

            holder.dayText.setTextColor(Color.RED);
        }

        //날짜 클릭 이벤트
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //클릭 시 기능 구현
            }
        });

    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder{

        TextView dayText;

        View parentView;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);

            dayText = itemView.findViewById(R.id.dayText);

            parentView = itemView.findViewById(R.id.parentView);
        }
    }
}