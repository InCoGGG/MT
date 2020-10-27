package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner gameSize;
    Button startingWait;
    Button numberRange;
    Switch timerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button returnBackBtn = findViewById(R.id.settingReturn);
        Button exitBtn = findViewById(R.id.settingExitGame);
        gameSize = findViewById(R.id.settingGameSize);
        startingWait = findViewById(R.id.settingStartingWait);
        numberRange = findViewById(R.id.settingNumberRan);
        timerSwitch = findViewById(R.id.settingTimerSwitch);

        updateValues();

        //Setting the on click listeners
        returnBackBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);

        //Getting changes from the spinner
        gameSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gameSize = parent.getItemAtPosition(position).toString();
                //Here we are checking the item changed and saving them to the sharePref
                SharePrefHelper sharePrefHelper = new SharePrefHelper(SettingsActivity.this);
                sharePrefHelper.setGameSize(gameSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing
            }
        });
        startingWait.setOnClickListener(this);
        numberRange.setOnClickListener(this);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharePrefHelper sharePrefHelper = new SharePrefHelper(SettingsActivity.this);
                sharePrefHelper.setTimer(b);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.settingReturn : {
                finish();
                break;
            }

            case R.id.settingExitGame : {
                //Exit the game/app

                break;
            }

            case R.id.settingStartingWait : {
                SharePrefHelper sharePrefHelper = new SharePrefHelper(SettingsActivity.this);
                String sw = sharePrefHelper.getStartingWait();
                showAlertWait(5,30, Integer.parseInt(sw), Settings.STARTING_WAIT);
                break;
            }

            case R.id.settingNumberRan : {
                SharePrefHelper sharePrefHelper = new SharePrefHelper(SettingsActivity.this);
                String nr = sharePrefHelper.getNumberRange();
                showAlertNumber(100,1000, Integer.parseInt(nr), Settings.NUMBER_RANGE);
                break;
            }


        }
    }

    private void showAlertWait(final int min, final int max, final int previousValue, final Settings settings ){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Enter Value");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String edtTxt = edt.getText().toString().trim();
                if (edtTxt.isEmpty()){
                    edtTxt = "0";
                }
                int i = Integer.parseInt(edtTxt);
                if (i >= min && i <= max){
                    SharePrefHelper sharePrefHelper = new SharePrefHelper(SettingsActivity.this);
                    if (settings == Settings.STARTING_WAIT){
                        sharePrefHelper.setStartingWait(edtTxt);
                        updateValues();
                    } else if(settings == Settings.NUMBER_RANGE) {
                        sharePrefHelper.setNumberRange(edtTxt);
                        updateValues();
                    }
                    dialog.dismiss();
                } else {
                    String message = "Error: Make sure the value is between " + min + " and " + max;
                    Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_LONG ).show();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                dialog.dismiss();
                updateValues();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void showAlertNumber(final int min, final int max, final int previousValue, final Settings settings ){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Enter Value");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String edtTxt = edt.getText().toString().trim();
                if (edtTxt.isEmpty()){
                    edtTxt = "0";
                }
                int i = Integer.parseInt(edtTxt);
                if (i >= min && i <= max){
                    SharePrefHelper sharePrefHelper = new SharePrefHelper(SettingsActivity.this);
                    if (settings == Settings.STARTING_WAIT){
                        sharePrefHelper.setStartingWait(edtTxt);
                        updateValues();
                    } else if(settings == Settings.NUMBER_RANGE) {
                        sharePrefHelper.setNumberRange(edtTxt);
                        updateValues();
                    }
                    dialog.dismiss();
                } else {
                    String message = "Error: Make sure the value is between " + min + " and " + max;
                    Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_LONG ).show();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                dialog.dismiss();
                updateValues();
            }
        });
        dialogBuilder.show();
    }

    void updateValues(){
        SharePrefHelper sharePrefHelper = new SharePrefHelper(this.getApplicationContext());
        String gameSizeStr = sharePrefHelper.getGameSize();
        String startingWaitStr = sharePrefHelper.getStartingWait();
        String numberRangeStr = sharePrefHelper.getNumberRange();
        Boolean timer = sharePrefHelper.getTimer();

        switch (gameSizeStr){
            case "4" : {
                this.gameSize.setSelection(0);
                break;
            }

            case "5" : {
                this.gameSize.setSelection(1);
                break;
            }

            case "6" : {
                this.gameSize.setSelection(2);
                break;
            }
        }
        this.startingWait.setText(startingWaitStr);
        this.numberRange.setText(numberRangeStr);
        this.timerSwitch.setChecked(timer);
    }

    enum Settings {
        STARTING_WAIT,
        NUMBER_RANGE
    }
}
