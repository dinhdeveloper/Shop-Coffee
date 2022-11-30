package dinhtc.android.xml.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dinhtc.android.xml.R
import dinhtc.android.xml.view.activity.MainActivity

abstract class BaseFragment<_ViewDataBinding : ViewDataBinding>() : Fragment() {

    private lateinit var mainActivity: MainActivity
    protected open var bottomNavigationViewVisibility = View.VISIBLE
    protected open var toolbarViewVisibility = View.GONE
    abstract val layoutResourceId: Int?
    lateinit var _binding: _ViewDataBinding
    private val binding get() = _binding!!



    abstract fun viewCreatedFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutResourceId!!,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreatedFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility,toolbarViewVisibility)
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility,toolbarViewVisibility)
        }
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility,toolbarViewVisibility)
        }
    }


    fun replaceFragment(
        fragmentManager: FragmentManager?,
        containerId: Int,
        fragment: BaseFragment<_ViewDataBinding>,
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

    fun addFragment(
        fragmentManager: FragmentManager?,
        containerId: Int,
        fragment: BaseFragment<_ViewDataBinding>,
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
            ft.add(containerId, fragment)
            if (addBackStack) {
                ft.addToBackStack(fragment::class.java.canonicalName)
            }
            ft.commit()
        }
    }
}