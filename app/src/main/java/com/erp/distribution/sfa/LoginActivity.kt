package com.erp.distribution.sfa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.erp.distribution.sfa.databinding.ActivityLoginBinding
import com.erp.distribution.sfa.security_model.FUser
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.find


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName

    var itemHeader: FUser = FUser()

    val rellay1: RelativeLayout by lazy { findViewById(R.id.rellay1) }
    val rellay2: RelativeLayout  by lazy { findViewById(R.id.rellay2) }

        val editTextUsername: EditText by lazy { findViewById(R.id.username) }

    val editTextPassword: EditText by lazy { findViewById(R.id.password) }

    val btnLoginNow: Button by lazy { findViewById(R.id.btn_login_now) }

    var handler = Handler()
    var runnable = Runnable {
        rellay1.setVisibility(View.VISIBLE)
        rellay2.setVisibility(View.VISIBLE)
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        handler.postDelayed(runnable, 2000) //2000 is the timeout for the splash
        initialize()
        /**
         * Kebetulan Belum menggunakan Extra Parceable
         */
        val intent: Intent = getIntent()
//        if (intent.hasExtra(EXTRA_ID)) {
//            setTitle("Add User");
//            FUser note = (FUser) intent.getSerializableExtra(EXTRA_OBJECT);
//            editTextTitle.setText(note.getTitle());
//        } else {
//            setTitle("Edit User");
//        }

    }

    fun initialize() {
        btnLoginNow.setOnClickListener { v: View? -> loginNow(v) }
    }

    fun loginNow(v: View?) {

        readBinderToItem()
        if (itemHeader.username.trim().isNullOrBlank() || itemHeader.password.trim().isNullOrBlank()) {
            Toast.makeText(this, "Username atau Password tidak boleh kosong!", Toast.LENGTH_SHORT)
                .show()
        }else {
//            Toast.makeText(this, itemHeader.username + " & " + itemHeader.password, Toast.LENGTH_LONG)
//                .show()

            val data = Intent()
            data.putExtra(EXTRA_OBJECT, itemHeader)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    fun readBinderToItem() {
        itemHeader.username = editTextUsername.text.toString()
        itemHeader.password = editTextPassword.text.toString()
    }

    companion object {
        const val EXTRA_ID = "com.erp.distribution.ActivityLogin.EXTRA_ID"
        const val EXTRA_OBJECT = "com.erp.distribution.ActivityLogin.EXTRA_OBJECT"
    }

}

