package quizdroid.connorha.washington.edu.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application(), TopicRepository {

    companion object {
        lateinit var instance : QuizApp
            private set

        lateinit var topics : List<Topic>
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "onCreate has fired - QuizApp being loaded and run")
        instance = this

        val mathQuestion1 = Quiz("What is 2 + 2?", arrayOf("1", "2", "3", "4"), 3)
        val mathQuestion2 = Quiz("What is 2 x 2?",
                arrayOf("1", "2", "3", "4"), 3)
        val mathQuestion3 = Quiz("What is 2 / 2?",
                arrayOf("1", "2", "3", "4"), 0)

        val physicsQuestion1 = Quiz("What is the acceleration of gravity on Earth?",
                arrayOf("9.6 ft/s^2", "9.6 m/s^2", "9.8 ft/s^2", "9.8 m/s^2"), 3)
        val physicsQuestion2 = Quiz("What is an object that orbits the Earth?",
                arrayOf("The Sun", "Space X-Ship", "The Moon", "International Space Pod"),
                2)
        val physicsQuestion3 = Quiz("What causes the ocean to have tides?",
                arrayOf("Melting Glaciers", "Gravity", "Water Sleeps", "Nukes from WW2"), 1)

        val marvelQuestion1 = Quiz("When was the first Avengers movie released to theaters?",
                arrayOf("2010", "2011", "2012", "2013"), 2)
        val marvelQuestion2 = Quiz("Who was not in the first Avengers movie?",
                arrayOf("Nick Fury", "Thor", "Loki", "Spider-Man"), 3)
        val marvelQuestion3 = Quiz("Who is Thors brother?",
                arrayOf("Nick Fury", "Thor", "Loki", "Spider-Man"), 2)

        val mathQuiz = listOf(mathQuestion1, mathQuestion2, mathQuestion3)
        val physicsQuiz = listOf(physicsQuestion1, physicsQuestion2, physicsQuestion3)
        val marvelQuiz = listOf(marvelQuestion1, marvelQuestion2, marvelQuestion3)

        val mathTopic = Topic("Math", "Numbers and Algorithms", "Mathematics is the study of such topics as quantity, structure, space, and change. There are many views among mathematicians and philosophers as to the exact scope and definition of mathematics.", mathQuiz)
        val physicsTopic = Topic("Physics", "Math and Science", "A branch of knowledge or study dealing with a body of facts or truths systematically arranged and showing the operation of general laws.", physicsQuiz)
        val marvelTopic = Topic("Marvel Super Heroes", "Avengers and Others", "Earths Mightiest Heroes joined forces to take on threats that were too big for any one hero to tackle. With a roster that has included Captain America, Iron Man, Ant-Man, Hulk, Thor, Wasp and dozens more over the years, the Avengers have come to be regarded as Earths No. 1 team.", marvelQuiz)

        topics = listOf(mathTopic, physicsTopic, marvelTopic)
    }

    override fun getTopics() : List<Topic> {
        return topics
    }

    override fun getTopic(num : Int) : Topic {
        return topics[num]
    }

}

data class Quiz(val question : String, val options : Array<String>, val answerIndex : Int)
data class Topic(val title : String, val shortDescription : String, val longDescription : String,
                 val questions : List<Quiz>)

interface TopicRepository {
    fun getTopics() : List<Topic>
    fun getTopic(num : Int) : Topic
}
