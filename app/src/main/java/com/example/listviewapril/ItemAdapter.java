package com.example.listviewapril;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> itemList;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_list_item, parent, false);
            holder = new ViewHolder();

            holder.itemName = convertView.findViewById(R.id.itemName);
            holder.itemImage = convertView.findViewById(R.id.itemImageView);
            holder.editButton = convertView.findViewById(R.id.editButton);
            holder.deleteButton = convertView.findViewById(R.id.deleteButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item currentItem = getItem(position);

        holder.itemName.setText(currentItem.getName());
        if (currentItem.getImageUri() != null) {
            holder.itemImage.setImageURI(currentItem.getImageUri());
        }

        holder.editButton.setOnClickListener(v -> {
            if (context instanceof MainActivity) {
                ((MainActivity) context).showAddEditDialog(currentItem, position);
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            itemList.remove(position);
            notifyDataSetChanged();
            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView itemName;
        ImageView itemImage;
        Button editButton;
        Button deleteButton;
    }
}

