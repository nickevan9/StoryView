package com.example.storyview

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.storyview.storyviewext.MyStory
import com.example.storyview.storyviewext.StoryClickListeners
import com.example.storyview.storyviewext.StoryView
import java.text.ParseException
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_show).setOnClickListener { showStories() }
    }

    fun showStories() {
        val myStories: ArrayList<MyStory> = ArrayList<MyStory>()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
        try {
            val story1 = MyStory(
                "https://media.pri.org/s3fs-public/styles/story_main/public/images/2019/09/092419-germany-climate.jpg?itok=P3FbPOp-",
                simpleDateFormat.parse("20-10-2019 10:00:00")
            )
            myStories.add(story1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        try {
            val story2 = MyStory(
                "http://i.imgur.com/0BfsmUd.jpg",
                simpleDateFormat.parse("26-10-2019 15:00:00"),
                "#TEAM_STANNIS"
            )
            myStories.add(story2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val story3 = MyStory(
            "https://mfiles.alphacoders.com/681/681242.jpg"
        )
        myStories.add(story3)
        StoryView.Builder(supportFragmentManager)
            .setStoriesList(myStories)
            .setStoryDuration(5000)
            .setTitleText("Hamza Al-Omari")
            .setSubtitleText("Damascus")
            .setStoryClickListeners(object : StoryClickListeners {
                override fun onDescriptionClickListener(position: Int) {
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked: " + myStories[position].description,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onTitleIconClickListener(position: Int) {}
            })
            .setOnStoryChangedCallback { position ->
                Toast.makeText(this@MainActivity, position.toString() + "", Toast.LENGTH_SHORT)
                    .show()
            }
            .setStartingIndex(0)
            .setRtl(false)
            .build()
            .show()
    }
}