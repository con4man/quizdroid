package quizdroid.connorha.washington.edu.quizdroid

import android.os.AsyncTask
import org.json.JSONArray
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class JSONParser : TopicRepository, AsyncTask<String, String, String>() {
    private val topics = ArrayList<Topic>()
    var url = ""

    init {
        url = "http://tednewardsandbox.site44.com/questions.json"
        execute(url).get()
    }

    override fun getTopics(): List<Topic> {
        return topics
    }

    override fun getTopic(index: Int): Topic {
        return topics[index]
    }

    override fun doInBackground(vararg params: String?): String {
        val connect = URL(url).openConnection() as HttpURLConnection
        var input = ""

        try {
            input = BufferedInputStream(connect.inputStream).use {
                it.reader().use {
                    reader -> reader.readText()
                }
            }
        } finally {
            connect.disconnect()
        }

        val json = JSONArray(input)

        for ( i in 0..(json.length() - 1)) {
            val topic = json.getJSONObject(i)
            val title = topic.getString("title")
            val description = topic.getString("desc")
            val questions = topic.getJSONArray("questions")
            val topicQuiz = ArrayList<QuizQuestion>()

            for ( j in 0..(questions.length() - 1)) {
                val quizQuestion = questions.getJSONObject(j)
                val question = quizQuestion.getString("text")
                val answerIndex = quizQuestion.getInt("answer") - 1
                val optionsList = quizQuestion.getJSONArray("answers")
                val options = Array(optionsList.length()) { "" }

                for (k in 0..(optionsList.length() - 1)) {
                    options[k] = optionsList[k].toString()
                }
                topicQuiz.add(QuizQuestion(question, options, answerIndex))
            }
            topics.add(Topic(title, description, description, topicQuiz))
        }
        return input
    }
}