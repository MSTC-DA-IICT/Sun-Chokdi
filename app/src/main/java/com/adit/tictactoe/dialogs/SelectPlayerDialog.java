package com.adit.tictactoe.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adit.tictactoe.R;

import java.util.List;

import static com.adit.tictactoe.MainActivity.activePlayer;

public class SelectPlayerDialog extends Dialog implements View.OnClickListener {

    private Button btnX,btnO;
    public SelectPlayerDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_player);
        btnX = (Button) findViewById(R.id.btn_X);
        btnO = (Button) findViewById(R.id.btn_O);
        btnX.setOnClickListener(this);
        btnO.setOnClickListener(this);
        setCancelable(false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_X:
                activePlayer = 0;
                dismiss();
                break;
            case R.id.btn_O:
                activePlayer = 1;
                dismiss();
                break;
            default:
                break;
        }
    }


}

