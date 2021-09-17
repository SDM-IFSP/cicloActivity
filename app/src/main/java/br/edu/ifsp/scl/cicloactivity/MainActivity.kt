package br.edu.ifsp.scl.cicloactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.scl.cicloactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainActivity: ActivityMainBinding
    private lateinit var  nomeEt: EditText
    private lateinit var  sobrenomeEt: EditText
    private lateinit var  editarActivityResultLaucher: ActivityResultLauncher<Intent>
    //private lateinit var  sobrenomeEt: EditText

    companion object{
        val CICLO_ACTIVITY: String = "CICLO_ACTIVITY"
        val NOME = "NOME"
        val SOBRENOME = "SOBRENOME"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainActivity.root)

        //actionBar
        supportActionBar?.title = "Pincipal"

        nomeEt = EditText(this)
        nomeEt.width = LinearLayout.LayoutParams.MATCH_PARENT
        nomeEt.height = LinearLayout.LayoutParams.WRAP_CONTENT
        nomeEt.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        nomeEt.hint = "Nome"
        activityMainActivity.root.addView(nomeEt)

        sobrenomeEt = EditText(this)
        with (sobrenomeEt) {
            width = LinearLayout.LayoutParams.MATCH_PARENT
            height = LinearLayout.LayoutParams.WRAP_CONTENT
            inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            hint = "Sobrenome"
            activityMainActivity.root.addView(this)
        }

        savedInstanceState?.getString(NOME).takeIf { it != null  }.apply { nomeEt.setText(this) }

        editarActivityResultLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado?.resultCode == RESULT_OK){
                with (resultado) {
                    data?.getStringExtra(NOME).takeIf { it != null }.let { nomeEt.setText(it) }
                    data?.getStringExtra(SOBRENOME).takeIf { it != null }.run { sobrenomeEt.setText(this) }
                }
            }
    }

    Log.v(CICLO_ACTIVITY, "onCreate: Iniciando ciclo de vida COMPLETO")
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
        Log.v(CICLO_ACTIVITY, "onDestroy: Finalizando ciclo de vida COMPLETO")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(CICLO_ACTIVITY, "onRestart: Preparando execução do onStart")
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
        //savedInstanceState.getString(NOME).takeIf { it !=  null} .apply { nomeEt.setText(this) }
        //nomeEt.setText(savedInstanceState.getString(NOME))
        //savedInstanceState.getString(NOME)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.editarMi -> {
                val editarIntent: Intent  = Intent(this, EditarActivity:: class.java)
                //startActivity(editarIntent)
                editarActivityResultLaucher.launch(editarIntent)
                true
            }
            else -> {
                false
            }
        }
    }
}