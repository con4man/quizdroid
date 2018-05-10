package quizdroid.connorha.washington.edu.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionFragment : Fragment() {

    private lateinit var topic : Topic
    private var topicId = -1
    private var selectedOptionIndex = -1
    private var score = 0
    private var questionIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            topicId = arguments!!.getInt("topicId")
            score = arguments!!.getInt("score")
            questionIndex = arguments!!.getInt("questionIndex")
        }
        topic = QuizApp.instance.getTopic(topicId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false) as View
        disableSubmitButton(view)
        setQuestionText(view)
        setAnswerOptions(view)
        setOptionOnCheckedListener(view)
        setSubmitButtonOnClickedListener(view)
        return view
    }

    private fun disableSubmitButton(view: View) {
        val buttonSubmit : Button = view.findViewById(R.id.buttonSubmit)
        buttonSubmit.isEnabled = false
    }

    private fun setQuestionText(view: View) {
        val textQuestion : TextView = view.findViewById(R.id.textQuestion)
        textQuestion.text = topic.questions[questionIndex].question
    }

    private fun setAnswerOptions(view: View) {
        val radioButton1 : RadioButton = view.findViewById(R.id.radioButton1)
        val radioButton2 : RadioButton = view.findViewById(R.id.radioButton2)
        val radioButton3 : RadioButton = view.findViewById(R.id.radioButton3)
        val radioButton4 : RadioButton = view.findViewById(R.id.radioButton4)
        val questionOptions = topic.questions[questionIndex].options
        radioButton1.text = questionOptions[0]
        radioButton2.text = questionOptions[1]
        radioButton3.text = questionOptions[2]
        radioButton4.text = questionOptions[3]
    }

    private fun setOptionOnCheckedListener(view: View) {
        val answerOptionsRadioGroup : RadioGroup = view.findViewById(R.id.answerOptionsRadioGroup)
        val buttonSubmit : Button = view.findViewById(R.id.buttonSubmit)
        answerOptionsRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            buttonSubmit.isEnabled = true
            val numDifferenceBetweenIdAndIndex = 2131165292
            selectedOptionIndex = i - numDifferenceBetweenIdAndIndex
        })
    }

    private fun setSubmitButtonOnClickedListener(view: View) {
            val buttonSubmit : Button = view.findViewById(R.id.buttonSubmit)
            buttonSubmit.setOnClickListener {
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                val instance = createInstance()
                val answerFragment = AnswerFragment()
                answerFragment.arguments = instance
                fragmentTransaction.replace(R.id.frameFragment, answerFragment).commit()
            }
    }

    private fun createInstance(): Bundle {
        val instance = Bundle()
        instance.putInt("topicId", topicId)
        instance.putInt("selectedOptionIndex", selectedOptionIndex)
        instance.putInt("score", score)
        instance.putInt("questionIndex", questionIndex)
        return instance
    }

}
