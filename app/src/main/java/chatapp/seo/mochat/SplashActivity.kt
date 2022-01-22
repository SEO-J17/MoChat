//package chatapp.seo.mochat
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import chatapp.seo.mochat.databinding.FragmentSplashBinding
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//class SplashActivity : Fragment() {
//    private lateinit var splashBinding: FragmentSplashBinding
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        splashBinding = FragmentSplashBinding.inflate(layoutInflater)
//        val view = splashBinding.root           //뷰설정
//
//        splashBinding.appNameTV.text = "모챗"
//        splashBinding.companyNameTV.text = "회사 이름"
//
//        CoroutineScope(Dispatchers.IO).launch {
//            delay(1500)     //1.5초
//        }
//        return view
//    }
//
//
//}
//
