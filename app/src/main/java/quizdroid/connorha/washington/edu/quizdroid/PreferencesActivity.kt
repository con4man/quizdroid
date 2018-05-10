package quizdroid.connorha.washington.edu.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class PreferencesActivity : AppCompatActivity() {

    var refreshFrequencyOptions = arrayOf("1 Minute", "5 Minutes", "10 Minutes", "1 Hour")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        initializeSpinner()
        setRetrieveButtonOnClickedListener()
    }

    private fun initializeSpinner() {
        var spinnerRefreshFrequency = findViewById(R.id.spinnerRefreshFrequency) as Spinner
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, refreshFrequencyOptions)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRefreshFrequency.adapter = arrayAdapter
        spinnerRefreshFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    //for later implementation
//                    0 -> quizApp.setRefresh(1)
//                    1 -> quizApp.setRefresh(5)
//                    2 -> quizApp.setRefresh(10)
//                    3 -> quizApp.setRefresh(60)
                }
            }
        }
        //sets default refresh frequency to 10 minutes
        spinnerRefreshFrequency.setSelection(2)
    }

    private fun setRetrieveButtonOnClickedListener() {

    }
}
