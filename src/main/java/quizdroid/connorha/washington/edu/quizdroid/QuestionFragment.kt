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

    private var selectedOptionIndex = -1
    private var score = 0
    private var questionIndex = -1
    private var topicQuestions = arrayOf("")
    private var topicOptions = arrayOf("")
    private var topicAnswers = arrayOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            score = arguments!!.getInt("score")
            questionIndex = arguments!!.getInt("questionIndex")
            topicQuestions = arguments!!.getStringArray("topicQuestions")
            topicOptions = arguments!!.getStringArray("topicOptions")
            topicAnswers = arguments!!.getStringArray("topicAnswers")
        }
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
        textQuestion.text = topicQuestions[questionIndex]
    }

    private fun setAnswerOptions(view: View) {
        val radioButton1 : RadioButton = view.findViewById(R.id.radioButton1)
        val radioButton2 : RadioButton = view.findViewById(R.id.radioButton2)
        val radioButton3 : RadioButton = view.findViewById(R.id.radioButton3)
        val radioButton4 : RadioButton = view.findViewById(R.id.radioButton4)
        radioButton1.text = topicOptions[0]
        radioButton2.text = topicOptions[1]
        radioButton3.text = topicOptions[2]
        radioButton4.text = topicOptions[3]
    }

    private fun setOptionOnCheckedListener(view: View) {
        val answerOptionsRadioGroup : RadioGroup = view.findViewById(R.id.answerOptionsRadioGroup)
        val buttonSubmit : Button = view.findViewById(R.id.buttonSubmit)
        answerOptionsRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            buttonSubmit.isEnabled = true
            val numDifferenceBetweenIdAndIndex = 2131230824
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
        instance.putInt("selectedOptionIndex", selectedOptionIndex)
        instance.putInt("score", score)
        instance.putInt("questionIndex", questionIndex)
        instance.putStringArray("topicQuestions", topicQuestions)
        instance.putStringArray("topicOptions", topicOptions)
        instance.putStringArray("topicAnswers", topicAnswers)
        return instance
    }

}
