package ipca.passwordman.a18064

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewPasswordActivity : AppCompatActivity() {

    private lateinit var editPassWordView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)
        editPassWordView = findViewById(R.id.edit_pass)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editPassWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val password = editPassWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, password)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "ipca.passwordman.a18064.REPLY"
    }
}