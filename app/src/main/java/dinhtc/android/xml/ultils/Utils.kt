package dinhtc.android.xml.ultils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class Utils {
    companion object{
        @JvmStatic
        fun formatMoney(money: String?): String {
            var money_ = ""
            try {
                val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
                val symbols = formatter.decimalFormatSymbols
                symbols.decimalSeparator = ','
                val decimalFormat = DecimalFormat("###,###,###,###", symbols)
                money_ = decimalFormat.format(money?.toDouble() ?: 0)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return money_.replace(",".toRegex(), ",")
        }

        @JvmStatic
        fun formatDouble(aDouble: Double?): String {
            var number: String? = ""
            try {
                number = DecimalFormat("#").format(aDouble)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return number ?: ""
        }
    }
}