package quizdroid.connorha.washington.edu.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class QuizTopicActivity : AppCompatActivity() {

    private var selectedTopicId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_topic)
        getIntentData()
        setNavBarTitle()
        showTopicOverViewFragment()
    }

    private fun getIntentData() {
        selectedTopicId = intent.getIntExtra("selectedTopicId",-1)
    }

    private fun setNavBarTitle() {
        val topics = resources.getStringArray(R.array.topics)
        val topicName = topics[selectedTopicId]
        supportActionBar?.title = topicName
    }

    private fun showTopicOverViewFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val instance = createInstance()
        val topicOverviewFragment = TopicOverviewFragment()
        topicOverviewFragment.arguments = instance
        fragmentTransaction.add(R.id.frameFragment,topicOverviewFragment).commit()
    }

    private fun createInstance(): Bundle {
        val instance = Bundle()
        instance.putInt("topicId", selectedTopicId)
        instance.putStringArray("topicQuestions", getTopicQuestions())
        instance.putStringArray("topicOptions", getTopicOptions())
        instance.putStringArray("topicAnswers", getTopicAnswers())
        return instance
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
