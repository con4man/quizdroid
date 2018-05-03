package quizdroid.connorha.washington.edu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_topic_overview.*

class TopicOverviewActivity : AppCompatActivity() {
    private var selectedTopicId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)
        getIntentData()
        setNavBarTitle()
        setDescription()
        setNumberOfQuestions()
        setBeginButtonOnClickListener()
    }

    private fun getIntentData() {
        selectedTopicId = intent.getIntExtra("selectedTopicId",-1)
    }

    private fun setNavBarTitle() {
        val topics = resources.getStringArray(R.array.topics)
        val topicName = topics[selectedTopicId]
        supportActionBar?.title = topicName
    }

    private fun setDescription() {
        val topicDescriptions = resources.getStringArray(R.array.topicDescriptions)
        textDescription.text = topicDescriptions[selectedTopicId]
    }

    private fun setNumberOfQuestions() {
        //hard coded for now
        val numQuestionsString = "There are 3 questions"
        textNumQuestions.text = numQuestionsString
    }

    private fun setBeginButtonOnClickListener() {
        val topicQuestions = getTopicQuestions()
        val topicOptions = getTopicOptions()
        val topicAnswers = getTopicAnswers()
        buttonBegin.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java).apply {
                putExtra("questionIndex", 0)
                putExtra("topicQuestions", topicQuestions)
                putExtra("topicOptions", topicOptions)
                putExtra("topicAnswers", topicAnswers)
            }
            startActivity(intent)
        }
    }

    private fun getTopicQuestions(): Array<String> {
        when (selectedTopicId){
            0 -> return resources.getStringArray(R.array.mathQuestions)
            1 -> return resources.getStringArray(R.array.physicsQuestions)
            2 -> return resources.getStringArray(R.array.marvelQuestions)
            else ->
                throw IllegalArgumentException()
        }
    }

    private fun getTopicOptions(): Array<String> {
        when (selectedTopicId){
            0 -> return resources.getStringArray(R.array.mathOptions)
            1 -> return resources.getStringArray(R.array.physicsOptions)
            2 -> return resources.getStringArray(R.array.marvelOptions)
            else ->
                throw IllegalArgumentException()
        }
    }

    private fun getTopicAnswers(): Array<String> {
        when (selectedTopicId){
            0 -> return resources.getStringArray(R.array.mathAnswers)
            1 -> return resources.getStringArray(R.array.physicsAnswers)
            2 -> return resources.getStringArray(R.array.marvelAnswers)
            else ->
                throw IllegalArgumentException()
        }
    }

}
