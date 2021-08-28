package com.peluffo.actividad1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LlamadaUsb llamada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
        }
    }

    @Override
    protected void onResume() {
        this.llamada = new LlamadaUsb();
        registerReceiver(this.llamada, new IntentFilter("android.hardware.usb.action.USB_STATE"));
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(llamada);
        super.onPause();
    }

    public class LlamadaUsb extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras().getBoolean("connected")) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:911"));
                startActivity(i);
                Toast.makeText(context, "Llamando al 911", Toast.LENGTH_LONG).show();
            }
        }
    }
}