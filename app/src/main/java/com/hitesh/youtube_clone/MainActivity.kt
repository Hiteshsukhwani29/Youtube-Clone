package com.hitesh.youtube_clone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hitesh.youtube_clone.fragment.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    lateinit var bottmNavigationView: BottomNavigationView
    lateinit var auth: FirebaseAuth
    var currentUser = ""
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        currentUser = auth.currentUser.toString()

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        bottmNavigationView = findViewById(R.id.bottom_nav)

        setCurrentFragment(Home())

        bottmNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(Home())
                R.id.explore -> setCurrentFragment(Explore())
                R.id.subscription -> setCurrentFragment(Subscriptions())
                R.id.notification -> setCurrentFragment(Notifications())
                R.id.library -> setCurrentFragment(Library())
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container1, fragment)
            commit()
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createvideo -> Toast.makeText(this, "Create Video", Toast.LENGTH_SHORT).show()
            R.id.search -> Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            R.id.profile -> showDialog()/*if (currentUser.length < 1) {
                showDialog()
            } else {
                Toast.makeText(this, "User is already logged in", Toast.LENGTH_SHORT).show()
            }*/
        }
        return true
    }

    private fun showDialog() {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(this)
        }

        Log.d("Working","Working")
        val vg: ViewGroup = findViewById(android.R.id.content)
        val view: View =
            LayoutInflater.from(applicationContext).inflate(R.layout.dialog_signin, vg, false)

        builder.setCancelable(true).setView(view).show()
        val signinbtn: Button = view.findViewById(R.id.btn_signin)
        signinbtn.setOnClickListener({
            signin()
        })
    }

    private fun signin() {
        val intent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, Req_Code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val userinfo: HashMap<String,String> = HashMap()
                            userinfo.put("name", account?.displayName.toString())
                            userinfo.put("email", account?.email.toString())
                            userinfo.put("profilephoto", account?.photoUrl.toString())

                                userinfo.put("uid", user!!.uid)

                            if (user != null) {
                                userinfo.put("uid", user.uid)
                            }

                            Log.d("UserData", userinfo.toString())

                        } else {

                        }
                    }

        }
        catch(e:Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
}