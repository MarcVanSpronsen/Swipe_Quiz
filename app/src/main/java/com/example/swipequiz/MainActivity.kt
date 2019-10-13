package com.example.swipequiz

import android.graphics.Color
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import com.google.android.material.snackbar.Snackbar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AddQuestion()
        InitView()
    }

    private val allQuestions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(allQuestions)

    private fun InitView() {
        createItemTouchHelper().attachToRecyclerView(questions)
        questions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        questions.adapter = questionAdapter
        questions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun AddQuestion() {
        for(i in Question.QUESTIONS.indices){
            allQuestions.add(Question(Question.QUESTIONS[i], Question.QUESTION_ANSWER[i]))
        }
        questionAdapter.notifyDataSetChanged()

    }

    private fun CheckAnswer(questionView: View, question: Question, swipeDirection: Int) {
        if (question.answer == true && swipeDirection == LEFT) {
            Snackbar.make(findViewById(android.R.id.text1), "Correct! The answer was: true", Snackbar.LENGTH_SHORT).show()
            questionView.setBackgroundColor(Color.GREEN)
            return
        }
        if (question.answer == false && swipeDirection == RIGHT) {
            Snackbar.make(findViewById(android.R.id.text1), "Correct! The answer was: false", Snackbar.LENGTH_SHORT).show()
            questionView.setBackgroundColor(Color.GREEN)
            return
        }
        if (question.answer == true && swipeDirection == RIGHT) {
            Snackbar.make(findViewById(android.R.id.text1), "Incorrect! The answer was: true", Snackbar.LENGTH_SHORT).show()
            questionView.setBackgroundColor(Color.RED)
            return
        }
        if (question.answer == false && swipeDirection == LEFT) {
            Snackbar.make(findViewById(android.R.id.text1), "Incorrect! The answer was: false", Snackbar.LENGTH_SHORT).show()
            questionView.setBackgroundColor(Color.RED)
            return
        }
    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val VIEW = viewHolder.itemView
                CheckAnswer(VIEW, allQuestions.get(position), direction)
                questionAdapter.notifyItemChanged(position)
            }
        }
        return ItemTouchHelper(callback)
    }
}


