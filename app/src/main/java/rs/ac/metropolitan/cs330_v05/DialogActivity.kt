package rs.ac.metropolitan.cs330_v05

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DialogActivity : AppCompatActivity() {
    var izbori = arrayOf<CharSequence>("FIT", "FAM", "FDU")
    var itemsChecked = BooleanArray(izbori.size)
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View?) {
        showDialog(0)
    }

    fun onClick2(v: View?) {
        val dialog = ProgressDialog.show(
            this, "Nešto se dešava.", "Sačekajte...", true
        )
        Thread {
            try {
                Thread.sleep(5000)
                dialog.dismiss()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }

    fun onClick3(v: View?) {
        showDialog(1)
        progressDialog!!.progress = 0
        Thread {
            for (i in 1..15) {
                try {
                    Thread.sleep(1000)
                    progressDialog!!.incrementProgressBy((100 / 15))
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            progressDialog!!.dismiss()
        }.start()

    }
    override fun onCreateDialog(id: Int): Dialog? {
        when (id) {
            0 -> return AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Dijalog")
                .setPositiveButton(
                    "OK"
                ) { dialog, whichButton ->
                    if (whichButton == DialogInterface.BUTTON_POSITIVE) {
                        val url = when {
                            itemsChecked[0] -> "https://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/"
                            itemsChecked[1] -> "https://www.metropolitan.ac.rs/osnovne-studije/fakultet-za-menadzment/"
                            itemsChecked[2] -> "https://www.metropolitan.ac.rs/fakultet-digitalnih-umetnosti-2/"
                            else -> ""
                        }
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(url)
                        startActivity(i)
                    }
                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, whichButton ->
                    Toast.makeText(
                        baseContext,
                        "Cancel je kliknut!", Toast.LENGTH_SHORT
                    ).show()
                }
                .setMultiChoiceItems(
                    izbori, itemsChecked
                ) { dialog, which, isChecked ->
                    Toast.makeText(
                        baseContext,
                        izbori[which].toString() + if (isChecked) " cekd!" else " ancekd !",
                        Toast . LENGTH_SHORT
                    ).show()
                }.create()

            1 -> {
                progressDialog = ProgressDialog(this)
                progressDialog!!.setIcon(R.mipmap.ic_launcher)
                progressDialog!!.setTitle("Preuzimanje datoteka...")
                progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                progressDialog!!.setButton(
                    DialogInterface.BUTTON_POSITIVE, "OK"
                ) { dialog, whichButton ->
                    Toast.makeText(
                        baseContext,
                        "OK je kliknut!", Toast.LENGTH_SHORT
                    ).show()
                }
                progressDialog!!.setButton(
                    DialogInterface.BUTTON_NEGATIVE, "Cancel"
                ) { dialog, whichButton ->
                    Toast.makeText(
                        baseContext,
                        "Cancel je kliknut!", Toast.LENGTH_SHORT
                    ).show()
                }
                return progressDialog
            }
        }
        return null
    }

}