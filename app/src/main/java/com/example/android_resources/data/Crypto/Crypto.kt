package com.example.android_resources.data.Crypto

import android.util.Base64
import java.lang.Exception
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object Crypto {
    fun encrypt(strToEncrypt: String): String? {
        var keyBytes: ByteArray
        val secret_key: String = "662ede816988e58fb6d057d9d85605e0"
        try {
            keyBytes = secret_key.toByteArray(charset("UTF8"))
            val skey = SecretKeySpec(keyBytes, "AES")
            val input = strToEncrypt.toByteArray(charset("UTF8"))

            synchronized(Cipher::class.java) {
                val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
                cipher.init(Cipher.ENCRYPT_MODE, skey)

                val cipherText = ByteArray(cipher.getOutputSize(input.size))
                var ctLength = cipher.update(
                    input, 0, input.size,
                    cipherText, 0
                )
                ctLength += cipher.doFinal(cipherText, ctLength)
                return String(
                    Base64.encode(cipherText, 0)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}