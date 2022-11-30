package dinhtc.android.xml.view.fragment

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dinhtc.android.xml.R
import dinhtc.android.xml.adapter.CategoryHomeAdapter
import dinhtc.android.xml.adapter.PopulerHomeAdapter
import dinhtc.android.xml.adapter.ProductHomeAdapter
import dinhtc.android.xml.adapter.TodayDealAdapter
import dinhtc.android.xml.base.BaseFragment
import dinhtc.android.xml.databinding.FragmentHomeBinding
import dinhtc.android.xml.model.CategoryModel
import dinhtc.android.xml.model.ProductModel
import dinhtc.android.xml.ultils.*
import dinhtc.android.xml.view.activity.MainActivity.Companion.KEY_BODY_PRODUCT
import dinhtc.android.xml.viewmodel.CategoryViewModel
import dinhtc.android.xml.viewmodel.ProductViewModel

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var categoryListApi = mutableListOf<CategoryModel>()
    private var productListApi = mutableListOf<ProductModel>()

    private val categoryViewModel by viewModels<CategoryViewModel>()
    private val productViewModel by viewModels<ProductViewModel>()

    private val listProductChoose = ArrayList<ProductModel>()
    private lateinit var productChoose: ProductModel

    private lateinit var categoryHomeAdapter: CategoryHomeAdapter
    private lateinit var productHomeAdapter: ProductHomeAdapter
    private lateinit var populerHomeAdapter: PopulerHomeAdapter
    private lateinit var todayDealAdapter: TodayDealAdapter

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun viewCreatedFragment() {
        onClickItem()


        categoryViewModel.getListCategory()
        categoryViewModel.categoryResponse.observe(viewLifecycleOwner) {
            categoryListApi.addAll(it.toMutableList())

            if (categoryListApi.isNotEmpty()) {
                listProductChoose.clear()
                val categoryIdFirst = categoryListApi[0].categoryId
                if (productListApi.isNotEmpty()) {
                    for (i in productListApi) {
                        if (i.categoryId == categoryIdFirst) {
                            productChoose = i
                            listProductChoose.add(productChoose)
                        }
                    }
                }
            }
            customAdapterCategory()
        }

        productViewModel.getListProduct()
        productViewModel.productResponse.observe(viewLifecycleOwner) {
            productListApi.addAll(it.toMutableList())
            customAdapterProduct()
        }

        /*
        * Adapter List Category
        * */
        customAdapterCategory()
        /*
        * Adapter List Product
        * */
        customAdapterProduct()

        /*
       * Custom List Today Deal
       * */

        todayDealAdapter = context?.let {
            TodayDealAdapter(
                productListApi, context = it
            )
        }!!

        _binding.rcTodayDeal.apply {
            adapter = todayDealAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        todayDealAdapter.onClickAddListener(object :TodayDealAdapter.OnAddClickListener{
            override fun onClickItem(productModel: ProductModel?, position: Int) {
                Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show()
            }
        })

        todayDealAdapter.onClickItemListener(object :TodayDealAdapter.OnItemClickListener{
            override fun onClickItem(productModel: ProductModel?, position: Int) {
                navigate2Fragment(
                    R.id.homeFragment_to_productDetailFragment,
                    bundleOf(
                        KEY_BODY_PRODUCT to productModel
                    )
                )
            }
        })

        /*
        * Custom List Populer
        * */

        populerHomeAdapter = context?.let {
            PopulerHomeAdapter(
                productListApi, context = it
            )
        }!!

        _binding.rcPopuler.apply {
            adapter = populerHomeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun customAdapterProduct() {
        productHomeAdapter = context?.let {
            context?.let { it1 -> ProductHomeAdapter(listProductChoose, it1) }
        }!!
        _binding.rcListProduct.apply {
            adapter = productHomeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        productHomeAdapter.setOnClickItem(object : ProductHomeAdapter.OnItemClickListener {
            override fun onClickItem(productModel: ProductModel?, position: Int) {
                navigate2Fragment(
                    R.id.homeFragment_to_productDetailFragment,
                    bundleOf(
                        KEY_BODY_PRODUCT to productModel
                    )
                )
            }
        })
    }

    private fun customAdapterCategory() {
        categoryHomeAdapter = context?.let {
            CategoryHomeAdapter(categoryListApi, context = it)
        }!!
        _binding.rcListCategory.apply {
            adapter = categoryHomeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        categoryHomeAdapter.setOnClickItem(object : CategoryHomeAdapter.OnItemClickListener {
            override fun onClickItem(categoryModel: CategoryModel?, position: Int) {
                loadDataListProduct(categoryModel?.categoryId)
            }
        })
    }

    private fun loadDataListProduct(categoryId: Int?) {
        if (productListApi.size > 0) {
            listProductChoose.clear()
            for (i in productListApi) {
                if (i.categoryId == categoryId) {
                    productChoose = i
                    listProductChoose.add(productChoose)
                }
                productHomeAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun onClickItem() {
        _binding.layoutCart.setOnClickListener {
            navigate2Fragment(R.id.homeFragment_to_cartBagFragment)
        }
    }
}