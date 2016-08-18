package com.example.kwangitti.smilethailand;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kwangitti on 8/16/16 AD.
 */
public class NewsItemAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private String[] date, title, des;
    private int[] img;

    public NewsItemAdapter(Context context, String[] dateNews, String[] titleNews, String[] desNews, int[] imgNews) {

        date = dateNews;
        title = titleNews;
        des = desNews;
        img = imgNews;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView textDate, textTitle, textDes;
        Button btnReadMore;
        ImageView imgNews;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.news_item_adapter, null);
        holder.imgNews = (ImageView) rowView.findViewById(R.id.img_news);
        holder.textDate = (TextView) rowView.findViewById(R.id.text_date_news);
        holder.textTitle = (TextView) rowView.findViewById(R.id.text_title_news);
        holder.textDes = (TextView) rowView.findViewById(R.id.text_des_news);
        holder.btnReadMore = (Button) rowView.findViewById(R.id.btn_read_more);

        holder.imgNews.setImageResource(img[position]);
        holder.textDate.setText(date[position]);
        holder.textTitle.setText(title[position]);
        holder.textDes.setText(des[position]);

        return rowView;
    }
}
