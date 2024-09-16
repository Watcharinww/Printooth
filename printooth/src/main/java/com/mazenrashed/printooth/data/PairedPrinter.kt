package com.mazenrashed.printooth.data

import android.content.Context
import java.io.Serializable

open class PairedPrinter(var name: String?, var address: String) : Serializable {

    companion object {
        private const val PAIRED_PRINTER_NAME = "printer_name"
        private const val PAIRED_PRINTER_ADDRESS = "printer_address"
        private const val PRINTOOTH_PREF = "PRINTOOTH_PREF"

        fun getPairedPrinter(applicationContext: Context?): PairedPrinter? {
            val sharedPreferences =
                applicationContext?.getSharedPreferences(PRINTOOTH_PREF, Context.MODE_PRIVATE)
            val printerName = sharedPreferences?.getString(PAIRED_PRINTER_NAME, "")
            val printerAddress = sharedPreferences?.getString(PAIRED_PRINTER_ADDRESS, null)

            if (printerAddress.isNullOrEmpty()) {
                return null
            }

            return PairedPrinter(printerName, printerAddress)
        }

        fun setPairedPrinter(context: Context?, printer: PairedPrinter) {
            val sharedPreferences =
                context?.getSharedPreferences(PRINTOOTH_PREF, Context.MODE_PRIVATE)

            sharedPreferences?.edit()?.apply {
                putString(PAIRED_PRINTER_NAME, printer.name)
                putString(PAIRED_PRINTER_ADDRESS, printer.address)
            }?.commit()
        }

        fun removePairedPrinter(context: Context?) {
            val sharedPreferences =
                context?.getSharedPreferences(PRINTOOTH_PREF, Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.clear()?.commit()
        }
    }

}