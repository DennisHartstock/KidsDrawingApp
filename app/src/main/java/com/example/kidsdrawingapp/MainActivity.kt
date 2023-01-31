package com.example.kidsdrawingapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private lateinit var dvCanvas: DrawingView
    private lateinit var llColors: LinearLayout
    private lateinit var ibCurrentColor: ImageButton
    private lateinit var ibBrush: ImageButton
    private lateinit var ibGallery: ImageButton
    private lateinit var ibUndo: ImageButton
    private lateinit var ivColoring: ImageView

    private val openGalleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                ivColoring.setImageURI(result.data?.data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        ibCurrentColor = llColors[0] as ImageButton
        ibCurrentColor.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.color_selected))
        dvCanvas.setNewBrushSize(10.toFloat())
        ibBrush.setOnClickListener { showBrushSizeChooserDialog() }
        ibGallery.setOnClickListener {
            val pickIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            openGalleryLauncher.launch(pickIntent)
        }
        ibUndo.setOnClickListener {
            dvCanvas.onUndoClick()
        }
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
        ibGallery = findViewById(R.id.ibGallery)
        ibUndo = findViewById(R.id.ibUndo)
        ivColoring = findViewById(R.id.ivColoring)
    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)

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