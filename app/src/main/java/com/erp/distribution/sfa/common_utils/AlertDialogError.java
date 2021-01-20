package com.erp.distribution.sfa.common_utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.erp.distribution.sfa.R;

public class AlertDialogError{

    AlertDialog alertDialog;

    TextView textViewMessage ;
    Button buttonOke;

    public AlertDialogError(Context context) {
        inizialize(context, "Terjadi kesalahan/error");
    }
    public AlertDialogError(Context context, String message) {
        inizialize(context, message);

    }
    public void inizialize(Context context, String message){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.alert_error_dialog, null);

        textViewMessage = view.findViewById(R.id.alert_message);
        buttonOke = view.findViewById(R.id.alert_button_close);

        alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();

        textViewMessage.setText(message);

        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //Supaya Transparant

    }

    public void showDialog(){
        alertDialog.show();
    }
    public void dismiss(){
        alertDialog.dismiss();
    }
    public void setCancelable(boolean isCancelable) {
        alertDialog.setCancelable(isCancelable);
    }

    public Button getButtonOke() {
        return buttonOke;
    }

    public void setButtonOke(Button buttonOke) {
        this.buttonOke = buttonOke;
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public void setAlertDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    public TextView getTextViewMessage() {
        return textViewMessage;
    }

    public void setTextViewMessage(TextView textViewMessage) {
        this.textViewMessage = textViewMessage;
    }
}
