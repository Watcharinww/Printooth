package com.mazenrashed.printooth

import android.annotation.SuppressLint
import android.content.Context
import com.mazenrashed.printooth.data.PairedPrinter
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.data.printer.Printer
import com.mazenrashed.printooth.utilities.Printing

@SuppressLint("StaticFieldLeak")
object Printooth {

    private var context: Context? = null
    private var printing: Printing? = null

    fun init(context: Context) {
        this.context = context
    }

    fun printer(printer: Printer, pairedPrinter: PairedPrinter): Printing = Printing(
            printer,
            pairedPrinter,
            context ?: error("You must call Printooth.init()")
    )

    fun printer(printer: Printer): Printing = Printing(
            printer,
            getPairedPrinter() ?: error("No paired printer saved, Save one and retry!!"),
            context ?: error("You must call Printooth.init()")
    )

    fun printer(pairedPrinter: PairedPrinter) = Printing(
            DefaultPrinter(),
            pairedPrinter,
            context ?: error("You must call Printooth.init()")
    )

    fun printer(): Printing {
        return if (printing == null) {
            printing = Printing(
                    DefaultPrinter(),
                    getPairedPrinter()
                            ?: error("No paired printer saved, Save one and retry!!"),
                    context ?: error("You must call Printooth.init()")
            )
            printing!!
        } else {
            printing!!
        }
    }

    fun setPrinter(name: String?, address: String) = PairedPrinter.setPairedPrinter(context, PairedPrinter(name, address))

    fun getPairedPrinter(): PairedPrinter? = PairedPrinter.getPairedPrinter(context)

    fun hasPairedPrinter(): Boolean = PairedPrinter.getPairedPrinter(context) != null

    fun removeCurrentPrinter() = PairedPrinter.removePairedPrinter(context)

}