package com.example.farm_monitoring;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MonitoringFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MonitoringFragment() {
        // Required empty public constructor
    }

    public static MonitoringFragment newInstance(String param1, String param2) {
        MonitoringFragment fragment = new MonitoringFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring, container, false);

        LineChart lineChart1 = view.findViewById(R.id.lineChart);
        lineChart1.setTouchEnabled(false);

        XAxis xAxis = lineChart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(8,true);
//        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//
//                SimpleDateFormat sdf = new SimpleDateFormat("H");
//                Log.d("확인",value +"");
////                long emissionsMilliSince1970Time = ((long) value) * 1000;
////
////                // Show time in local version
////                Date timeMilliseconds = new Date(emissionsMilliSince1970Time);
////                DateFormat dateTimeFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
//
////                return dateTimeFormat.format(timeMilliseconds) + "시";
//                return sdf.format(value) + "시";
//            }
//        });

        YAxis leftAxis = lineChart1.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
//        leftAxis.setLabelCount(10,true);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
//        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = lineChart1.getAxisRight();
        rightAxis.setEnabled(false); //비활성화


        List<Entry> entry_chart = new ArrayList<>();

        LineData charData = new LineData();

        entry_chart.add(new Entry(0,50));
        entry_chart.add(new Entry(1,60));
        entry_chart.add(new Entry(2,40));
        entry_chart.add(new Entry(3,70));

        LineDataSet lineDataSet = new LineDataSet(entry_chart, "차트1");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(3f);
//        lineDataSet.setFillAlpha(65);
//        lineDataSet.setFillColor(Color.RED);
//        lineDataSet.setDrawCircleHole(false);
//        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
//        lineDataSet.setCircleRadius(6); // 곡률
        lineChart1.animateY(1000);



        charData.addDataSet(lineDataSet);

        List<String> lables = new ArrayList<>();
        lables.add("9시");
        lables.add("12시");
        lables.add("15시");
        lables.add("18시");
        lables.add("11시");
        lables.add("13시");
        lables.add("14시");
        lables.add("16시");

        lineChart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(lables));
//        lineChart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(getAreaCount()));
        lineChart1.setDescription(null);

        lineChart1.setData(charData);


        lineChart1.invalidate();

        return view;
    }

//    public ArrayList<String> getAreaCount() {
//        ArrayList<String> label= new ArrayList<>();
//        for (int i= 0; i < areaList.size(); i++)
//            label.add(areaList.get(i).getTopicName());
//        return label;
//    }

}