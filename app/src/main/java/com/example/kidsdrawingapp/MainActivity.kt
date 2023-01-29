package com.example.kidsdrawingapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private lateinit var dvCanvas: DrawingView
    private lateinit var llColors: LinearLayout
    private lateinit var ibCurrentColor: ImageButton
    private lateinit var ibBrush: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        ibCurrentColor = llColors[0] as ImageButton
        ibCurrentColor.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.color_selected))
        dvCanvas.setNewBrushSize(10.toFloat())
        ibBrush.setOnClickListener { showBrushSizeChooserDialog() }
    }

    fun onColorClick(view: View) {
        if (view != ibCurrentColor) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            dvCanvas.setNewColor(colorTag)
            imageButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.color_selected))
            ibCurrentColor.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.color_normal
                )
            )
            ibCurrentColor = view
        }
    }

    private fun initViews() {
        dvCanvas = findViewById(R.id.dvCanvas)
        llColors = findViewById(R.id.llColors)
        ibBrush = findViewById(R.id.ibBrush)
    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Choose brush size")

        val ibUltraSmall = brushDialog.findViewById<ImageButton>(R.id.ibUltraSmall)
        ibUltraSmall.setOnClickListener {
            dvCanvas.setNewBrushSize(2.toFloat())
            brushDialog.dismiss()
        }

        val ibSmall = brushDialog.findViewById<ImageButton>(R.id.ibSmall)
        ibSmall.setOnClickListener {
            dvCanvas.setNewBrushSize(10.toFloat())
            brushDialog.dismiss()
        }
        val ibMedium = brushDialog.findViewById<ImageButton>(R.id.ibMedium)
        ibMedium.setOnClickListener {
            dvCanvas.setNewBrushSize(20.toFloat())
            brushDialog.dismiss()
        }
        val ibLarge = brushDialog.findViewById<ImageButton>(R.id.ibLarge)
        ibLarge.setOnClickListener {
            dvCanvas.setNewBrushSize(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }
}