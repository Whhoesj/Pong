package nl.sjtek.smartmobile.pong;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;


public class ActivityOnlineGame extends Activity implements SensorEventListener {

    private OnlineGame onlineGame = new OnlineGame();
    private OnlineGameView onlineGameView;
    private SensorManager sensorManager;
    private Sensor sensor;
    //    private TextView textViewRaw, textViewMapped;
    private float valueSmooth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getCanonicalName(), "game start");
        setContentView(R.layout.activity_online_game);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        textViewRaw = (TextView) findViewById(R.id.textViewRaw);
//        textViewMapped = (TextView) findViewById(R.id.textViewMapped);
        onlineGameView = (OnlineGameView) findViewById(R.id.onlineGameView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        onlineGame.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_exit:
                // exit game
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            openOptionsMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public float power(final float base, final int power) {
        float result = 1;
        for (int i = 0; i < power; i++) {
            result *= base;
        }
        return result;
    }

    private float map(float x, float in_min, float in_max, float out_min, float out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    private float exponentialSmoothing(float input, float output, float alpha) {
        return (output + alpha * (input - output));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float valueMapped = map(sensorEvent.values[0], -2f, 2f, 565f, 0f);
        valueSmooth = exponentialSmoothing(valueMapped, valueSmooth, 0.1f);

        onlineGame.setMovementValue(valueSmooth);

//        textViewRaw.setText(String.valueOf(valueRaw));
//        textViewMapped.setText(String.valueOf(valueSmooth));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

}