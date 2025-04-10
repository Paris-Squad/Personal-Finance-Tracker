package org.example.common

fun <T> check(actual: T, expected: T, name: String) {
    if (actual == expected) {
        println("✓ - $name")
    } else {
        System.err.println("✗ - $name. Expected $expected, but got $actual")
    }
}