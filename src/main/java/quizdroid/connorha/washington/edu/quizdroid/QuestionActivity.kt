package quizdroid.connorha.washington.edu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private var selectedOptionIndex = -1
    private var score = 0
    private var questionIndex = -1
    private var topicQuestions = arrayOf("")
    private var topicOptions = arrayOf("")
    private var topicAnswers = arrayOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        buttonSubmit.isEnabled = false
        getIntentData()
        setNavBarTitle()
        setQuestionText()
        createAnswerOptions()
        setOptionOnCheckedListener()
        setSubmitButtonOnClickedListener()
    }

    private fun getIntentData() {
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

    private fun setQuestionText() {
        textQuestion.text = topicQuestions[questionIndex]
    }

    private fun createAnswerOptions() {
        for (option in topicOptions) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            answerOptionsRadioGroup.addView(radioButton)
        }
    }

    private fun setOptionOnCheckedListener() {
        answerOptionsRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            buttonSubmit.isEnabled = true
            //lazy fix but to prevent errors with options on later activities to have id's > 4
            //Causes errors when taking a quiz for another topic
            selectedOptionIndex = i - 1 - (questionIndex * 4)
        })
    }

    private fun setSubmitButtonOnClickedListener() {
        buttonSubmit.setOnClickListener {
            val intent = Intent(this, AnswerActivity::class.java).apply {
                putExtra("selectedOptionIndex", selectedOptionIndex)
                putExtra("score", score)
                putExtra("questionIndex", questionIndex)
                putExtra("topicQuestions", topicQuestions)
                putExtra("topicOptions", topicOptions)
                putExtra("topicAnswers", topicAnswers)
            }
            startActivity(intent)
        }
    }
}
