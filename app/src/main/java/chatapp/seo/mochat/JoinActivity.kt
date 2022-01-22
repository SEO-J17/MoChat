package chatapp.seo.mochat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import chatapp.seo.mochat.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {
    private lateinit var joinBinding: ActivityJoinBinding
    private var fireAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fireAuth = Firebase.auth

        joinBinding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(joinBinding.root)


    }
}