package me.skrilltrax.koinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), View {

    private lateinit var etEmail: EditText
    private lateinit var etName: EditText
    private lateinit var btnSave: Button
    private lateinit var btnGet: Button

    private val presenter: Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        btnSave.setOnClickListener {
            if (etEmail.text.isNullOrEmpty() or etName.text.isNullOrEmpty()) {
                Toast.makeText(this, "Empty field", Toast.LENGTH_LONG).show()
            } else {
                presenter.saveDetails(etEmail.text.toString(), etName.text.toString())
            }
        }
        btnGet.setOnClickListener {
            if (etEmail.text.isNullOrEmpty()) {
                Toast.makeText(this, "Empty fields", Toast.LENGTH_LONG).show()
            } else {
                presenter.getDetails(etEmail.text.toString())
            }
        }

    }

    private fun findViews() {
        etEmail = findViewById(R.id.et_email)
        etName = findViewById(R.id.et_name)
        btnSave = findViewById(R.id.btn_save)
        btnGet = findViewById(R.id.btn_get)
    }

    override fun onSaveDetails() {
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
    }

    override fun onGetDetails(email: String, name: String) {
        etName.setText(name)
    }
}
