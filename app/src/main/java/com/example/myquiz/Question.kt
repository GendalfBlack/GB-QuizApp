package com.example.myquiz

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_qText = "questionText"
private const val ARG_a1 = "answer1"
private const val ARG_a2 = "answer2"
private const val ARG_a3 = "answer3"
private const val ARG_a4 = "answer4"
private const val ARG_points = "points"
private const val ARG_correct = "correctIndex"

typealias bindQuestion = (value : Int?) -> Unit

class Question : Fragment() {
    lateinit var answerEvent : bindQuestion
    private var questionText: String? = null
    private var answer1: String? = null
    private var answer2: String? = null
    private var answer3: String? = null
    private var answer4: String? = null

    var points : Int? = null
    private var correctIndex : Int? = null
    var answered : Boolean = false

    fun getCorrect(i : Int) : Int? {
        answered = true
        if (i == correctIndex){ return points }
        else{ return 0 }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionText = it.getString(ARG_qText)
            answer1 = it.getString(ARG_a1)
            answer2 = it.getString(ARG_a2)
            answer3 = it.getString(ARG_a3)
            answer4 = it.getString(ARG_a4)
            points = it.getInt(ARG_points)
            correctIndex = it.getInt(ARG_correct)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        val text : TextView = view.findViewById(R.id.questionTextView)
        val b1 : Button = view.findViewById(R.id.buttonA1)
        val b2 : Button = view.findViewById(R.id.buttonA2)
        val b3 : Button = view.findViewById(R.id.buttonA3)
        val b4 : Button = view.findViewById(R.id.buttonA4)

        text.text = questionText
        b1.text = answer1
        b2.text = answer2
        b3.text = answer3
        b4.text = answer4
        b1.setOnClickListener { onButtonClick(b1, 1)}
        b2.setOnClickListener { onButtonClick(b2, 2) }
        b3.setOnClickListener { onButtonClick(b3, 3) }
        b4.setOnClickListener { onButtonClick(b4, 4) }

        return view
    }
    @SuppressLint("ResourceAsColor")
    fun onButtonClick(button: Button, position : Int){
        if(!answered) {
            val p = getCorrect(position)
            answerEvent(p)
            if (p != null) {
                val colorCorrect = ContextCompat.getColor(button.context, R.color.correct)
                val colorWrong = ContextCompat.getColor(button.context, R.color.wrong)
                val colorListCorrect = ColorStateList.valueOf(colorCorrect)
                val colorListWrong = ColorStateList.valueOf(colorWrong)
                if (p > 0) button.backgroundTintList = colorListCorrect
                else button.backgroundTintList = colorListWrong
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(
            questionText: String,
            answer1: String,
            answer2: String,
            answer3: String,
            answer4: String,
            points : Int,
            correct : Int
        ) =
            Question().apply {
                arguments = Bundle().apply {
                    putString(ARG_qText, questionText)
                    putString(ARG_a1, answer1)
                    putString(ARG_a2, answer2)
                    putString(ARG_a3, answer3)
                    putString(ARG_a4, answer4)
                    putInt(ARG_points, points)
                    putInt(ARG_correct, correct)
                }
            }
    }
}
