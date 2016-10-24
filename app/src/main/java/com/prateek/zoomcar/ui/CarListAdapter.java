package com.prateek.zoomcar.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.prateek.zoomcar.R;
import com.prateek.zoomcar.rest.ListCarsResponseDto;
import com.squareup.picasso.Picasso;

import java.util.Collections;

import butterknife.ButterKnife;

/**
 * Created by prateek.kesarwani on 9/13/2015.
 */
public class CarListAdapter extends BaseAdapter {

    private ListCarsResponseDto mListCarsResponseDto;
    private Context mContext;

    public CarListAdapter(Context context, ListCarsResponseDto listCarsResponseDto) {
        this.mListCarsResponseDto = listCarsResponseDto;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mListCarsResponseDto.getCars().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View finalView = (View) inflater.inflate(R.layout.car_list_item, null);

        TextView carNameTxt = ButterKnife.findById(finalView, R.id.car_item_name_txt);
        carNameTxt.setText(mListCarsResponseDto.getCars().get(position).getName());

        RatingBar carRatingBar = ButterKnife.findById(finalView, R.id.car_item_rating);
        String carRating = mListCarsResponseDto.getCars().get(position).getRating();
        if (carRating != null) {
            carRatingBar.setRating(Float.parseFloat(carRating));
        }

        TextView carPriceTxt = ButterKnife.findById(finalView, R.id.car_item_price_txt);
        carPriceTxt.setText(mListCarsResponseDto.getCars().get(position).getHourly_rate());


        //Initialize ImageView
        ImageView imageView = ButterKnife.findById(finalView, R.id.car_item_img);

        String image = mListCarsResponseDto.getCars().get(position).getImage();

        Picasso.with(mContext)
                .load(mListCarsResponseDto.getCars().get(position).getImage())
                .into(imageView);

        return finalView;
    }

    public void sortByPrice() {
        Collections.sort(mListCarsResponseDto.getCars(), new ListCarsResponseDto.SortByPrice());
        this.notifyDataSetChanged();
    }

    public void sortByRating() {
        Collections.sort(mListCarsResponseDto.getCars(), new ListCarsResponseDto.SortByRating());
        this.notifyDataSetChanged();
    }
}