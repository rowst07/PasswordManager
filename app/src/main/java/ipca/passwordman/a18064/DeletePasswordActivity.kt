package ipca.passwordman.a18064

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_delete_password.*

class DeletePasswordActivity : AppCompatActivity() {

    private lateinit var editPassView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_password)

        val button = findViewById<Button>(R.id.button_delete)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editPassView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editPassView.text.toString()

                replyIntent.putExtra(WORD_REPLY, word)


                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }

    companion object {
        const val WORD_REPLY = "word"
        const val EXTRA_REPLY = "ipca.passwordman.a18064.REPLY"
    }
}