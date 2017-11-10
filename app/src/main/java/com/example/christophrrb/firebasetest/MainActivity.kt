package com.example.christophrrb.firebasetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            savePerson()
        }
    }

    fun savePerson() {
        val fbName = name.text.toString().trim()
        val fbEmail = email.text.toString().trim()

        if (fbName.isEmpty()) {
            name.error = "Please enter a name."
            return
        } else {

            val ref = FirebaseDatabase.getInstance().getReference("people")
            val personId = ref.push().key

            val person = Person(personId, fbName, fbEmail)
            ref.child(personId).setValue(person).addOnCompleteListener{
                toast("The text was submitted to the Firebase!")
            }
        }
    }
}
