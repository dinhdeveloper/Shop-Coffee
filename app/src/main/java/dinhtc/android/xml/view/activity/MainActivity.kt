package dinhtc.android.xml.view.activity

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dinhtc.android.xml.R
import dinhtc.android.xml.base.BaseActivity
import dinhtc.android.xml.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object{
        const val KEY_BODY_PRODUCT = "KEY_BODY_PRODUCT"
    }

    lateinit var itemCart: ImageView
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding


    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun onCreateActivity() {
        binding = viewDataBinding as ActivityMainBinding
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment?
        navController = findNavController(R.id.nav_host)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.menuFragment -> {
                    navController.navigate(R.id.menuFragment)
                    true
                }
                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }
                R.id.hearFragment -> {
                    navController.navigate(R.id.hearFragment)
                    true
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                else -> true
            }
        }

        onClickItem()

        itemCart = binding.layoutToolbar.imvCart
    }

    private fun onClickItem() {
        binding.apply {
            layoutToolbar.imgBack.setOnClickListener {
                onSupportNavigateUp()
            }

            layoutToolbar.imvCart.setOnClickListener {
                navController.navigate(R.id.homeFragment_to_cartBagFragment)
            }
        }
    }


    fun setTitle(title: String) {
        binding.title = title
    }

    fun setBottomNavigationVisibility(visibilityBottom: Int, visibilityToolbar: Int) {
        binding.bottomNavigation.visibility = visibilityBottom
        binding.layoutToolbar.layoutToolbarNew.visibility = visibilityToolbar
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        val prevDestinationId = navController.previousBackStackEntry?.destination?.id ?: -1

        return when {
            navController.currentDestination?.id == R.id.productDetailFragment
                    && prevDestinationId == R.id.homeFragment -> {
                navController.navigateUp()
                true
            }
            navController.currentDestination?.id == R.id.cartBagFragment
                    && prevDestinationId == R.id.productDetailFragment -> {
                navController.navigateUp()
                true
            }
            (!navController.navigateUp()) -> {
                finish()
                false
            }
            else -> {
                false
            }
        }
    }

    fun setColorToolbar() {
        binding.layoutToolbar.apply {
            layoutToolbarNew.background =
                ContextCompat.getDrawable(this@MainActivity, R.drawable.bg_toolbar_bag_cart)
            imgBack.setImageResource(R.drawable.ic_back)
        }
    }

    fun hideCart(status: Boolean) {
        binding.layoutToolbar.apply {
            viewGroup.visibility = if (status) {
                View.INVISIBLE
            } else {
                View.VISIBLE
            }
        }
    }
}