package com.example.kazu.placeapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTimeText;
    TextView mDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button input_button = (Button) findViewById(R.id.inputButton);
        input_button.setOnClickListener(this);

        Button finish_button = (Button) findViewById(R.id.finishButton);
        finish_button.setOnClickListener(this);

        mTimeText = (TextView) findViewById(R.id.timeText);
        mDateText = (TextView) findViewById(R.id.dateText);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.inputButton) {
            showTimePickerDialog();
            showDatePickerDialog();
        } else {
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        // AlertDialog.Builderクラスを使ってAlertDialogの準備をする
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("入力完了");
        alertDialogBuilder.setMessage("以上でよろしいでしょうか？");

        // 肯定ボタンに表示される文字列、押したときのリスナーを設定する
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("UI_PARTS", "OKボタン");
                    }
                });

        // 中立ボタンに表示される文字列、押したときのリスナーを設定する
        alertDialogBuilder.setNeutralButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("UI_PARTS", "CANCELボタン");
                    }
                });

        // AlertDialogを作成して表示する
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("UI-PARTS", String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
                        mTimeText.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
                    }
                },
                13, // 初期値（時間）
                0,  // 初期値（分）
                true);
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.d("UI-PARTS", String.valueOf(year) + "/" + String.valueOf(monthOfYear)+ "/" + String.valueOf(dayOfMonth));
                        mDateText.setText(String.valueOf(year) + "/" + String.valueOf(monthOfYear)+ "/" + String.valueOf(dayOfMonth));

                    }
                },
                2017,
                3,
                1);
        datePickerDialog.show();
    }
}