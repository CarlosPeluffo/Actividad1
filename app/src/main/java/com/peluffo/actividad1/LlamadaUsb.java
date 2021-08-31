package com.peluffo.actividad1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class LlamadaUsb extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getExtras().getBoolean("connected")) {
            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:911"));
            context.startActivity(i);
            Toast.makeText(context, "Llamando al 911", Toast.LENGTH_LONG).show();
        }
    }
}