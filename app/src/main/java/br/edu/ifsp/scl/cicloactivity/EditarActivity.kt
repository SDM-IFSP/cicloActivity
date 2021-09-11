package br.edu.ifsp.scl.cicloactivity

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.cicloactivity.databinding.ActivityMainBinding

class EditarActivity : AppCompatActivity() {
    private lateinit var activityEditarBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditarBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityEditarBinding.root)

        supportActionBar?.title = "Editar"

        activityEditarBinding.
    }
}