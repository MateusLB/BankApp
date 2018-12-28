package com.mateus.batista.testeandroidv2app.utils

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class ConvertUtil {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getDataFormat(data: String?): String {
            return if (data != null) {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(data)
                SimpleDateFormat("dd/MM/yyyy").format(date)
            } else {
                ""
            }
        }

        fun getRealForm(value: Double): String {
            val ptBr = Locale("pt", "BR")
            return NumberFormat.getCurrencyInstance(ptBr).format(value)
        }

        fun getBankAccountForm(bankAccount : String) : String{
          return addMask(bankAccount,"####/##.######-#")
        }

        private fun addMask(text: String, mask: String): String {
            var textWithMask = ""
            var i = 0
            for (m in mask.toCharArray()) {
                if (m != '#') {
                    textWithMask += m
                    continue
                }
                try {
                    textWithMask += text[i]
                } catch (e: Exception) {
                    break
                }
                i++
            }
            return textWithMask
        }
    }
}