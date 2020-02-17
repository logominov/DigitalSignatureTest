import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.Signature


fun main() {
    val signature: Signature =
        Signature.getInstance("SHA256WithDSA") // создаем экземпляр класса Signature для его использования

    val secureRandom = SecureRandom()
    val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("DSA")
    val keyPair: KeyPair =
        keyPairGenerator.generateKeyPair() // экземпляр Signature инициализируется закрытым ключом пары секретный/открытый ключ и экземпляром SecureRandom.

    signature.initSign(
        keyPair.private,
        secureRandom
    ) // После создания экземпляра Signature необходимо инициализировать его, перед тем как начать использовать его.

    // Когда экземпляр Signature инициализирован, можно использовать его для создания цифровых подписей.
    // Цифровая подпись создается вызывом метода update() (один или несколько раз) и заканчивая вызовом sign().
    val data = "abcdefghijklmnopqrstuvxyz".toByteArray(charset("UTF-8"))
    signature.update(data)
    val digitalSignature = signature.sign()

    // Проверка цифровой подписи
    // Если вы хотите проверить цифровую подпись, нужно инициализировать экземпляр подписи в режиме проверки (вместо режима подписи).
    // Экземпляр Signature теперь инициализируется в режиме проверки, передавая открытый ключ от пары ключей в качестве параметра:

    val signature2 = Signature.getInstance("SHA256WithDSA")
    signature2.initVerify(keyPair.public)

    // После инициализации в режиме проверки можно использовать экземпляр Signature для проверки цифровой подписи:

    val data2 = "abcdefghijklmnopqrstuvxyz".toByteArray(charset("UTF-8"))
    signature2.update(data2)

    val verified: Boolean = signature2.verify(digitalSignature)
    println("verified = $verified")
}