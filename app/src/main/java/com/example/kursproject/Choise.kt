package com.example.kursproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kursproject.databinding.ActivityChoiseBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Choise : AppCompatActivity() {
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityChoiseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account!=null){
                    firebaseAuthWithGoogle(account.idToken!!) // если норм, то подключаем аккаунт
                }
            }
            catch (e: ApiException) {
                Log.d("MyLog", "launcher false")
            }
        }
        checkAuthState() // если мы уже зарегистрированы, то сразу откроется MainActivity
    }

    // висит на кнопке регистрация
    fun clickerRegister(view: View) {
        startActivity(Intent(this@Choise, RegisterActivity::class.java))
    }
    // висит на кнопке входа
    fun loginRegister(view: View) {
        startActivity(Intent(this@Choise, LoginActivity::class.java))
    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    // висит на кнопке входа через гугл
    fun clickerGoogle(view: View) {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val cridencial = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(cridencial).addOnCompleteListener{
            if(it.isSuccessful){
//                val mainIntent = Intent(this@Choise, MainActivity::class.java)
//                startActivity(mainIntent)
                Log.d("MyLog", "GoogleSignIn task")
                checkAuthState()
            }
            else{
                Log.d("MyLog", "GoogleSignIn false")
            }
        }
    }

    private fun checkAuthState(){
        if (auth.currentUser != null){ // мы вошли под аккаунтом
            val i = Intent(this, MainActivity::class.java)
            startActivity(i) // значит можно открыть активити
        }
    }












}