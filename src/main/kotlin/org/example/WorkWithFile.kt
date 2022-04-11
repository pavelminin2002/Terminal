package org.example

import java.io.File
import java.io.FileNotFoundException

fun workWithFile(inputName: String, outputName: String, key: String) {
    var indexKey=0
    File(outputName).bufferedWriter().use {
        try {
            val reader = File(inputName)
            for (line in reader.readLines()){
                var nwLine=""
                for (i in line.indices){
                    if (indexKey==key.length) indexKey=0
                    nwLine+=(line[i].code xor key[indexKey].code).toChar()
                    indexKey++
                }
                it.write(nwLine)
                it.newLine()
            }
        } catch (Ex: FileNotFoundException){
            println(Ex.message)
            throw FileNotFoundException()
        }
    }
}

