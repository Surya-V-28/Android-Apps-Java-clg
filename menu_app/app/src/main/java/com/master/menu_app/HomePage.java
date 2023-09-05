package com.master.menu_app;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class HomePage extends AppCompatActivity {
    private  int requestcode = 1;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_home);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case (R.id.back) :
                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                Intent intent1 =  new Intent(this,MainActivity.class);
                startActivity(intent1);
            case (R.id.Help) :
                showAlert(HomePage.this);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                if (ActivityCompat.checkSelfPermission(HomePage.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestAcess();
                    ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE},requestcode);
                }
                callIntent.setData(Uri.parse("tel:91-637-952-1173"));
                startActivity(callIntent);
            default :
                return super.onOptionsItemSelected(item);
        }
    }
    public void showAlert(Context context) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(context);
        builder.setMessage("You can Feel Free to call use");
        builder.setTitle("Help");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes",(DialogInterface.OnClickListener) (dialog,which) -> {
            finish();
        });
        builder.setNegativeButton("No",(DialogInterface.OnClickListener) (dialog,which)->{
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void requestAcess() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){

        }
    }

}
