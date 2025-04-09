package org.example.test

fun main(){
    check(
        name = "When removing from empty list, then should return false",
        result=false,
        expected =  false
    )
    check(
        name = "When removing existing transaction, then should return true",
        result = true,
        expected = true
    )
    check(
        name = "When removing same data but different ID, then should return false",
        result =  false ,
        expected =  false
    )
    check(
        name = "When removing one of multiple transactions, then should return true",
        result= true,
        expected = true
    )
}

 fun <T>check(name: String, result: T, expected: T) {
    if (result == expected) {
        println("Successful: $name")
    } else {
        println("X-Failed: $name | Expected: $expected, Got: $result")
    }
}