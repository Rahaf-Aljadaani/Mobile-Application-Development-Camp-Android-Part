package com.example.youtubeapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer


class itemAdapter(val items: ArrayList<viedoInfo>, val player: YouTubePlayer) :
    RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var button: Button

        init {

            button = itemView.findViewById(R.id.button);
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.youtube_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: itemAdapter.ViewHolder, position: Int) {
        holder.button.text = items[position].name
        holder.button.setOnClickListener {
            player.loadVideo(items[position].viedo_URL, 0f)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}