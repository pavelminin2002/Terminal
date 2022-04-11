package org.example

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.Option
import org.kohsuke.args4j.CmdLineParser

fun main(args: Array<String>) {
    TerminalWork().getDistribution(args)
}

class TerminalWork {
    @Option(name = "-c", forbids = ["-d"], metaVar = "Encryption", usage = "encryption key")
    private var encryptionKey: String = ""

    @Option(name = "-d", forbids = ["-c"], metaVar = "Decryption", usage = "decryption key")
    private var decryptionKey: String = ""

    @Argument(metaVar = "InputName", usage = "Intput File name", required = true)
    private var inputName: String = ""

    @Option(name = "-o", metaVar = "OutputName", usage = "Output File name")
    private var outputName: String = ""

    private fun argumentsDistribution(args: Array<String>) {
        val parser = CmdLineParser(this)
        val suffix: String
        val key: String
        val arg: Collection<String>
        arg = args.toList()
        try {
            parser.parseArgument(arg)
            if (encryptionKey != "") {
                if (!encryptionKey.matches(Regex("""[0-9a-f]+""")))
                    throw IllegalArgumentException("Неверный формат ключа")
                suffix = "Encrypted.txt"
                key = workWithKey(encryptionKey)
            } else if (decryptionKey != "") {
                if (!decryptionKey.matches(Regex("""[0-9a-f]+""")))
                    throw IllegalArgumentException("Неверный формат ключа")
                suffix = "Decrypted.txt"
                key = workWithKey(decryptionKey)
            } else throw IllegalArgumentException("Вы ключ обронили :)")
            if (outputName == "") outputName = inputName.removeSuffix(".txt") + suffix
            workWithFile(inputName, outputName, key)
        } catch (Ex: IllegalArgumentException) {
            println(Ex.message)
            throw IllegalArgumentException()
        }
    }
    fun getDistribution(args: Array<String>){
        argumentsDistribution(args)
    }

    private fun workWithKey(key: String): String {
        var result = ""
        for (i in key.indices) {
            result += key[i].toString().toInt(16).toChar()
        }
        return result
    }

    fun getWorkWithKey(key: String):String{
        return workWithKey(key)
    }
}