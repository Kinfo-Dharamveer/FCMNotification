package com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging
import com.myapplication.utils.Constants
import com.orhanobut.hawk.Hawk
import android.widget.Toast
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener




class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Hawk.init(this).build();

        try {
            val token = Hawk.get(Constants.FCM_TOKEN, "")
            Log.d("Token", token)


        } catch (e: Exception) {
            Log.e("Token null", e.toString())
        }



        FirebaseMessaging.getInstance().subscribeToTopic("all")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Log.d("TAG", msg)
               // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }



        val extras = intent!!.extras

        if (extras != null) {
            if (extras.containsKey("keyData1")) {

                // extract the extra-data in the Notification
                val title = extras.getString("keyData1")
                val message = extras.getString("keyData2")

                showPopUp(title, message)

            }
        }
    }

    private fun showPopUp(title: String?, message: String?) {

        val dialogBuilder = AlertDialog.Builder(this)

        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.pop_up, null)
        dialogBuilder.setView(dialogView)

        val mTitle = dialogView.findViewById(R.id.textView_title) as TextView
        val mMessage = dialogView.findViewById(R.id.textViewMessage) as TextView
        val okButton = dialogView.findViewById(R.id.button_ok) as Button

        mTitle.text = title
        mMessage.text = message


        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        okButton.setOnClickListener {
            alertDialog.dismiss()
        }


    }


}
