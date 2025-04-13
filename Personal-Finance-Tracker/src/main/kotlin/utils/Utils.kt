package org.example.utils

fun <T> check( name: String,actual: T, expected: T) {
    if (actual == expected) {
        println("✓ - $name")
    } else {
        System.err.println("✗ - $name. Expected $expected, but got $actual")
    }
}