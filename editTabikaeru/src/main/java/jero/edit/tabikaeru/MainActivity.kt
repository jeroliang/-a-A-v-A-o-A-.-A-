package jero.edit.tabikaeru

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.text.TextUtils.indexOf
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private var inputMethodManager: InputMethodManager? = null
    private val FILE_PATH = Environment.getExternalStorageDirectory().toString() +
            File.separator + "Android/data/"
    private val SAVE_PATH = "/files/Tabikaeru.sav"
    private var selectPk = 0
    private val pkName: Array<String> = arrayOf("jp.co.hit_point.tabikaeru.jero", "jp.co.hit_point.tabikaeru", "jp.co.hit_point.tabikaeru.st")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val list = ArrayList<String>()
        list.add("jero版本")
        list.add("官方版本/3DM版本")
        list.add("手谈版本")
        list.add("其他版本")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectPk = position
                if (position == 3) {
                    edit_lay.visibility = View.VISIBLE
                } else {
                    edit_lay.visibility = View.GONE
                }
            }
        }
        ok.setOnClickListener({ readFile() })
        save.setOnClickListener(View.OnClickListener {
            Log.d("123", " " + cloverEt.text)
            Log.d("123", " " + raffleEt.text)
            hideSoftInput()
            if (TextUtils.isEmpty(cloverEt.text) || TextUtils.isEmpty(raffleEt.text)) {
                Snackbar.make(container, "两项都必须填写", Snackbar.LENGTH_LONG).show()
                return@OnClickListener
            }
            val cloverHex = String.format("%06X", Integer.valueOf(cloverEt.getText().toString()))
            val couponHex = String.format("%06X", Integer.valueOf(raffleEt.getText().toString()))
            Log.d("123", " " + cloverHex)
            Log.d("123", " " + couponHex)
            writeToFile(cloverHex, couponHex)
        })
    }

    private fun getPkName(): String {
        if (selectPk == 3) {
            if (TextUtils.isEmpty(right.text))
                return left.text.toString()
            else
                return left.text.toString() + "." + right.text.toString()
        }
        return pkName[selectPk]
    }

    private fun readFile() {
        Log.i("123", "" + getPkName())
        FileTools.getBackList(getPkName()).forEach(::println)
        val file = FileTools.getSaveFile(getPkName())
        if (!file.exists()) {
            cloverEt.setText("")
            raffleEt.setText("")
            Snackbar.make(container, "未找到存档文件,请切换版本选择", Snackbar.LENGTH_LONG).show()
            return
        }
        var fileInputStream = FileInputStream(file)
        val arrayOfByte = ByteArray(fileInputStream.available())
        Log.d("123", "文件大小" + arrayOfByte.size)
        fileInputStream.read(arrayOfByte)
        if (arrayOfByte.size > 29) {
            //三叶草
            Log.i("123456", "" + Tools.byteArrayToInt(byteArrayOf(arrayOfByte[22], arrayOfByte[23], arrayOfByte[24], arrayOfByte[25])))
            //抽奖券
            Log.i("123456", "" + Tools.byteArrayToInt(byteArrayOf(arrayOfByte[26], arrayOfByte[27], arrayOfByte[28], arrayOfByte[29])))
            cloverEt.setText(Tools.byteArrayToInt(byteArrayOf(arrayOfByte[22], arrayOfByte[23], arrayOfByte[24], arrayOfByte[25])).toString())
            raffleEt.setText(Tools.byteArrayToInt(byteArrayOf(arrayOfByte[26], arrayOfByte[27], arrayOfByte[28], arrayOfByte[29])).toString())
        }
        fileInputStream.close()
    }

    fun writeToFile(cloverHex: String, couponHex: String) {
        var fileInputStream: FileInputStream? = null
        var fileOutputStream: FileOutputStream? = null
        Log.i("123", "" + getPkName())
        val file = File(FILE_PATH + getPkName() + SAVE_PATH)
        val newFile = File(FILE_PATH + getPkName() + SAVE_PATH)
        val cloverByteArray = hexStringToByte(cloverHex)
        val couponByteArray = hexStringToByte(couponHex)
        Log.i("123", "" + cloverByteArray + "/" + cloverHex.toByteArray() + "//" + couponByteArray + "/" + couponHex.toByteArray())
        if (!file.exists()) {
            Snackbar.make(container, "未找到存档文件", Snackbar.LENGTH_LONG)
                    .setAction("重试", View.OnClickListener { save.callOnClick() }).show()
            return
        }
        return
        try {
            fileInputStream = FileInputStream(file)
            val arrayOfByte = ByteArray(fileInputStream.available())
            Log.d("123", "文件大小" + arrayOfByte.size)
            fileInputStream.read(arrayOfByte)
            if (arrayOfByte.size > 29) {
                //三叶草
                arrayOfByte[23] = cloverByteArray[0]//Byte.valueOf(cloverHex.substring(0, 2));
                arrayOfByte[24] = cloverByteArray[1]//Byte.valueOf(cloverHex.substring(2, 4));
                arrayOfByte[25] = cloverByteArray[2]//Byte.valueOf(cloverHex.substring(4, 6));

                //抽奖券
                arrayOfByte[27] = couponByteArray[0]//Byte.valueOf(couponHex.substring(0, 2));
                arrayOfByte[28] = couponByteArray[1]//Byte.valueOf(couponHex.substring(2, 4));
                arrayOfByte[29] = couponByteArray[2]//Byte.valueOf(couponHex.substring(4, 6));
                Log.d("123", " " + arrayOfByte.size)
                for (i in arrayOfByte.indices) {
                    Log.d("123", " " + arrayOfByte[i])
                }
                file.delete()
                createFile(newFile)
                fileOutputStream = FileOutputStream(newFile)
                fileOutputStream.write(arrayOfByte)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show()
            try {
                if (fileInputStream != null) {
                    fileInputStream.close()
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun createFile(file: File) {
        try {
            file.parentFile.mkdirs()
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun hideSoftInput() {
        if (inputMethodManager == null) {
            inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        }
        inputMethodManager!!.hideSoftInputFromWindow(cloverEt.getWindowToken(), 0)
        cloverEt.clearFocus()

        inputMethodManager!!.hideSoftInputFromWindow(raffleEt.getWindowToken(), 0)
        raffleEt.clearFocus()
    }

    /**
     * 把16进制字符串转换成字节数组
     */
    fun hexStringToByte(hex: String): ByteArray {
        val len = hex.length / 2
        val result = ByteArray(len)
        val achar = hex.toCharArray()
        for (i in 0 until len) {
            val pos = i * 2
            result[i] = (toByte(achar[pos]) shl 4 or toByte(achar[pos + 1])).toByte()
            if (result[i].toInt() == 0) {
                result[i] = 0
            }
        }
        return result
    }

    private fun toByte(c: Char): Int {
        val b = indexOf("0123456789ABCDEF", c).toByte()
        return b.toInt()
    }
}
