package rageagainstmachinelearning.queuebeacon;

import android.app.Application;
import android.util.Log;

import com.eyro.cubeacon.CBApp;

/**
 * Created by nao on 9/16/15.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Application", "Init Cubeacon APP");
        // Cubeacon App Initialization
        CBApp.setup(this, "Ci9EOiAF","F7lEEBCqqDUnmI0i","e8ff293e","336f7ecfd5826f013576255992369ead","US",
                true,true,true,true,true,"You have #event in a Beacon : #beacon_name","Found Beacon");
        // Configure verbose debug logging.
        CBApp.enableDebugLogging(true);
        // Configure download image brochure.
        CBApp.enableDownloadImage(true);
    }
}
