package quizdroid.connorha.washington.edu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbarMenu))
        createTopicsList()
        createTopicListOnClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.preferences, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, PreferencesActivity::class.java)
        startActivity(intent)
        return true
    }

    private fun createTopicsList() {
        var listView = findViewById<ListView>(R.id.listViewTopics)
        val topics = QuizApp.instance.getTopics()
        val adapter = TopicAdapter(this, topics)
        listView.adapter = adapter
    }

    private fun createTopicListOnClickListeners() {
        listViewTopics.setOnItemClickListener({ _, _, _, id ->
            val intent = Intent(this, QuizTopicActivity::class.java).apply {
                putExtra("selectedTopicId", id.toInt() )
            }
            startActivity(intent)
        })
    }

}
