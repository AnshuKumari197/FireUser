package com.coding.a28fireusersystem

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainActivity : AppCompatActivity() {
    var db: FirebaseDatabase = FirebaseDatabase.getInstance()
    var rootRef: DatabaseReference = db.getReference()
    var userRef: DatabaseReference = rootRef.child("Users")
    var username: EditText? = null
    var name: EditText? = null
    var email: EditText? = null
    var button: Button? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById<View>(R.id.username) as EditText?
        name = findViewById<View>(R.id.name) as EditText?
        email = findViewById<View>(R.id.email) as EditText?
        button = findViewById<View>(R.id.button) as Button?
        button!!.setOnClickListener {
            val myUserName: String = username.getText().toString().trim { it <= ' ' }
            val myName: String = name.getText().toString().trim { it <= ' ' }
            val myEmail: String = email.getText().toString().trim { it <= ' ' }


//                theen me mujhe error msg show karna tha agar field empty chora hai to
//                but theen pe error show karne ke liye mujhe theen baar same code likhna para
            if (myUserName.length == 0) {
                username.setError("No value")
            } else {
                if (myName.length == 0) {
                    name.setError("No value")
                } else {
                    if (myEmail.length == 0) {
                        email.setError("No value")
                    } else {
                        val userMap = HashMap<String, String>()
                        userMap.put("UserName", myUserName)
                        userMap.put("Name", myName)
                        userMap.put("Email", myEmail)
                        userRef.push().setValue(userMap)
                            .addOnCompleteListener(object : OnCompleteListener<Void?> {
                                override fun onComplete(task: Task<Void>) {
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "SUCCESS",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Failure",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            })
                    }
                }
            }
        }
    }
}