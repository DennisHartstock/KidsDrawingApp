package com.example.kidsdrawingapp

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private lateinit var drawingView: DrawingView
    private lateinit var llColors: LinearLayout
    private lateinit var ibCurrentColor: ImageButton
    private lateinit var ibBrush: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        ibCurrentColor = llColors[0] as ImageButton
        ibCurrentColor.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.color_selected))
        drawingView.setNewBrushSize(10.toFloat())
        ibBrush.setOnClickListener { showBrushSizeChooserDialog() }
    }

    private fun initViews() {
        drawingView = findViewById(R.id.vDrawing)
        llColors = findViewById(R.id.llColors)
        ibBrush = findViewById(R.id.ibBrush)
    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Choose brush size")

        val ibSmall = brushDialog.findViewById<ImageButton>(R.id.ibSmall)
        ibSmall.setOnClickListener {
            drawingView.setNewBrushSize(10.toFloat())
            brushDialog.dismiss()
        }
        val ibMedium = brushDialog.findViewById<ImageButton>(R.id.ibMedium)
        ibMedium.setOnClickListener {
            drawingView.setNewBrushSize(20.toFloat())
            brushDialog.dismiss()
        }
        val ibLarge = brushDialog.findViewById<ImageButton>(R.id.ibLarge)
        ibLarge.setOnClickListener {
            drawingView.setNewBrushSize(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }
}