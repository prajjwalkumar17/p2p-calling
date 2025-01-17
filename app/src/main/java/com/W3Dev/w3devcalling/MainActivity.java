package com.W3Dev.w3devcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.W3Dev.w3devcalling.background.DataChecker;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button2, button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        RequestPermissions();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), cameraRenderActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getApplicationContext(), DataChecker.class));
                startActivity(new Intent(getApplicationContext(), CompleteFunctionalityActivity.class));
            }
        });
    }

    private void RequestPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "Permission Denied\n" + deniedPermissions.toString() + "\nPlease grant all the permissions to move forward", Toast.LENGTH_SHORT).show();
                RequestPermissions();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.MODIFY_AUDIO_SETTINGS
                        , Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.RECORD_AUDIO, Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.BLUETOOTH)
                .check();
    }

}




