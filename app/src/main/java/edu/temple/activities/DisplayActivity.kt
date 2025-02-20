package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
const val TEXTSIZE = "Fontsize"
const val FONTSELECTED = 1000
class DisplayActivity : AppCompatActivity() {

    // TODO Step 1: Launch TextSizeActivity when button clicked to allow selection of text size value
    private var Launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result -> //this lambda is aka callback
        //this line above uses the Acitivity result launcher to register a launcher to start an activity that can receive a callback(a return)
        if (result.resultCode == FONTSELECTED) { //checks if the activity has launched
            val textSize = result.data?.getFloatExtra(TEXTSIZE, 16f) ?: 16f
            //this line retreives the intent that was sent back from text size activity and uses getfloat to extract float number
            //the ?: is a null safety
            lyricsDisplayTextView.textSize = textSize
            //sets the displayview in the original activity to the size
        }
    }


    // TODO Step 3: Use returned value for lyricsDisplayTextView text size

    //initializes var or val with types

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)//so we are on a current screen, where it is the text view display
        //the current activity has a button that calls another activity to show up, which has a spinner
        //the goal is to select a float from the spinner and have it update the textviewi n the original activity

        //set the ids to the vars
        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

//listens for a button click
        textSizeSelectorButton.setOnClickListener{
        //upon a click, a intent is made to the textsizeactivity class and is attached with the textsize of the displayview
                 val intent1 = Intent(this@DisplayActivity, TextSizeActivity::class.java).apply {
                        putExtra(TEXTSIZE, lyricsDisplayTextView.textSize)
                    }
            Launcher.launch(intent1) // the launcher launches activities and passes an intent

        }

    }
}