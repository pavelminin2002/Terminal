package org.example

import org.junit.Test
import java.io.File
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import java.io.FileNotFoundException

class HelloTest {

    @Test
    fun workWithKey() {
        val x = TerminalWork()
        assertEquals(x.getWorkWithKey("1a3"), "" + 1.toChar() + 10.toChar() + 3.toChar())
        assertEquals(x.getWorkWithKey("ba3"), "" + 11.toChar() + 10.toChar() + 3.toChar())
        assertEquals(x.getWorkWithKey(""), "")
    }

    @Test
    fun filesWork() {
        fun conformity(inputName: String, outputName1: String, outputName2: String, key1: String, key2: String) {
            workWithFile(inputName, outputName1, key1)
            workWithFile(outputName1, outputName2, key2)
            val inp = File(inputName).readLines().joinToString("\n")
            val outp = File(outputName2).readLines().joinToString("\n")
            if (key1 == key2) assertEquals(inp, outp)
            else assertFalse(inp == outp)
        }
        conformity("test.txt", "testEncrypted.txt", "testDecrypted.txt", "11", "11")
        conformity("test.txt", "testEncrypted.txt", "testDecrypted.txt", "ab", "ab")
        conformity("test.txt", "testEncrypted.txt", "testDecrypted.txt", "ab", "a")
    }

    @Test
    fun terminal(){
        val x = TerminalWork()
        assertThrows(IllegalArgumentException::class.java){x.getDistribut(arrayOf("-c","zz","test.txt"))}
        assertThrows(IllegalArgumentException::class.java){x.getDistribut(arrayOf("-c","","test.txt"))}
        assertThrows(IllegalArgumentException::class.java){x.getDistribut(arrayOf("-c","!","test.txt"))}
        assertThrows(IllegalArgumentException::class.java){x.getDistribut(arrayOf("-d","abz","test.txt"))}
        assertThrows(IllegalArgumentException::class.java){x.getDistribut(arrayOf("-d","","test.txt"))}
        assertThrows(FileNotFoundException::class.java){x.getDistribut(arrayOf("-c","abc","notTest.txt"))}
    }
}
