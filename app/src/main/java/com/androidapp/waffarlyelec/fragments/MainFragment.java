package com.androidapp.waffarlyelec.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.waffarlyelec.R;
import com.androidapp.waffarlyelec.activities.MainActivity;
import com.androidapp.waffarlyelec.views.CustomTextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

import de.nitri.gauge.Gauge;

public class MainFragment extends Fragment {

    private View view;
//    private GraphView graphView;
//    private LineGraphSeries<DataPoint> series;
    private CustomTextView consumptionValueTextView;
    private CustomTextView remainingToTopTextView;
    private CustomTextView priceTextView;

    private double totalPrice = 0;
    private double totalConsumption = 0;

    //Speed Test
    Gauge gauge;

    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        initView();

        //createGraph();
        changeValue();

        priceTextView.setText("" + (totalPrice * 30));
        consumptionValueTextView.setText("" + totalConsumption + " Kw");
        remainingToTopTextView.setText(calculateRemaining(totalConsumption) + " Kw");

        if(totalConsumption > 700){
            createNotification();
        }

        return view;
    }

    private void initView(){
        gauge = view.findViewById(R.id.gauge);
        //graphView = view.findViewById(R.id.graph);
        consumptionValueTextView = view.findViewById(R.id.main_conNowValue_textView);
        remainingToTopTextView = view.findViewById(R.id.main_conUntilValue_textView);
        priceTextView = view.findViewById(R.id.main_priceValue_textView);
    }

    private void changeValue(){
        gauge.setValue(50);
    }

//    private void createGraph(){
//
//        int min = 1;
//        int max = 50;
//
//        Random r = new Random();
//        int value;
//        int time;
//
//        series = new LineGraphSeries<>();
//
//        for (int i=0 ; i<30 ; i++){
//
//            value = r.nextInt(max - min + 1) + min;
//            time = i;
//
//            totalConsumption +=value;
//            totalPrice += (value * 0.13);
//
//            series.appendData(new DataPoint(time,value), true, 30);
//        }
//        graphView.addSeries(series);
//
//    }

    private String calculateRemaining(double consumption){

        double remaining;

        if(consumption <= 50){
            remaining = 50 - totalConsumption;
        } else if(consumption <= 200){
            remaining = 200 - totalConsumption;
        } else if(consumption <= 350){
            remaining = 350 - totalConsumption;
        } else if(consumption <= 650){
            remaining = 650 - totalConsumption;
        } else if (consumption <= 1000){
            remaining = 1000 - totalConsumption;
        } else {
            return "انت في اعلى شريحة";
        }
        return "" + remaining;
    }

    private void createNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
        mBuilder.setSmallIcon(R.mipmap.ic_waffarly_app);
        mBuilder.setContentTitle("Waffarly Alert");
        mBuilder.setContentText("انتبه! الاستهلاك الخاص بك مرتفع: " + totalConsumption + " كيلو وات");

        Intent resultIntent = new Intent(getContext() ,MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(getContext().NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

}
