package com.example.swipequiz

import android.view.View

data class Question (
    var question: String,
    var answer: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            "A 'val' and a 'var' are the same.",
            "A Sheep is an Animal.",
            "Kotlin is based on JAVA.",
            "The sky is green.",
            "The sun is a star.",
            "Programming is fun",
            "A fish can swim",
            "A dog can fly",
            "A giraffe has a long neck",
            "It rains a lot in the Netherlands",
            "Rotterdam is the capital of the Netherlands",
            "Sitting all day is bad for the human body"
        )

        val QUESTION_ANSWER = arrayOf(
            false,
            true,
            true,
            false,
            true,
            true,
            true,
            false,
            true,
            true,
            false,
            true
        )
    }
}