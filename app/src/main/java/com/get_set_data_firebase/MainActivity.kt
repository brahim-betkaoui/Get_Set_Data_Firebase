package com.get_set_data_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var database = FirebaseDatabase.getInstance().reference

        button.setOnClickListener{
            var empno = editText_1.text.toString().toInt()
            var ename = editText_2.text.toString()
            var esal = editText_3.text.toString().toInt()

            database.child(empno.toString()).setValue(Employee(ename, esal))
        }

        //Getdata
        var getdata = object : ValueEventListener{
            override fun onCancelled(p0 : DatabaseError){
            }

            override fun onDataChange(p0: DataSnapshot){
                var sb = StringBuilder()
                for (i in p0.children){
                    var ename1 = i.child("ename").getValue()
                    var esal1 = i.child("esal").getValue()
                    sb.append("${i.key} $ename1  $esal1\n")
                }

                textView.setText(sb)
            }
        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)


    }
}