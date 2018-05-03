package quizdroid.connorha.washington.edu.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TopicOverviewFragment : Fragment() {


    private lateinit var topic : Topic
    private var topicId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            topicId = arguments!!.getInt("topicId")
        }
        topic = QuizApp.topics[topicId]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_topic_overview, container, false) as View
        setDescription(view)
        setNumberOfQuestions(view)
        setBeginButtonOnClickListener(view)
        return view
    }

    private fun setDescription(view: View) {
        val textDescription : TextView = view.findViewById(R.id.textDescription)
        val topicDescriptionString = topic.longDescription
        textDescription.text = topicDescriptionString
    }

    private fun setNumberOfQuestions(view: View) {
        val textNumQuestions : TextView = view.findViewById(R.id.textNumQuestions)
        val numQuestions = topic.questions.size
        val numQuestionsString = "There are $numQuestions questions"
        textNumQuestions.text = numQuestionsString
    }

    private fun setBeginButtonOnClickListener(view: View) {
        val buttonBegin : Button = view.findViewById(R.id.buttonBegin)
        buttonBegin.setOnClickListener {
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            val instance = createInstance()
            val questionFragment = QuestionFragment()
            questionFragment.arguments = instance
            fragmentTransaction.replace(R.id.frameFragment, questionFragment).commit()
        }
    }

    private fun createInstance(): Bundle {
        val instance = Bundle()
        instance.putInt("topicId", topicId)
        instance.putInt("questionIndex", 0)
        return instance
    }

}
