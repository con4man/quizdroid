package quizdroid.connorha.washington.edu.quizdroid

import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView


internal class TopicAdapter(private val context: Context, private val dataSource: List<Topic>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_topic, parent, false)

            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(R.id.topic_list_thumbnail) as ImageView
            holder.titleTextView = view.findViewById(R.id.topic_list_title) as TextView
            holder.subtitleTextView = view.findViewById(R.id.topic_list_subtitle) as TextView
            holder.detailTextView = view.findViewById(R.id.topic_list_detail) as TextView

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView
        val topic = getItem(position) as Topic

        titleTextView.text = topic.title
        subtitleTextView.text = topic.shortDescription
        detailTextView.text = ""
        thumbnailImageView.setImageResource(R.drawable.android)
        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var thumbnailImageView: ImageView
    }
}