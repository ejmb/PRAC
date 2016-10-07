package py.com.ejmb.prac.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import py.com.ejmb.prac.R;

/**
 * Created by Eduardo on 5/10/2016.
 */

public class AdaptadorListView extends BaseAdapter {

    private final ArrayList mData;

    public AdaptadorListView(Map<String, Integer> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, Integer> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO implement you own logic with ID
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_curso, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, Integer> item = getItem(position);

        // TODO replace findViewById by ViewHolder
        ((TextView) result.findViewById(R.id.textViewTituloCurso)).setText(item.getKey());
        ((TextView) result.findViewById(R.id.textViewHoras)).setText(String.valueOf(item.getValue()));

        return result;
    }


}
