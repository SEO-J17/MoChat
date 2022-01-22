package chatapp.seo.mochat

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import chatapp.seo.mochat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        val editID: EditText = loginBinding.inputIDET
        val editPW: EditText = loginBinding.inputPasswordET

        //로그인 버튼
        loginBinding.loginBtn.setOnClickListener() {
            var id = editID.text.toString().trim()
            var pw = editPW.text.toString().trim()

            if (TextUtils.isEmpty(id)) {
                editID.setError("아이디를 입력하세요")
            } else if (TextUtils.isEmpty(pw)) {
                editPW.setError("비밀번호를 입력하세요")
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)         //메인액티비티로 갈때 스택에 있는 모든 액티비티를 삭제한다.
                startActivity(intent)
            }
        }

        //회원가입으로 이동
        loginBinding.joinBtn.setOnClickListener(){
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }

}