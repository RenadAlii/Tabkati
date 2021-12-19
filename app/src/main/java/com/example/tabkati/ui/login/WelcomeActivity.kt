package com.example.tabkati.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.tabkati.R
import com.example.tabkati.databinding.ActivityWelcomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

val REQUEST_CODE_SIGNIN = 0
class WelcomeActivity : AppCompatActivity() {
    private var _binding: ActivityWelcomeBinding? = null
    private lateinit var binding:ActivityWelcomeBinding
    private val model: LoginViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        binding=_binding!!
        setContentView(binding.root)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this Activity.
            lifecycleOwner = lifecycleOwner
            viewModel = model
            // @ because inside binding.apply this revers to the binding instance.
            // not the class DetailsFragment.
            welcomeActivity = this@WelcomeActivity
            googlePlusIcon.setOnClickListener {
                googleSignInClientIntent()
            }

        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGNIN){
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                googleAuthFirebase(it)
            }
        }
    }

    private fun googleAuthFirebase(account: GoogleSignInAccount){
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credentials)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"siggned in",Toast.LENGTH_LONG).show()
                }}.addOnFailureListener {
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show()
                println(it.message.toString())
            }



                }



     fun googleSignInClientIntent(){
         val googleSignInClient = GoogleSignIn.getClient(this, model.signUpWithGoogle())
         googleSignInClient.signInIntent.also {
             startActivityForResult(it, REQUEST_CODE_SIGNIN)
         }
     }


    // fun to go to the Login Activity.
    fun goToLoginActivity(){
        val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    // fun to go to the SignUp Activity.
    fun goToSignUpActivity(){
        val intent = Intent(this@WelcomeActivity, RegisterActivity::class.java)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}
