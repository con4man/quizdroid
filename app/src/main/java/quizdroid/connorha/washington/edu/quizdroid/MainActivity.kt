package quizdroid.connorha.washington.edu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //var topics = resources.getStringArray(R.array.topics)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createTopicsList()
        createTopicListOnClickListeners()
    }

    private fun createTopicsList() {
        val topics = resources.getStringArray(R.array.topics)
        val topicsListItem = android.R.layout.simple_list_item_1
        val topicsListItemName = android.R.id.text1
        val adapter = ArrayAdapter<String>( this,
        topicsListItem, topicsListItemName, topics)
        listViewTopics.adapter = adapter

    }

    private fun createTopicListOnClickListeners() {
        listViewTopics.setOnItemClickListener({ _, _, _, id ->
            val intent = Intent(this, TopicOverviewActivity::class.java).apply {
                putExtra("selectedTopicId", id.toInt() )
            }
            startActivity(intent)
        })
    }

}
