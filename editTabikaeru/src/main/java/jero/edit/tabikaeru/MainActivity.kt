package jero.edit.tabikaeru

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var inputMethodManager: InputMethodManager
    private var selectPk = 0
    private val pkName: Array<String> = arrayOf("jp.co.hit_point.tabikaeru.jero", "jp.co.hit_point.tabikaeru", "jp.co.hit_point.tabikaeru.st")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        readFile()
    }

    private fun initView() {
        val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, arrayOf("jero版本", "官方版本/3DM版本", "手谈版本", "其他版本"))
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectPk = position
                edit_lay.visibility = if (position == 3) View.VISIBLE else View.GONE
            }
        }
        ok.setOnClickListener({ readFile() })
        save.setOnClickListener(View.OnClickListener {
            hideSoftInput()
            if (TextUtils.isEmpty(cloverEt.text) || TextUtils.isEmpty(raffleEt.text)) {
                Snackbar.make(container, "两项都必须填写", Snackbar.LENGTH_LONG).show()
                return@OnClickListener
            }
            writeToFile(cloverEt.text.toString(), raffleEt.text.toString())
        })
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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
        val file = FileTools.getSaveFile(getPkName())
        if (!file.exists()) {
            cloverEt.setText("")
            raffleEt.setText("")
            Snackbar.make(container, "未找到存档文件,请切换版本选择", Snackbar.LENGTH_LONG).show()
            return
        }
        val arrayOfByte = file.readBytes()
        if (arrayOfByte.size > 29) {
            //三叶草
            cloverEt.setText(Tools.byteArrayToInt(arrayOfByte.slice(22..25).toByteArray()).toString())
            //抽奖券
            raffleEt.setText(Tools.byteArrayToInt(arrayOfByte
                    .slice(26..29).toByteArray()).toString())
        }
    }

    private fun writeToFile(cloverHex: String, couponHex: String) {
        val file = FileTools.getSaveFile(getPkName())
        if (!file.exists()) {
            Snackbar.make(container, "未找到存档文件", Snackbar.LENGTH_LONG)
                    .setAction("重试", { save.callOnClick() }).show()
            return
        }
        val cloverByteArray = Tools.intToByteArray(Integer.valueOf(cloverHex))
        val couponByteArray = Tools.intToByteArray(Integer.valueOf(couponHex))
        val arrayOfByte = file.readBytes()
        if (arrayOfByte.size > 29) {
            //三叶草
            arrayOfByte[22] = cloverByteArray[0]
            arrayOfByte[23] = cloverByteArray[1]
            arrayOfByte[24] = cloverByteArray[2]
            arrayOfByte[25] = cloverByteArray[3]

            //抽奖券
            arrayOfByte[26] = couponByteArray[0]
            arrayOfByte[27] = couponByteArray[1]
            arrayOfByte[28] = couponByteArray[2]
            arrayOfByte[29] = couponByteArray[3]

            file.writeBytes(arrayOfByte)
        }
        Snackbar.make(container, "保存完成", Snackbar.LENGTH_LONG).show()
    }

    private fun hideSoftInput() {
        inputMethodManager.hideSoftInputFromWindow(cloverEt.windowToken, 0)
        cloverEt.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(raffleEt.windowToken, 0)
        raffleEt.clearFocus()
    }

}
