import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.SecureRandom


fun main() {
    var secureRandom: SecureRandom = SecureRandom()
    var keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("DSA")
    var keyPair: KeyPair = keyPairGenerator.generateKeyPair()

    println(keyPair.public)

    val messageDigest = MessageDigest.getInstance("SHA-256")

    val data1 = "0123456789".toByteArray(charset("UTF-8"))
    val data2 = "abcdefghijklmnopqrstuvxyz".toByteArray(charset("UTF-8"))

    messageDigest.update(data1)
    messageDigest.update(data2)

    val digest = messageDigest.digest()

}


