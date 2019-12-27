package com.imuons.shopntrips.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.ExpandableListModel;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<ExpandableListModel> mExpandableListTitle;

    private HashMap<String, ArrayList<String>> mExpandableListDetail;
    private LayoutInflater mLayoutInflater;

    public CustomExpandableListAdapter(Context context, ArrayList<ExpandableListModel> expandableListTitle,
                                       HashMap<String, ArrayList<String>> expandableListDetail) {
        this.mContext = context;
        this.mExpandableListTitle = expandableListTitle;

        this.mExpandableListDetail = expandableListDetail;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition).title)
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition).title)
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return mExpandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return mExpandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        ExpandableListModel headerTitle = (ExpandableListModel) getGroup(listPosition);
//        String listTitle = (String) getGroup(listPosition);

//        if (isExpanded) {
//            headerTitle.img.setImageResource(R.drawable.group_down);
//        } else {
//            groupHolder.img.setImageResource(R.drawable.group_up);
//        }
//        Integer listIcon = (Integer) getGroup(listPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        listTitleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        listTitleTextView.setText(headerTitle.title);

        ImageView listImageView = (ImageView) convertView
                .findViewById(R.id.listIcon);

    listImageView.setImageResource(headerTitle.image);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
