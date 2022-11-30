package dinhtc.android.xml.view.fragment

import android.view.View
import dinhtc.android.xml.R
import dinhtc.android.xml.adapter.CartBagAdapter
import dinhtc.android.xml.base.BaseFragment
import dinhtc.android.xml.databinding.FragmentCartBagBinding
import dinhtc.android.xml.view.activity.MainActivity

class CartBagFragment : BaseFragment<FragmentCartBagBinding>() {

    override var bottomNavigationViewVisibility = View.GONE
    override var toolbarViewVisibility = View.VISIBLE

    private lateinit var cartBagAdapter : CartBagAdapter

    override val layoutResourceId: Int
        get() = R.layout.fragment_cart_bag

    override fun viewCreatedFragment() {
        if (activity is MainActivity){
            (activity as MainActivity).setTitle("Cart List")
        }
        if (activity is MainActivity){
            (activity as MainActivity).hideCart(true)
        }

        initData()
        onClickItem()
    }

    private fun onClickItem() {
        _binding.apply {
        }
    }

    private fun initData() {

//        cartBagAdapter = context?.let {
//            CartBagAdapter(
//                listProduct, context = it
//            )
//        }!!
//
//        _binding.rcListCart.apply {
//            adapter = cartBagAdapter
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            setHasFixedSize(true)
//        }
    }
}