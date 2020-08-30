package com.pjs.soss.ui.sms

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.pjs.soss.R
import kotlinx.android.synthetic.main.activity_s_m_s.*

class SMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_m_s)

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),111)
        }
        else
            receiveMsg()
        button.setOnClickListener {
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(editText.text.toString(), "ME", editText2.text.toString(),null,null)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            receiveMsg()
    }

    private fun receiveMsg() {
        var br = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, pl: Intent?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    for(sms in Telephony.Sms.Intents.getMessagesFromIntent(pl)){
                        editText.setText(sms.originatingAddress)
                        editText2.setText(sms.displayMessageBody)
                    }
                }
            }
        }
        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }
}
