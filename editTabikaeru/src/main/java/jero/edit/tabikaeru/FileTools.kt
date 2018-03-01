package jero.edit.tabikaeru

import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Jero on 2018/2/26 0026.
 */
object FileTools {

    private val FILE_PATH = Environment.getExternalStorageDirectory().toString() + File.separator + "Android/data/"
    private val SAVE_PATH = "/files/"
    private val SAVE_NAME = "Tabikaeru.sav"
    private val BACK_START = "back"
    private val IMG_START = "album"

    fun getSaveFile(pkName: String): File {
        return File(FILE_PATH + pkName + SAVE_PATH + SAVE_NAME)
    }

    fun getRootDirectory(pkName: String): File {
        return File(FILE_PATH + pkName + SAVE_PATH)
    }

    fun getBackDirectory(pkName: String): File {
        return File(FILE_PATH + pkName + SAVE_PATH)
    }

    fun getBackFile(pkName: String, name: String): File {
        return File(FILE_PATH + pkName + SAVE_PATH + name)
    }

    fun newBackFile(pkName: String): File {
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSS").format(Date())
        return File(FILE_PATH + pkName + SAVE_PATH + BACK_START + date)
    }

    fun getBackList(file: File): Array<String> {
        val filelist: MutableList<String> = mutableListOf()
        file.walk().maxDepth(1)
                .filter { it.isFile }
                .filter { it.name.startsWith(BACK_START) }
                .forEach {
                    filelist.add(it.name)
                }
        return filelist.toTypedArray()
    }

    fun getBackList(pkName: String): Array<String> {
        return getBackList(getBackDirectory(pkName))
    }

    fun delBackList(pkName: String): Int {
        var num = 0
        getBackDirectory(pkName).walk().maxDepth(1)
                .filter { it.isFile }
                .filter { it.name.startsWith(BACK_START) }
                .forEach { if (!it.delete()) num-- }
        return num
    }

}