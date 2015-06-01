package com.example.teatrulcaracalean;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Context context;
	private List<String> listaParinti;
	private HashMap<String, List<String>> listaCopii;
	
	public ExpandableListAdapter(Context context, List<String> listaParinti
			, HashMap<String, List<String>> listaCopii){
		this.context = context;
		this.listaParinti = listaParinti;
		this.listaCopii = listaCopii;
	}
	
	
	@Override
	public int getGroupCount() {
		return this.listaParinti.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.listaCopii.get(this.listaParinti.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listaParinti.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.listaCopii.get(this.listaParinti.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String titlu = (String) getGroup(groupPosition);
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.grouplist, null);
		}
		TextView lblCapLista = (TextView) convertView.findViewById(R.id.lblCapLista);
		lblCapLista.setTypeface( null, Typeface.BOLD);
		lblCapLista.setText(titlu);
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String textCopil = (String)getChild(groupPosition, childPosition);
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.listitem,null);
		}
		TextView textListaCopil = (TextView) convertView.findViewById(R.id.lblListItem);
		textListaCopil.setText(textCopil);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
