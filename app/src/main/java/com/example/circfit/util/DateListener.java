package com.example.circfit.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.ZoneId;

import static com.example.circfit.util.MyDateUtil.dtf;

public class DateListener {
    String tag = DateListener.class.getSimpleName();

    public interface MyInterface {
        void setText(LocalDate localDate);
    }

    EditText editTextDate;
    MyInterface myInterface;

    LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

    public DateListener(EditText dateInput, Context context, MyInterface myInterface) {
        this.editTextDate = dateInput;
        this.myInterface = myInterface;

        this.editTextDate.setOnClickListener(v ->
                new DatePickerDialog(context, onDateSetListener,
                        localDate.getYear(),
                        localDate.getMonthValue() -1,
                        localDate.getDayOfMonth())
                        .show());
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = (datePicker, year, monthOfYear, dayOfMonth) -> {
        Log.d(tag, "Setting date listener");
        LocalDate ld = LocalDate.of(year,monthOfYear +1 ,dayOfMonth);
        Log.d(tag, "Setting date listener"  + ld.format(dtf));
        editTextDate.setText(ld.format(dtf));
        myInterface.setText(ld);
    };

}
