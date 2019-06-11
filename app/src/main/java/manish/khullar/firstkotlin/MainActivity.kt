package manish.khullar.firstkotlin

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(),SensorEventListener{


    /*override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


    var sensor: Sensor? = null
    var sensorManager: SensorManager? = null


    var isRunning = false
    var mp: MediaPlayer? = null

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] > 30 && isRunning == false) {
            isRunning = true
            try {
                mp = MediaPlayer()
                mp!!.setDataSource("https://jatt.download/music/data/English/201802/Best_of_August_2013/128/Do_I_Wanna_Know.mp3")
                mp!!.prepare()
                mp!!.start()
            } catch (ex: Exception) {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)


    }

}