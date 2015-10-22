package rageagainstmachinelearning.queuebeacon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eyro.cubeacon.CBBeacon;

import java.util.ArrayList;

/**
 * Created by nao on 10/8/15.
 */
public class BeaconListAdapter extends ArrayAdapter<CBBeacon> {
    Context context;
    int layoutResourceId;
    ArrayList<CBBeacon> BeaconList;
    public BeaconListAdapter(Context context,int Resource, int ResourceTextId,ArrayList<CBBeacon> data) {
        super(context, Resource,ResourceTextId, data);
        this.layoutResourceId = Resource;
        this.context = context;
        this.BeaconList = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BeaconHolder holder = null;
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BeaconHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.icon);
            holder.FirstLine = (TextView)row.findViewById(R.id.firstLine);
            holder.SecondLine = (TextView) row.findViewById(R.id.secondLine);
            row.setTag(holder);
        }
        else {
            holder = (BeaconHolder)row.getTag();
        }

        CBBeacon beacon = BeaconList.get(position);
        holder.FirstLine.setText(beacon.getName()+" , "+beacon.getStoryline().getStorylineTitle());
        holder.SecondLine.setText(beacon.getStoryline().getEvent().toString());
        return row;
    }

    static class BeaconHolder {
        ImageView imgIcon;
        TextView FirstLine;
        TextView SecondLine;
    }
}
