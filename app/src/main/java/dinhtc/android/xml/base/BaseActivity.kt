package dinhtc.android.xml.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dinhtc.android.xml.R
import java.util.ArrayList

abstract class BaseActivity<_ViewDataBinding : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutResourceId: Int
    protected var viewDataBinding: ViewDataBinding? = null
    abstract fun onCreateActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        onCreateActivity()
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        val baseLayout = layoutInflater.inflate(R.layout.activity_base, null) as FrameLayout
        val layoutMain: FrameLayout = baseLayout.findViewById(R.id.layout_main)
        viewDataBinding = DataBindingUtil.inflate(layoutInflater, layoutResID, layoutMain, true)
        super.setContentView(baseLayout)
    }

    fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }

    fun getConnectionType(context: Context): Int {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_VPN)){
                        result = 3
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = 2
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = 1
                    } else if(type == ConnectivityManager.TYPE_VPN) {
                        result = 3
                    }
                }
            }
        }
        return result
    }

    fun replaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        addBackStack: Boolean = true,
        hasAnim: Boolean = true
    ) {
        fragmentManager?.let {
            val ft = it.beginTransaction()
            if (hasAnim) {
                ft.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
            }
            ft.replace(containerId, fragment)
            if (addBackStack) {
                ft.addToBackStack(fragment::class.java.canonicalName)
            }
            ft.commit()
        }
    }
}