package chatapp.seo.mochat

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
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

        joinBinding.regiseterBtn.setOnClickListener() {
            var editID = joinBinding.joinIdET.text.toString().trim()
            var editPW = joinBinding.joinPWET.text.toString().trim()
            var chkPW = joinBinding.chkPWET.text.toString().trim()
            var nickname = joinBinding.joinAliasET.text.toString().trim()

            if (TextUtils.isEmpty(editID)) {
                joinBinding.joinIdET.setError("아이디를 입력해주세요")
                joinBinding.joinIdET.requestFocus()
            } else if (TextUtils.isEmpty(editPW)) {
                joinBinding.joinPWET.setError("패스워드를 입력해주세요")
                joinBinding.joinPWET.requestFocus()
                if (!chkPW.equals(editPW)) {
                    joinBinding.chkPWET.setError("패스워드가 다릅니다")
                    joinBinding.chkPWET.requestFocus()
                }
            } else if (TextUtils.isEmpty(nickname)) {
                joinBinding.joinAliasET.setError("닉네임을 입력해주세요")
                joinBinding.joinAliasET.requestFocus()
            } else {
                createAccount(editID, editPW, nickname)
            }
        }
    }

    private fun createAccount(editID: String, editPW: String, nickname: String) {
        fireAuth?.createUserWithEmailAndPassword(editID, editPW)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "회원가입이 실패했습니다. 인터넷 연결을 다시 확인해 주세요", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(TAG,"파이어베이스 실패", task.getException())
                }
            }
    }
}

//이메일 형식,패스워드는 최소 6자리로 해야됨
//사용자에게 알려주도록