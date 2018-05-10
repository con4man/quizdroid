package quizdroid.connorha.washington.edu.quizdroid

import android.app.Application

class QuizApp : Application() {

    companion object {
        lateinit var instance: JSONParser
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = JSONParser()
    }

}

data class QuizQuestion(val question : String, val options : Array<String>, val answerIndex : Int)
data class Topic(val title : String, val shortDescription : String, val longDescription : String,
                 val questions : List<QuizQuestion>)

interface TopicRepository {
    fun getTopics() : List<Topic>
    fun getTopic(index : Int) : Topic
}
