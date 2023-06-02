package com.example.myquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quiz = Quiz()
        quiz.textViewForPoints = this.findViewById(R.id.pointsTextView)

        val q : Question = Question.newInstance(
            "Під час буксирування яким із способів пасажири можуть перебувати в буксированому транспортному засобі?",
            "Завжди, під час будь-якого буксирування – в будь-якому буксированому автомобілі.",
            "Під час буксирування на жорсткому або гнучкому зчепленні – в легковому автомобілі.",
            "Під час буксирування на жорсткому зчепленні в світлу пору доби.",
            "Під час буксирування на жорсткому зчепленні – у вантажному автомобілі.",
            5,
            2
        )
        quiz.addQuestion(q)

        val manager : FragmentManager = supportFragmentManager;
        val transition = manager.beginTransaction()
        transition.replace(R.id.questionContainer, q)
        transition.commit()

    }
}