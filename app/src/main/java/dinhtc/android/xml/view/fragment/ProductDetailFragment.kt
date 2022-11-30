package dinhtc.android.xml.view.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dinhtc.android.xml.R
import dinhtc.android.xml.adapter.AddExtraAdapter
import dinhtc.android.xml.base.BaseFragment
import dinhtc.android.xml.base.BottomSheetChooseDetail
import dinhtc.android.xml.databinding.FragmentProductDetailBinding
import dinhtc.android.xml.model.ProductModel
import dinhtc.android.xml.view.activity.MainActivity
import dinhtc.android.xml.ultils.navigate2Fragment
import dinhtc.android.xml.viewmodel.ProductViewModel
import java.io.Serializable

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    private var productBundle: ProductModel? = null
    override var bottomNavigationViewVisibility = View.GONE
    override var toolbarViewVisibility = View.VISIBLE

    private lateinit var addExtraAdapter: AddExtraAdapter
    private var productListApi = mutableListOf<ProductModel>()

    private val productViewModel by viewModels<ProductViewModel>()

    override val layoutResourceId: Int?
        get() = R.layout.fragment_product_detail

    override fun viewCreatedFragment() {

        if (activity is MainActivity) {
            (activity as MainActivity).hideCart(false)
        }

        productBundle = arguments?.getSerializable(MainActivity.KEY_BODY_PRODUCT) as ProductModel?
        _binding.product = productBundle
        onClickItem()

        productViewModel.getListProduct()
        productViewModel.productResponse.observe(viewLifecycleOwner) {
            productListApi.clear()
            for (k in it){
                if (productBundle?.productId != k.productId){
                    productListApi.add(k)
                }
            }

            addExtraAdapter = context?.let {
                    item -> AddExtraAdapter(productListApi, item)
            }!!

            _binding.layoutDetail.rcAddExtra.apply {
                adapter = addExtraAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }

    private fun onClickItem() {
        _binding.apply {
            layoutOrderNow.setOnClickListener {
                navigate2Fragment(R.id.productDetailFragment_to_cartBagFragment)
            }

            layoutMoney.setOnClickListener {
                showBottomSheet()
            }
        }

        if (activity is MainActivity) {
            (activity as MainActivity).itemCart.setOnClickListener {
                navigate2Fragment(R.id.productDetailFragment_to_cartBagFragment)
            }
        }
    }

    private fun showBottomSheet() {
        val dialogFragment = BottomSheetChooseDetail()
        dialogFragment.show(
            parentFragmentManager,
            dialogFragment.tag
        )
        dialogFragment.isCancelable = false

        dialogFragment.setCloseBottomSheet(object :
            BottomSheetChooseDetail.OnClickCloseBottomSheet {
            override fun onClickClose(view: View) {
                dialogFragment.dismiss()
            }
        })
    }
}