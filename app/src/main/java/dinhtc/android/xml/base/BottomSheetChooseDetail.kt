package dinhtc.android.xml.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dinhtc.android.xml.R
import dinhtc.android.xml.adapter.ToppingAdapter
import dinhtc.android.xml.model.ToppingModel

class BottomSheetChooseDetail : BottomSheetDialogFragment() {

    private var rcTopping: RecyclerView? = null
    private lateinit var toppingAdapter: ToppingAdapter

    lateinit var vBinding: ViewDataBinding
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null

    var onCloseBottomSheet: OnClickCloseBottomSheet? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.custom_bottom_sheet_choose_detail,
            container,
            false
        )
        return vBinding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    companion object {
        const val TAG = "BottomSheetChooseDetail"
        fun newInstance(): BottomSheetChooseDetail {
            return BottomSheetChooseDetail()
        }
    }

    override fun onResume() {
        super.onResume()
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcTopping = view.findViewById(R.id.rcTopping)
        val imvClose = view.findViewById<ImageView>(R.id.imvClose)

        bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior?.isDraggable = false

        imvClose.setOnClickListener {
            onCloseBottomSheet?.onClickClose(imvClose)
        }

        initDataTopping()
    }

    private fun initDataTopping(){
        val listProduct = listOf(
            ToppingModel(
                id = 1,
                name = "Cafe đen đá",
                price = "100.000 VNĐ",
            ),
            ToppingModel(
                id = 2,
                name = "Cafe capochino",
                price = "90.000 VNĐ",
            ),
            ToppingModel(
                id = 3,
                name = "Cafe đen",
                price = "12.000 VNĐ",
            ),
            ToppingModel(
                id = 4,
                name = "Cafe đen hồng",
                price = "100.000 VNĐ",
            )
        )

        toppingAdapter = context?.let {
            ToppingAdapter(
                listProduct, context = it
            )
        }!!

        rcTopping?.apply {
            adapter = toppingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    interface OnClickCloseBottomSheet {
        fun onClickClose(view: View)
    }

    fun setCloseBottomSheet(onClickListener: OnClickCloseBottomSheet) {
        this.onCloseBottomSheet = onClickListener
    }
}