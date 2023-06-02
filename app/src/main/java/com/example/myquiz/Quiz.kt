package com.example.myquiz

import android.annotation.SuppressLint
import android.widget.TextView

class Quiz {
    private val questions : ArrayList<Question> = ArrayList()
    lateinit var current : Question
    var points : Int = 0
    var maxPoints : Int = 0
    lateinit var textViewForPoints : TextView
    fun addQuestion(q : Question){
        questions.add(q)
        q.answerEvent = { checkAnwer(it)}
        if (questions.isNotEmpty()){current = questions[0]}
    }

    @SuppressLint("SetTextI18n")
    fun checkAnwer(points : Int?){
        if (points != null) {
            this.points += points
            textViewForPoints.text = "Points: $points"
        }
    }
}