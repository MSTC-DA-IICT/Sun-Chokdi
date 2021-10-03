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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adit.tictactoe.R;

import java.util.List;

import static com.adit.tictactoe.MainActivity.activePlayer;

public class SelectPlayerDialog extends Dialog implements View.OnClickListener {

    private TextView confirm;
    private EditText confirm_player;
    public SelectPlayerDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_player);
        confirm = (TextView) findViewById(R.id.dialogConfirm);
        confirm_player = (EditText) findViewById(R.id.confirm_player);
        confirm.setOnClickListener(this);
        setCancelable(false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogConfirm:
                String player = confirm_player.getText().toString().toLowerCase();
                if(player.equals("x")){
                    activePlayer = 0;
                    confirm_player.getText().clear();
                    dismiss();
                }else if(player.equals("o")){
                    activePlayer = 1;
                    confirm_player.getText().clear();
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "Only two options:X or O", Toast.LENGTH_SHORT).show();
                    activePlayer=-1;
                }
                break;
            default:
                break;
        }
    }

}
