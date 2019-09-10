package com.fcamacho.simpleloginparse.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fcamacho.simpleloginparse.R
import com.google.android.material.card.MaterialCardView
import com.parse.ParseFile
import com.parse.ParseObject

class AdapterCourses(private val coursesList: ArrayList<ParseObject>) :
    RecyclerView.Adapter<CoursesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent,false)
        return CoursesHolder(view)
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: CoursesHolder, position: Int) {
        holder.bind(coursesList[position])
    }
}

class CoursesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var courseCard: MaterialCardView = itemView.findViewById(R.id.mcv_main_course_element)
    var courseTitle: TextView = itemView.findViewById(R.id.tv_main_course_title)
    var coursePicture: ImageView = itemView.findViewById(R.id.iv_main_course_image)

    init {
        courseCard.setOnClickListener {
            Toast.makeText(
                it.context,
                "Curso seleccionado: ${courseTitle.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun bind(obj: ParseObject) {
        val img = obj["image"] as ParseFile
        courseTitle.text = obj["name"].toString()
        Glide.with(itemView)
            .load(Uri.parse(img.url ?: ""))
            .into(coursePicture)
    }
}