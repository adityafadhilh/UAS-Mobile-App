package id.ac.umn.uas_mobileapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    Date date;
    Button dateExpenseBtn, dateIncomeBtn;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Context context = getContext();
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        if(context.equals(IncomeFragment.class)){
            dateIncomeBtn = getView().findViewById(R.id.dateIncome);
            dateIncomeBtn.setText(day + "/" + month + "/" + year);
        }else if(context.equals(ExpenseFragment.class)){
            dateExpenseBtn = getView().findViewById(R.id.date);
            dateExpenseBtn.setText(day + "/" + month + "/" + year);
        }
    }
}
