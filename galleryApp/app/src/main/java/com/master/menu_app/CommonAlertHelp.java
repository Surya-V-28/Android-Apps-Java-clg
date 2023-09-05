package com.master.menu_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


public class CommonAlertHelp  {
    public static void showAlert(Context context) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(context);
        builder.setMessage("You can Feel Free to call use");
        builder.setTitle("Help");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes",(DialogInterface.OnClickListener) (dialog, which) ->
        {
            Toast.makeText(context, "Thank you you will get a mail", Toast.LENGTH_SHORT).show();

        });

        builder.setNegativeButton("No",(DialogInterface.OnClickListener) (dialog,which)->
        {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
