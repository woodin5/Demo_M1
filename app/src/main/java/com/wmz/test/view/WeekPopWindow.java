package com.wmz.test.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wmz.test.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by miles
 * 15/12/18.
 */
public class WeekPopWindow extends PopupWindow
        implements View.OnClickListener, PointLineView.onDateScrolling {

    private Context context;
    private TextView txtTitle;
    private TextView txtViewDay;
    private PointLineView lineView;
    private int TYPE = 0;
    private List<Integer> valueResult;

    private String unitStep;
    private String unitHour;
    private String unitMin;
    private int indexDay;

    public WeekPopWindow(Context context) {
        this.context = context;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        View rootView = LayoutInflater.from(context).inflate(R.layout.popwindow_week, null);
        txtTitle = (TextView) rootView.findViewById(R.id.txtTitle);
        txtViewDay = (TextView) rootView.findViewById(R.id.txtViewDay);
        lineView = (PointLineView) rootView.findViewById(R.id.lineView);
        int color = context.getResources().getColor(android.R.color.holo_red_light);
        lineView.setGraphColor(color);
        lineView.setDateScrollingLinstener(this);
        //
        txtViewDay.setOnClickListener(this);
        //
        setContentView(rootView);
        setWidth((int) (dm.widthPixels * 0.9f));
        setHeight((int) (dm.heightPixels / 2 * 0.8f));
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        valueResult = new ArrayList<>();

//        unitHour = context.getString(R.string.unit_hour);
//        unitMin = context.getString(R.string.unit_minute);
//        unitStep = context.getString(R.string.unit_step);
    }

    public void setTypeValue(int type) {
        this.TYPE = type;
        Calendar calendar = Calendar.getInstance();
        String over = DateUtil.getCalendarDate(calendar);
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        String start = DateUtil.getCalendarDate(calendar);


        int index = 6;

//        Calendar calendar = Calendar.getInstance();
//        calendar = DateUtil.getWeekStart(calendar);
//        String start = DateUtil.getCalendarDate(calendar);
//        calendar.add(Calendar.DAY_OF_YEAR, 6);
//        String over = DateUtil.getCalendarDate(calendar);
//        int index = DateUtil.getWeekIndex(Calendar.getInstance());
        valueResult.clear();
//        if (type == 1) { // 黑夜
//            SleepDao sleepDao = new SleepDao(context);
//            valueResult.addAll(sleepDao.getDataByWeekTime(start, over));
//        } else { // 白天
//            SportDao sportDao = new SportDao(context);
//            valueResult.addAll(sportDao.getDataByWeekSteps(start, over));
//        }
        lineView.setDatas(valueResult);
        lineView.setCurrentItem(index);
    }

    public void showWindow(View view) {
        showAsDropDown(view, -(getWidth() - view.getWidth()) / 2, 0);
    }

    @Override
    public void onClick(View v) {
        if (txtViewDay == v && onChangeDateListener != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -6);
            calendar.add(Calendar.DAY_OF_YEAR, this.indexDay);

//            Calendar calendar = Calendar.getInstance();
//            calendar = DateUtil.getWeekStart(calendar);
//            calendar.add(Calendar.DAY_OF_YEAR, this.indexDay);
            onChangeDateListener.onChangeDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH));
        }
        dismiss();
    }

    private OnChangeDateListener onChangeDateListener;

    public OnChangeDateListener getOnChangeDateListener() {
        return onChangeDateListener;
    }

    public void setOnChangeDateListener(OnChangeDateListener onChangeDateListener) {
        this.onChangeDateListener = onChangeDateListener;
    }

    @Override
    public void onScrolling(int index) {
        if (valueResult == null || valueResult.size() <= 0) return;
      //  LogUtil.e("index---->>>"+index);
        this.indexDay = index;
        int value = valueResult.get(index);
        if (TYPE == 1) { // 黑夜
            txtTitle.setText((value / 60) + unitHour + (value % 60) + unitMin);
        } else { // 白天
            txtTitle.setText(value + unitStep);
        }
    }

    public interface OnChangeDateListener {
        void onChangeDate(int year, int month, int day);
    }
}
