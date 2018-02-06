package jero.edit.tabikaeru

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils.indexOf
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import jero.edit.tabikaeru.R.id.spinner



class MainActivity : AppCompatActivity() {
    private var inputMethodManager: InputMethodManager? = null
    private var FILE_PATH = Environment.getExternalStorageDirectory().toString() +
            File.separator + "Android/data/" +
            "jp.co.hit_point.tabikaeru.st" +
            "/files/Tabikaeru.sav"

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
        button.setOnClickListener(View.OnClickListener {
            if (cloverEt.getText().toString() == "" || raffleEt.getText().toString() == "") {
                return@OnClickListener
            }
            val cloverHex = String.format("%06X", Integer.valueOf(cloverEt.getText().toString()))
            val couponHex = String.format("%06X", Integer.valueOf(raffleEt.getText().toString()))
            Log.d("123", " " + cloverHex)
            Log.d("123", " " + couponHex)
            writeToFile(cloverHex, couponHex)
        })
    }

    fun writeToFile(cloverHex: String, couponHex: String) {
        var fileInputStream: FileInputStream? = null
        var fileOutputStream: FileOutputStream? = null
        val file = File(FILE_PATH)
        val newFile = File(FILE_PATH)
        val cloverByteArray = hexStringToByte(cloverHex)
        val couponByteArray = hexStringToByte(couponHex)
        if (!file.exists()) {
            Log.d("123", "未找到文件Tabikaeru.sav")
            return
        }
        try {
            fileInputStream = FileInputStream(file)
            val arrayOfByte = ByteArray(fileInputStream.available())
            Log.d("123", "文件大小" + arrayOfByte.size)
            fileInputStream.read(arrayOfByte)
            if (arrayOfByte.size > 29) {
                file.delete()
                Log.d("123", "删除旧文件")
                createFile(newFile)
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
                fileOutputStream = FileOutputStream(newFile)
                fileOutputStream.write(arrayOfByte)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show()
            hideSoftInput()
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
