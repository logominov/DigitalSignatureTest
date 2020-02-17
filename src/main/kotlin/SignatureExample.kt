import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.Signature


fun main() {
    val secureRandom = SecureRandom()
    val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("DSA")
    val keyPair: KeyPair = keyPairGenerator.generateKeyPair()

    val signature: Signature = Signature.getInstance("SHA256WithDSA")

    signature.initSign(keyPair.private, secureRandom)

    val data = "abcdefghijklmnopqrstuvxyz".toByteArray(charset("UTF-8"))
    signature.update(data)

    val digitalSignature: ByteArray = signature.sign()

    val signature2: Signature = Signature.getInstance("SHA256WithDSA")
    signature2.initVerify(keyPair.public)

    signature2.update(data)

    val verified: Boolean = signature2.verify(digitalSignature)
    println("verified = $verified")
}