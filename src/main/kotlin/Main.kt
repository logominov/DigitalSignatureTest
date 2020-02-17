import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.SecureRandom
import java.security.Security
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


var rnd: SecureRandom = SecureRandom()
var iv = IvParameterSpec(rnd.generateSeed(16))

fun main() {
    Security.addProvider(BouncyCastleProvider())
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

    val keyBytes = byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    val algorithm = "RawBytes"
    val key = SecretKeySpec(keyBytes, algorithm)

    cipher.init(Cipher.ENCRYPT_MODE, key, iv)

    val plainText = "abcdefghijklmnopqrstuvwxyz".toByteArray(charset("UTF-8"))
    val cipherText = cipher.doFinal(plainText)




    cipher.init(Cipher.DECRYPT_MODE, key, iv)
    val text = cipher.doFinal(cipherText).toString(charset("UTF-8"))

    println(text)

}