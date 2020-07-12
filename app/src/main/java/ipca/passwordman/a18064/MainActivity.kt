package ipca.passwordman.a18064

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipca.passwordman.a18064.PassDao as PassDao

class MainActivity : AppCompatActivity() {
    private lateinit var passwordViewModel: PassViewModel
    private val newPasswordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PassListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        passwordViewModel = ViewModelProvider(this).get(PassViewModel::class.java)
        passwordViewModel.allPass.observe(this, Observer { pass ->
            pass?.let { adapter.setPasswords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPasswordActivity::class.java)
            startActivityForResult(intent, newPasswordActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newPasswordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewPasswordActivity.EXTRA_REPLY)?.let {
                val Password = Password(it)
                passwordViewModel.insert(Password)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}