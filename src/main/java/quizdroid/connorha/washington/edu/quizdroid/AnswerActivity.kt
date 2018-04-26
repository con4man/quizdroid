package quizdroid.connorha.washington.edu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : AppCompatActivity() {

    private var selectedOptionIndex = -1
    private var score = -1
    private var questionIndex = -1
    private var topicQuestions = arrayOf("")
    private var topicOptions = arrayOf("")
    private var topicAnswers = arrayOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        getIntentData()
        displaySubmittedAnswer()
        setNavBarTitle()
        displayCorrectAnswer()
        displayScore()
        if (isLastQuestion()) {
            changeNextButtonLabelToFinish()
        }
        setNextButtonOnClickListener()
    }

    private fun displaySubmittedAnswer() {
        val stringToDisplay = "You chose the answer: " + topicOptions[selectedOptionIndex]
        textSubmittedAnswer.text = stringToDisplay
    }

    private fun displayCorrectAnswer() {
        val stringToDisplay = "The correct answer was: " + topicAnswers[questionIndex]
        textCorrectAnswer.text = stringToDisplay
    }

    private fun displayScore() {
        if (topicOptions[selectedOptionIndex] == topicAnswers[questionIndex]) {
            score++
        }
        var stringToDisplay = ""
        if (isLastQuestion()) {
            stringToDisplay = "You got " + score + " out of " + topicQuestions.size + " correct"
        } else {
            stringToDisplay = "You have gotten " + score + " out of " + topicQuestions.size + " correct so far"
        }
        textScore.text = stringToDisplay
    }

    private fun getIntentData() {
        selectedOptionIndex = intent.getIntExtra("selectedOptionIndex", 0)
        score = intent.getIntExtra("score", 0)
        questionIndex = intent.getIntExtra("questionIndex", 0)
        topicQuestions = intent.getStringArrayExtra("topicQuestions")
        topicOptions = intent.getStringArrayExtra("topicOptions")
        topicAnswers = intent.getStringArrayExtra("topicAnswers")
    }


    private fun setNavBarTitle() {
        val navTextToDisplay = "Question " + (questionIndex + 1)
        supportActionBar?.title = navTextToDisplay
    }

    private fun changeNextButtonLabelToFinish() {
        buttonNext.text = "Finish"
    }

    private fun isLastQuestion(): Boolean {
        Log.i("AnswerActivity","QuestiionInd = " + questionIndex + "topicSize + " + topicQuestions.size)
        return questionIndex == topicQuestions.size -1
    }

    private fun setNextButtonOnClickListener() {
        if (!isLastQuestion()) {
            buttonNext.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java).apply {
                    putExtra("selectedOptionIndex", 0)
                    putExtra("score", score)
                    putExtra("questionIndex", (questionIndex + 1))
                    putExtra("topicQuestions", topicQuestions)
                    putExtra("topicOptions", topicOptions)
                    putExtra("topicAnswers", topicAnswers)
                }
                startActivity(intent)
            }
        } else {
            buttonNext.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
        }
    }
}
