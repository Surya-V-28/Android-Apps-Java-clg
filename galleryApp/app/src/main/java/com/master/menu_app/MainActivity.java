package com.master.menu_app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    Button  button,button2;
    ImageView imageView;
    MenuBuilder menuBuilder;
    private  int requestCode = 1,requestCode2 = 2;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =  findViewById(R.id.button);
        imageView = findViewById(R.id.imageiew);

        button2 = findViewById(R.id.button2);
        menuBuilder = new MenuBuilder(MainActivity.this);
        MenuInflater inflater = new MenuInflater(MainActivity.this);
        inflater.inflate(R.menu.context_menu,menuBuilder);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuPopupHelper smallpop = new MenuPopupHelper(MainActivity.this,menuBuilder,
                        v);
                smallpop.setForceShowIcon(true);
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.downloadImage:
                                Toast.makeText(MainActivity.this,"home",Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.share:
                                Toast.makeText(MainActivity.this, "sharing", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }

                });
                smallpop.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatePicker.class);
                startActivity(intent);
            }
        });

         button.setOnClickListener(view -> {
             PopupMenu popupMenu = new PopupMenu(MainActivity.this, button);
             popupMenu.setForceShowIcon(true);
             popupMenu.getMenuInflater().inflate(R.menu.context_menu, popupMenu.getMenu());
             popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                 @Override
                 public boolean onMenuItemClick(MenuItem menuItem) {
                     Toast.makeText(MainActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                     return true;
                 }
             });
             popupMenu.show();
         });



    }
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo MenuInfo){
       super.onCreateContextMenu(contextMenu,v,MenuInfo);
       MenuInflater menuInflater = getMenuInflater();
       menuInflater.inflate(R.menu.context_real_menu,contextMenu);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case (R.id.home):
                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(this,HomePage.class);
                startActivity(intent);
                return true;
            case (R.id.back) :
                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                Intent intent1 =  new Intent(this,MainActivity.class);
                startActivity(intent1);
            case (R.id.Help) :
                CommonAlertHelp.showAlert(this);

            default :
                return super.onOptionsItemSelected(item);

        }

    }

    @SuppressLint("NonConstantResourceId")
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.veiw:
                Toast.makeText(this, "Moving to Home", Toast.LENGTH_LONG).show();
                return true;
            case R.id.downloadImage:
                if(ContextCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED & ContextCompat.checkSelfPermission(this,Manifest
                        .permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                ) {

                } else {
                    requestpermission();
                }
                Uri uri = Uri.parse("https://images.indianexpress.com/2019/12/SALMAN-KHAN-CHENNAI-759.jpeg");
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                request.setTitle("Downloading").setDescription("Downloaind by app").
                        allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/images/"+"image-download"+".png");
                request.setMimeType("*/*");
                downloadManager.enqueue(request);
                Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                Toast.makeText(this, "Sharing the Image", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
    public void requestpermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this).setTitle("Request Premission").
                    setMessage("To Download the image need premision to access memory")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                            ActivityCompat.requestPermissions(MainActivity.this,new String[
                                    ] {Manifest.permission.INTERNET}, requestCode2);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
        }

    }
}