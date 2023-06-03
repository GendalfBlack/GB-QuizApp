package com.example.myquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.navigationView)
        val headerView = navigationView.getHeaderView(0)

        val quiz = Quiz()
        quiz.supportFragmentManager = supportFragmentManager
        quiz.textViewForPoints = this.findViewById(R.id.pointsTextView)

        val q : Question = Question.newInstance(
            "Під час буксирування яким із способів пасажири можуть перебувати в буксированому транспортному засобі?",
            arrayOf(
                "Завжди, під час будь-якого буксирування – в будь-якому буксированому автомобілі.",
                "Під час буксирування на жорсткому або гнучкому зчепленні – в легковому автомобілі.",
                "Під час буксирування на жорсткому зчепленні в світлу пору доби.",
                "Під час буксирування на жорсткому зчепленні – у вантажному автомобілі."),
            5,
            2
        )
        quiz.addQuestion(q)

        val q2 : Question = Question.newInstance(
            "Під час буксирування яким із способів пасажири можуть перебувати в буксированому транспортному засобі?",
            arrayOf(
                "Завжди, під час будь-якого буксирування – в будь-якому буксированому автомобілі.",
                "Під час буксирування на жорсткому або гнучкому зчепленні – в легковому автомобілі.",
                "Під час буксирування на жорсткому зчепленні в світлу пору доби."),
            5,
            2
        )
        quiz.addQuestion(q2)
        val q3 : Question = Question.newInstance(
            "Під час буксирування яким із способів пасажири можуть перебувати в буксированому транспортному засобі?",
            arrayOf(
                "Завжди, під час будь-якого буксирування – в будь-якому буксированому автомобілі.",
                "Під час буксирування на жорсткому або гнучкому зчепленні – в легковому автомобілі."),
            5,
            2
        )
        quiz.addQuestion(q3)

        quiz.showQuestion()
        val prevButton : Button = findViewById(R.id.previousQuestion)
        val nextButton : Button = findViewById(R.id.nextQuestion)

        prevButton.setOnClickListener { quiz.prevQuestion() }
        nextButton.setOnClickListener { quiz.nextQuestion() }
    }

}