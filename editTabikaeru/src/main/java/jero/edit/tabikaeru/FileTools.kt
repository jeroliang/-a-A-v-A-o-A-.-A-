package jero.edit.tabikaeru

import android.os.Environment
import java.io.File

/**
 * Created by Jero on 2018/2/26 0026.
 */
object FileTools {

    private val FILE_PATH = Environment.getExternalStorageDirectory().toString() + File.separator + "Android/data/"
    private val SAVE_PATH = "/files/"
    private val SAVE_NAME = "Tabikaeru.sav"
    private val BACK_EXT = "back"
    private val IMG_NAME = "album"

    fun getSaveFile(pkName: String): File {
        return File(FILE_PATH + pkName + SAVE_PATH + SAVE_NAME)
    }

    fun getBackFile(pkName: String): File {
        return File(FILE_PATH + pkName + SAVE_PATH)
    }

    fun getBackList(file: File): Array<String> {
        val filelist: MutableList<String> = mutableListOf()
        file.walk().maxDepth(1)
                .filter { it.isFile }
                .filter { it.name.startsWith("T") }
                .forEach { filelist.add(it.name) }
        return filelist.toTypedArray()
    }

    fun getBackList(pkName: String): Array<String> {
        return getBackList(getBackFile(pkName))
    }

}