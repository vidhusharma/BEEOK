package com.unleashed.android.beeokunleashed.ui.customgrid;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.unleashed.android.beeokunleashed.R;

/**
 * Created by sudhanshu on 21/08/15.
 */


public class CustomGridAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] MenuItemsString;
    private final TypedArray Imageid;

    private Holder holder;

    public static class Holder{
        TextView grid_item_text;
        ImageView grid_item_image;
    }

    public CustomGridAdapter(Context c,String[] menuItemsString,TypedArray Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.MenuItemsString = menuItemsString;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return MenuItemsString.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            //grid = new View(mContext);
            convertView = inflater.inflate(R.layout.grid_item_single, null);

            holder = new Holder();


            holder.grid_item_text = (TextView) convertView.findViewById(R.id.tv_grid_item_text);
            holder.grid_item_image = (ImageView)convertView.findViewById(R.id.imgView_grid_item_image);

            holder.grid_item_text.setText(MenuItemsString[position]);
            holder.grid_item_image.setImageResource(Imageid.getResourceId(position, -1));

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();

            holder.grid_item_text.setText(MenuItemsString[position]);
            holder.grid_item_image.setImageResource(Imageid.getResourceId(position, -1));

        }

        return convertView;
    }



}