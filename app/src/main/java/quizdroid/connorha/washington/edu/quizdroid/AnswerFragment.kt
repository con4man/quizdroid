package quizdroid.connorha.washington.edu.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class AnswerFragment : Fragment() {

    private lateinit var topic : Topic
    private var topicId = -1
    private var selectedOptionIndex = -1
    private var score = 0
    private var questionIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            topicId = arguments!!.getInt("topicId")
            selectedOptionIndex = arguments!!.getInt("selectedOptionIndex")
            score = arguments!!.getInt("score")
            questionIndex = arguments!!.getInt("questionIndex")
        }
        topic = QuizApp.instance.getTopic(topicId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_answer, container, false) as View
        displaySubmittedAnswer(view)
        displayCorrectAnswer(view)
        displayScore(view)
        if (isLastQuestion()) {
            changeNextButtonLabelToFinish(view)
        }
        setNextButtonOnClickListener(view)
        return view
    }

    private fun displaySubmittedAnswer(view: View) {
        val questionOptions = getQuestionOptions()
        val stringToDisplay = "You chose the answer: " + questionOptions[selectedOptionIndex]
        val textSubmittedAnswer : TextView = view.findViewById(R.id.textSubmittedAnswer)
        textSubmittedAnswer.text = stringToDisplay
    }

    private fun displayCorrectAnswer(view: View) {
        val questionOptions = getQuestionOptions()
        val answerIndex = getAnswerIndex()
        val stringToDisplay = "The correct answer was: " + questionOptions[answerIndex]
        val textCorrectAnswer : TextView = view.findViewById(R.id.textCorrectAnswer)
        textCorrectAnswer.text = stringToDisplay
    }

    private fun getQuestionOptions() : Array<String> {
        return topic.questions[questionIndex].options
    }

    private fun getAnswerIndex() : Int {
        return topic.questions[questionIndex].answerIndex
    }

    private fun displayScore(view: View) {
        val answerIndex = getAnswerIndex()
        if (selectedOptionIndex == answerIndex) {
            score++
        }
        var stringToDisplay = ""
        if (isLastQuestion()) {
            stringToDisplay = "You got " + score + " out of " + topic.questions.size+ " correct"
        } else {
            stringToDisplay = "You have gotten " + score + " out of " + topic.questions.size + " correct so far"
        }
        val textScore : TextView = view.findViewById(R.id.textScore)
        textScore.text = stringToDisplay
    }

    private fun isLastQuestion(): Boolean {
        return questionIndex == topic.questions.size -1
    }

    private fun changeNextButtonLabelToFinish(view: View) {
        val buttonNext : Button = view.findViewById(R.id.buttonNext)
        buttonNext.text = "Finish"
    }

    private fun setNextButtonOnClickListener(view: View) {
        if (!isLastQuestion()) {
            val buttonNext : Button = view.findViewById(R.id.buttonNext)
            buttonNext.setOnClickListener {
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                val instance = createInstance()
                val questionFragment = QuestionFragment()
                questionFragment.arguments = instance
                fragmentTransaction.replace(R.id.frameFragment, questionFragment).commit()
            }
        } else {
            val buttonNext : Button = view.findViewById(R.id.buttonNext)
            buttonNext.setOnClickListener {
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                val instance = createInstance()
                val questionFragment = QuestionFragment()
                questionFragment.arguments = instance
                fragmentTransaction.replace(R.id.frameFragment, questionFragment).commit()
            }
            buttonNext.setOnClickListener {
                val intent = Intent(activity, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
        }
    }

    private fun createInstance(): Bundle {
        val instance = Bundle()
        instance.putInt("topicId", topicId)
        instance.putInt("score", score)
        instance.putInt("questionIndex", questionIndex + 1)
        return instance
    }

}
