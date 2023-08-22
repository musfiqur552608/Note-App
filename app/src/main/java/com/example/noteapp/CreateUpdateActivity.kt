package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityCreateUpdateBinding
import com.example.noteapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUpdateBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        database = Firebase.database.reference
        val key = database.child("Notes").push().key
        binding.addBtn.setOnClickListener {
            database.child("Notes").child(key!!).setValue(Data(binding.noteETxt.text.toString(),key)).addOnCompleteListener {
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Toast.makeText(this, task.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
            binding.noteETxt.setText("")
        }
    }

}