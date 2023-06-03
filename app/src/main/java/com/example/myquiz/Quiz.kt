package com.example.myquiz

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.fragment.app.FragmentManager

class Quiz {
    private val questions : ArrayList<Question> = ArrayList()
    lateinit var current : Question
    var points : Int = 0
    lateinit var textViewForPoints : TextView
    lateinit var supportFragmentManager : FragmentManager
    fun addQuestion(q : Question){
        questions.add(q)
        q.answerEvent = { checkAnswer(it)}
        if (questions.isNotEmpty()){current = questions[0]}
    }

    @SuppressLint("SetTextI18n")
    fun checkAnswer(points : Int?){
        if (points != null) {
            this.points += points
            textViewForPoints.text = "Бали: $points"
        }
    }

    fun showQuestion(){
        val manager : FragmentManager = supportFragmentManager
        val transition = manager.beginTransaction()
        transition.replace(R.id.questionContainer, current)
        transition.commit()
    }

    fun prevQuestion(){
        val currindex = questions.indexOf(current)
        if(currindex == 0){ return }
        else{
            current = questions[currindex - 1]
            showQuestion()
        }
    }
    fun nextQuestion(){
        val currindex = questions.indexOf(current)
        if(currindex == questions.lastIndex){ return }
        else{
            current = questions[currindex + 1]
            showQuestion()
        }
    }

}