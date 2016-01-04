package com.exmple.www.finalmycollage;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustumExpndaple extends BaseExpandableListAdapter {
    private Context _context;
    private HashMap<String, List<String>> _listDataChild;
    private List<parsdata> _listDataHeader;

    public CustumExpndaple(Context context, List<parsdata> listDataHeader, HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    public Object getChild(int groupPosition, int childPosititon) {
        return ((List) this._listDataChild.get(((parsdata) this._listDataHeader.get(groupPosition)).get_id())).get(childPosititon);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return (long) childPosition;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_chiled_ex, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.textView_chile);
        txtListChild.setTypeface(Typeface.createFromAsset(this._context.getAssets(), "al-qassam-extended.ttf"));
        txtListChild.setText(childText);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return ((List) this._listDataChild.get(((parsdata) this._listDataHeader.get(groupPosition)).get_id())).size();
    }

    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    public long getGroupId(int groupPosition) {
        return (long) groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = ((parsdata) getGroup(groupPosition)).getNameTask();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_group, null);        }
        String chr = BuildConfig.FLAVOR;
        String time = ((parsdata) this._listDataHeader.get(groupPosition)).getTimeTask();
        for (int i = 0; i < 2; i++) {
            chr = chr + String.valueOf(time.charAt(i));
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.name_task_ex);
        TextView lblListHeaderhell = (TextView) convertView.findViewById(R.id.textView_hell_ex);
        TextView lblListHeadertime = (TextView) convertView.findViewById(R.id.textView_time_ex);
        Typeface typeface = Typeface.createFromAsset(this._context.getAssets(), "al-qassam-extended.ttf");
        lblListHeader.setTypeface(typeface);
        lblListHeaderhell.setTypeface(typeface);
        lblListHeadertime.setTypeface(typeface);
        lblListHeadertime.setText(chr);
        lblListHeaderhell.setText(((parsdata) this._listDataHeader.get(groupPosition)).getNameAPleca());
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
