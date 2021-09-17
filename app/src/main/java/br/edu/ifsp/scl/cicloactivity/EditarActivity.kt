package br.edu.ifsp.scl.cicloactivity

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.cicloactivity.databinding.ActivityEditarBinding
import br.edu.ifsp.scl.cicloactivity.databinding.ActivityMainBinding

class EditarActivity : AppCompatActivity() {
    private lateinit var activityEditarBinding: ActivityEditarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditarBinding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(activityEditarBinding.root)

        // ActionBar
        supportActionBar?.title = "Editar"

        activityEditarBinding.salvarBt.setOnClickListener {
            val retornoIntent: Intent = Intent()
            with (activityEditarBinding) {
                retornoIntent.putExtra(MainActivity.NOME, nomeEt.text.toString())
                retornoIntent.putExtra(MainActivity.SOBRENOME, sobrenomeEt.text.toString())
            }
            setResult(RESULT_OK, retornoIntent)
            finish()
        }
    }
}