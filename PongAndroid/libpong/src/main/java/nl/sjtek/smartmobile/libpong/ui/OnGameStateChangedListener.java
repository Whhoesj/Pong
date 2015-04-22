package nl.sjtek.smartmobile.libpong.ui;

/**
 * Created by wouter on 21-4-15.
 */
public interface OnGameStateChangedListener {

    public void onGameChanged(State state);

    public enum State {
        Waiting,
        Connecting,
        Running,
        Stopping
    }
}