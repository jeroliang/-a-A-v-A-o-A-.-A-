package jero.edit.tabikaeru

/**
 * Created by Jero on 2018/2/26 0026.
 */
object Tools {
    /**
     * byte[]与int转换
     */
    fun byteArrayToInt(b: ByteArray): Int {
        return b[3].toInt() and 0xFF or (b[2].toInt() and 0xFF shl 8) or (b[1].toInt() and 0xFF shl 16) or (b[0].toInt() and 0xFF shl 24)
    }

    fun intToByteArray(a: Int): ByteArray {
        return byteArrayOf((a shr 24 and 0xFF).toByte(), (a shr 16 and 0xFF).toByte(), (a shr 8 and 0xFF).toByte(), (a and 0xFF).toByte())
    }
}