package com.i015213.emas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.i015213.emas.Models.Car;
import com.i015213.emas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IVONNE on 10/06/2017.
 */

public class FavoritesCarAdapter extends BaseAdapter {

    List<Car> userList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public FavoritesCarAdapter(Context context, List<Car> userList) {
        this.context = context;
        this.userList = userList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return userList.size();
    }

    @Override
    public Car getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_favorite, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Car car = getItem(position);
        viewHolder.route.setText(car.getRoute());
        viewHolder.neighborhood.setText(car.getNeighborhood());

        return convertView;
    }

    public class ViewHolder{
        TextView route;
        TextView neighborhood;

        public ViewHolder(View item) {
            route = (TextView) item.findViewById(R.id.id_item_favorite_route);
            neighborhood = (TextView) item.findViewById(R.id.id_item_favorite_neighborhood);
        }
    }
}
