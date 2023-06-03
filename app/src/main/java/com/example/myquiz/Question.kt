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
private const val ARG_answers = arrayOf("answers")
private const val ARG_points = "points"
private const val ARG_correct = "correctIndex"

typealias bindQuestion = (value : Int?) -> Unit

class Question : Fragment() {
    lateinit var answerEvent : bindQuestion
    private var questionText: String? = null
    lateinit private var answers: ArrayList<String?>

    var points : Int? = null
    private var correctIndex : Int? = null
    private var incorrectIndex : Int = 0
    var answered : Boolean = false

    fun getCorrect(i : Int) : Int? {
        answered = true
        if (i == correctIndex){ return points }
        else{
            incorrectIndex = i
            return 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionText = it.getString(ARG_qText)
            answers = it.getStringArrayList(ARG_answers)!!
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

        val buttons : ArrayList<Button> = ArrayList()

        buttons.add(view.findViewById(R.id.buttonA1))
        buttons.add(view.findViewById(R.id.buttonA2))
        buttons.add(view.findViewById(R.id.buttonA3))
        buttons.add(view.findViewById(R.id.buttonA4))

        text.text = questionText
        for(i in 0 until 4){
            if (i < answers.size){
                buttons[i].text = answers[i]
                buttons[i].setOnClickListener { onButtonClick(buttons[i], i + 1)}
            } else { buttons[i].visibility = View.GONE }
        }

        if (answered){
            if (incorrectIndex == 0){
                val button = buttons[correctIndex?.minus(1)!!]
                val colorCorrect = ContextCompat.getColor(button.context, R.color.correct)
                val colorListCorrect = ColorStateList.valueOf(colorCorrect)
                button.backgroundTintList = colorListCorrect
            }else{
                val button = buttons[incorrectIndex - 1]
                val colorWrong = ContextCompat.getColor(button.context, R.color.wrong)
                val colorListWrong = ColorStateList.valueOf(colorWrong)
                button.backgroundTintList = colorListWrong
            }
        }

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
            answers: Array<out String?>,
            points : Int,
            correct : Int
        ) =
            Question().apply {
                arguments = Bundle().apply {
                    putString(ARG_qText, questionText)
                    putStringArray(ARG_answers, answers)
                    putInt(ARG_points, points)
                    putInt(ARG_correct, correct)
                }
            }
    }
}
