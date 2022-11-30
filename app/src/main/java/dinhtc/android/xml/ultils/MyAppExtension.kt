package dinhtc.android.xml.ultils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

/**
 * Support for fragment that is within a [NavHostFragment]
 */
fun Fragment.navigate2Fragment(action: Int, args: Bundle? = null) {
    try { findNavController().navigate(action, args) } catch (ignore: IllegalStateException) {} catch (ignore: IllegalArgumentException) {}
}

/**
 * Support for fragment that is within a [NavHostFragment]
 */
val Fragment.currentDestination: NavDestination?
    get() = try { findNavController().currentDestination } catch (ignore: IllegalStateException) { null }

/**
 * Support for fragment that is within a [NavHostFragment]
 */
fun Fragment.navigateUp() = try { findNavController().navigateUp() } catch (ignore: IllegalStateException) {}




const val DELAY_TRANSITION_TIME: Long = 700

fun <T> Fragment.observe(liveData: LiveData<T>, action: (t: T) -> Unit): Observer<T> {
    val observer = Observer<T> { t -> action(t) }
    liveData.observe(this.viewLifecycleOwner, observer)
    return observer
}

fun <T> Fragment.observeForever(liveData: LiveData<T>, action: (t: T) -> Unit): Observer<T> {
    val observer = Observer<T> { t -> action(t) }
    liveData.observeForever(observer)
    return observer
}

fun <T> Fragment.observe(liveData: LiveData<T>) {
    liveData.observe(this.viewLifecycleOwner, Observer {})
}

fun <T> AppCompatActivity.observe(liveData: LiveData<T>, action: (t: T) -> Unit): Observer<T> {
    val observer = Observer<T> { it?.let { t -> action(t) } }
    liveData.observe(this, observer)
    return observer
}

fun <T> AppCompatActivity.observe(liveData: LiveData<T>) {
    liveData.observe(this, Observer {})
}

fun <T : Any?> MutableLiveData<T>.defaultValue(initialValue: T) = apply { setValue(initialValue) }

inline fun <reified T> Activity.start(clearBackStack: Boolean = false, bundle: Bundle? = null, activityOptions: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    if (clearBackStack) {
        if (activityOptions == null) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        } else {
            //need to delay for better UI performance in case no transition between activities
            Handler().postDelayed({ if (!isDestroyed) finish() }, DELAY_TRANSITION_TIME)
        }
    }
    bundle?.let {
        intent.putExtras(bundle)
    }
    ActivityCompat.startActivity(this, intent, activityOptions)
}

inline fun <reified T> Activity.startForResult(requestCode: Int, bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    startActivityForResult(intent, requestCode)
}

inline fun <reified T> Activity.startForResult(requestCode: Int, bundle: Bundle? = null, activityOptions: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    ActivityCompat.startActivityForResult(this, intent, requestCode, activityOptions)
}