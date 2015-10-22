package rageagainstmachinelearning.queuebeacon;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.eyro.cubeacon.CBActivity;
import com.eyro.cubeacon.CBBeacon;
import com.eyro.cubeacon.CBConstant;
import com.eyro.cubeacon.CBStoryline;

import java.io.ByteArrayOutputStream;
import java.security.cert.CertPathBuilderSpi;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends CBActivity {
    ArrayList<CBBeacon> ListBeacon;
    ListView beaconlistView;
    ArrayAdapter<CBBeacon> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListBeacon = new ArrayList<CBBeacon>();
        beaconlistView = (ListView) findViewById(R.id.beacon_list_view);
        adapter = new BeaconListAdapter(this, R.layout.list_item_row,R.id.firstLine,  ListBeacon);
        // Assign adapter to ListView
        beaconlistView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /* Fungsi callback saat device keluar dari area suatu beacon
     */
    @Override
    protected void onExitedBeacon(CBBeacon beacon, long timeInterval) {
        Log.d("Application","Exited Beacon");
        checkBeacon(beacon);
    }
    /* Fungsi callback saat device masuk ke suatu area beacon
     */
    @Override
    protected void onEnteredBeacon(CBBeacon beacon) {
        Log.d("Application", "Entered Beacon");
        checkBeacon(beacon);
    }
    /* Fungsi callback saat device tidak mendeteksi beacon
     */
    @Override
    protected void onEmptyBeacon() {
        Log.d("Application","Empty Beacon");
        // TODO Auto-generated method stub
    }
    /* Fungsi callback saat device tidak mendeteksi beacon
    */
    @Override
    protected void onNearestBeaconChanged(CBBeacon old, CBBeacon current) {
        Log.d("Application", "Nearest Beacon");
        checkBeacon(current);

    }
    /* Fungsi callback saat device berada sangat dekat dengan beacon*/
    @Override
    protected void onImmediateBeacon(CBBeacon beacon) {
        Log.d("Application", "Immediate Beacon");
        checkBeacon(beacon);
    }
    /* Fungsi callback saat device berada dekat dengan beacon*/
    @Override
    protected void onNearBeacon(CBBeacon beacon) {
        Log.d("Application","Near Beacon");
        checkBeacon(beacon);
    }
    /* Fungsi callback saat device berada jauh dengan beacon*/
    @Override
    protected void onFarBeacon(CBBeacon beacon) {
        Log.d("Application","Far Beacon");
        checkBeacon(beacon);
    }
    /* Fungsi yang mengecek beacon mana saja yang pernah dideteksi*/
    private void checkBeacon(CBBeacon beacon){
        Log.d("ErrorBeacon","Logging "+beacon.getMajor());
        if (!ListBeacon.contains(beacon)){
            ListBeacon.add(beacon);
            adapter.notifyDataSetChanged();
        }
    }
}
