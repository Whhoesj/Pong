package nl.sjtek.smartmobile.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_singleplayer:
                Intent intentSingleplayer = new Intent(this, ActivityGame.class);
                startActivity(intentSingleplayer);
                return true;
            case R.id.action_multiplayer_host:
                //TODO host multiplayer game
                Intent intentHost = new Intent(this, ActivityHost.class);
                startActivity(intentHost);
                return true;
            case R.id.action_multiplayer_join:
                //TODO join multiplayer game
                Intent intentJoin = new Intent(this, ActivityJoin.class);
                startActivity(intentJoin);
                return true;
            case R.id.action_exit:
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
}
