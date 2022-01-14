package chatapp.seo.mochat

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import chatapp.seo.mochat.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = splashBinding.root           //뷰설정
        setContentView(view)

        splashBinding.appNameTV.text = "모챗"
        splashBinding.companyNameTV.text = "회사 이름"

    }
}