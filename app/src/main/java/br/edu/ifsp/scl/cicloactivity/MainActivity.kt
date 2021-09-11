package br.edu.ifsp.scl.cicloactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import br.edu.ifsp.scl.cicloactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainActivity: ActivityMainBinding

    companion object{
        val CICLO_ACTIVITY  = "CICLO_ACTIVITY"
        val NOME = "Paula"
    }

    private lateinit var  nomeEt: EditText
    //private lateinit var  sobrenomeEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainActivity.root)

        nomeEt = EditText(this)
        nomeEt.width = LinearLayout.LayoutParams.MATCH_PARENT
        nomeEt.height = LinearLayout.LayoutParams.WRAP_CONTENT
        nomeEt.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        nomeEt.hint = "NOME"
        activityMainActivity.root.addView(nomeEt)

        Log.v(CICLO_ACTIVITY, "onStart: Iniciando ciclo de vida em PRIMEIO PLANO")
    }

    override fun onStart() {
        super.onStart()
        Log.v(CICLO_ACTIVITY, "onStart: Iniciando ciclo de vida em VISÍVEL")
    }

    override fun onResume() {
        super.onResume()
        Log.v(CICLO_ACTIVITY, "onResume: Iniciando ciclo de vida em PRIMEIO PLANO")
    }

    override fun onPause() {
        super.onPause()
        Log.v(CICLO_ACTIVITY, "onPause: Finalizando ciclo de vida em PRIMEIO PLANO")
    }

    override fun onStop() {
        super.onStop()
        Log.v(CICLO_ACTIVITY, "onStop: Finalizando ciclo de vida VISÍVEL")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(CICLO_ACTIVITY, "onDestroy: Finalizando ciclo de vida completo")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(CICLO_ACTIVITY, "onRestart: Preparando execução do n Start")
    }


    //salvando dados da instância
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(NOME, nomeEt.text.toString())
    }

    //restaurando dados de instância
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
//        val nome: String? = savedInstanceState.getString(NOME)
//        if (nome !=  null) {
//            nomeEt.setText(nome)
//        }
        savedInstanceState.getString(NOME).takeIf { it !=  null} .apply { nomeEt.setText(this) }
        //nomeEt.setText(savedInstanceState.getString(NOME))
        //savedInstanceState.getString(NOME)
    }
}