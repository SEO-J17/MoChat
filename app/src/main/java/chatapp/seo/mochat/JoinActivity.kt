package chatapp.seo.mochat

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import chatapp.seo.mochat.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class JoinActivity : AppCompatActivity() {
    private lateinit var joinBinding: ActivityJoinBinding
    private var fireAuth: FirebaseAuth? = null
    private var fireDB = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fireAuth = Firebase.auth

        joinBinding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(joinBinding.root)


        var sex = ""
        joinBinding.sexradiogroup.setOnCheckedChangeListener { radioGroup, i ->
            if (joinBinding.maleRadioBtn.isChecked) {
                sex = "male"
                Toast.makeText(this, sex, Toast.LENGTH_SHORT).show()
            } else if (joinBinding.femaleRadioBtn.isChecked) {
                sex = "female"
                Toast.makeText(this, sex, Toast.LENGTH_SHORT).show()
            }
        }


        joinBinding.regiseterBtn.setOnClickListener() {
            var editID = joinBinding.joinEmailET.text.toString().trim()
            var editPW = joinBinding.joinPWET.text.toString().trim()
            var chkPW = joinBinding.chkPWET.text.toString().trim()
            var nickname = joinBinding.joinAliasET.text.toString().trim()

            if (TextUtils.isEmpty(nickname)) {
                joinBinding.joinAliasET.setError("닉네임을 입력해주세요")
                joinBinding.joinAliasET.requestFocus()
            } else if (TextUtils.isEmpty(editID)) {
                joinBinding.joinEmailET.setError("이메일을 입력해주세요")
                joinBinding.joinEmailET.requestFocus()
            } else if (TextUtils.isEmpty(editPW)) {
                joinBinding.joinPWET.setError("패스워드를 입력해주세요")
                joinBinding.joinPWET.requestFocus()
                if (!chkPW.equals(editPW)) {
                    joinBinding.chkPWET.setError("패스워드가 다릅니다")
                    joinBinding.chkPWET.requestFocus()
                }
            } else if (sex.equals("")) {
                Toast.makeText(this, "성별을 선택해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(editID, editPW, nickname, sex)
            }
        }
    }


    private fun createAccount(editID: String, editPW: String, nickname: String, sex: String) {
        val mRef = fireDB.getReference()
        fireAuth?.createUserWithEmailAndPassword(editID, editPW)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")
                    val joinTime = LocalDateTime.now().format(formatter)

                    Toast.makeText(this, "회원가입을 완료했습니다! 로그인 해주세요", Toast.LENGTH_SHORT).show()
                    val data = JoinDataModel(
                        nickname, editID, editPW, sex, "OFFLINE", joinTime,
                    )
                    mRef.child("users").child(task.result.user?.uid.toString()).setValue(data)      //데이터 베이스에 회원정보 반영
                } else {
                    Toast.makeText(this, "회원가입이 실패했습니다. 인터넷 연결을 다시 확인해 주세요", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("firebase", "파이어베이스 실패", task.getException())
                }
            }
    }
}

//이메일 형식,패스워드는 최소 6자리로 해야됨
//사용자에게 알려주도록