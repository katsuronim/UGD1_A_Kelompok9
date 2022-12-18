package com.example.ugd1_a_kelompok9.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ugd1_a_kelompok9.R
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.shashank.sony.fancytoastlib.FancyToast

class ShakeKuponActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var square: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shake_kupon)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        square = findViewById(R.id.tv_square)

        setUpSensorStuff()
    }

    fun setUpSensorStuff(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
                accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val sides = event.values[0]
            val upDown = event.values[1]

            square.apply {
                rotationX = upDown * 3f
                rotationY = sides * 3f
                rotation = -sides
                translationX = sides * -10
                translationY = upDown * -10
            }

            val color = if (upDown.toInt() == 0 && sides.toInt() ==0)
                Color.GREEN else Color.RED
            square.setBackgroundColor(color)
            square.text = "Ayo goyangkan hingga untuk mendapatkan kupon diskon 50%!"
            if(color == Color.GREEN){
                FancyToast.makeText(applicationContext,"Maaf Anda Kurang Beruntung",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }

}
