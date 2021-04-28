package com.app.news.utility

import android.content.Context
import androidx.appcompat.app.AlertDialog

class NewsAlertDialog {

    lateinit var alert : AlertDialog

    public fun showAlertDialog(context: Context,title: String,msg : String){
        val dialogBuilder = context?.let { AlertDialog.Builder(it) }
        dialogBuilder?.setMessage("Loading.. Please wait")
        alert = dialogBuilder?.create()!!
        alert.setTitle(title)
        alert.setMessage(msg)
        alert.show()
    }

    public fun dismissAlertDialog(){
        if(alert.isShowing){
            alert.dismiss()
        }
    }
}