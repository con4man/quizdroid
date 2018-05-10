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
        val topicTitle = QuizApp.instance.getTopic(selectedTopicId).title
        supportActionBar?.title = topicTitle
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
        return instance
    }

}
