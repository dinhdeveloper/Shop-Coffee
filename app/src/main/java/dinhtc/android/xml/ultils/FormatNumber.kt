package dinhtc.android.xml.ultils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("formatMoney")
fun TextView.formatMoney(money: String?) {
    if (money?.isNotEmpty() == true){
        this.text = "${Utils.formatMoney(Utils.formatDouble(money.toDouble()))} VNĐ"
    }
}

@BindingAdapter("formatMoneyStrike")
fun TextView.formatMoneyStrike(money: String?) {
    if (money?.isNotEmpty() == true){
        this.text = "${Utils.formatMoney(Utils.formatDouble(money.toDouble()))} VNĐ"
        this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}

@BindingAdapter("formatPoint")
fun TextView.formatPoint(money: String?) {
    if (money?.isNotEmpty() == true){
        this.text = "${Utils.formatMoney(Utils.formatDouble(money.toDouble()))} Điểm"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("countPreview")
fun TextView.countPreview(name: Int?) {
    if (name != null) {
        if (name > 0){
            this.text = " (${Utils.formatMoney(Utils.formatDouble(name.toDouble()))} review)"
        }
    }
}

@BindingAdapter("loadImageDrawble")
fun ImageView.loadImageDrawble(images: String?) {
    if (images?.isNotEmpty() == true){
        this.setImageResource(getImageId(context, images))
    }
}

fun getImageId(context: Context, imageName: String): Int {
    return context.resources
        .getIdentifier("drawable/$imageName", null, context.packageName)
}