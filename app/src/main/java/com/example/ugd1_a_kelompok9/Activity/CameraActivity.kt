package com.example.ugd1_a_kelompok9.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.hardware.Camera
import android.media.Image
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import com.example.ugd1_a_kelompok9.CameraView
import java.lang.Exception
import com.example.ugd1_a_kelompok9.R

class CameraActivity : AppCompatActivity() {
    private var mCamera: Camera? = null
    private var mCameraView: CameraView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        try {
            mCamera = Camera.open()
        } catch (e: Exception) {
            Log.d("Error", "Failed to get Camera" + e.message)
        }
        if(mCamera !=null){
            mCameraView = CameraView(this, mCamera!!)
            val camera_view = findViewById<View>(R.id.FLCamera) as FrameLayout
            camera_view.addView(mCameraView) //  Camera
        }
        @SuppressLint("MissingInflatedId", "LocalSuppress") val imageClose =
            findViewById<View>(R.id.imgClose) as ImageButton
        imageClose.setOnClickListener{ view: View? -> System.exit(0)}
    }
}