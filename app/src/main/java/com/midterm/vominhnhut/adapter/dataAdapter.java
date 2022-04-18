package com.midterm.vominhnhut.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.vominhnhut.DB.AppDatabase;
import com.midterm.vominhnhut.DB.DataDao;
import com.midterm.vominhnhut.R;
import com.midterm.vominhnhut.model.DataDB;
import com.midterm.vominhnhut.otherActivites.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.ViewHolder> implements Filterable {

    private List<DataDB> data;
    private List<DataDB> dataOld;
    private Context context;

    private AppDatabase appDatabase;
    private DataDao dataDao;

    public dataAdapter(List<DataDB> data, Context context){
        this.data = data;
        this.dataOld = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataDB d = data.get(position);
        if (d == null){
            return;
        }

        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvDesc.setText(data.get(position).getDesc());
        holder.tvTimestamp.setText(data.get(position).getTimestamp());
        holder.setItemClickListener((view, position1, isLongClick) -> {
            if (!isLongClick){
                Intent intent= new Intent(context, DetailActivity.class);
                intent.putExtra("title", data.get(position1).getTitle());
                intent.putExtra("desc", data.get(position1).getDesc());
                intent.putExtra("timestamp", data.get(position1).getTimestamp());
                intent.putExtra("lat", data.get(position1).getLat());
                intent.putExtra("lng", data.get(position1).getLng());
                intent.putExtra("addr", data.get(position1).getAddr());
                intent.putExtra("e", data.get(position1).getE());
                intent.putExtra("zip", data.get(position1).getZip());
                ((Activity) context).startActivity(intent);
            }
            if (isLongClick){
//                DataApi dataApi = data.get(position1);
//                DataDB data1 = new DataDB(dataApi.getTitle(), dataApi.getDesc(), dataApi.getTimeStamp(), dataApi.getLat(), dataApi.getLng(), dataApi.getAddr(), dataApi.getE(), dataApi.getZip());
//                data.remove(position1);
//                MainActivity.isRemove = true;
//                notifyDataSetChanged();
                Intent intent = new Intent("position");
                intent.putExtra("pos", Integer.toString(position1));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return 0;
    }

    public void setData(List<DataDB> dataApiList) {
        this.data = dataApiList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        public TextView tvTitle, tvDesc, tvTimestamp;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvTimestamp = itemView.findViewById(R.id.tv_ts);
            appDatabase = AppDatabase.getInstance(context);
            dataDao = appDatabase.dataDao();

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getBindingAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getBindingAdapterPosition(),true);
            return false;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                dataOld = dataDao.getAllData();
                if (strSearch.isEmpty()){
                    data = dataOld;
                } else {
                    List<DataDB> list = new ArrayList<>();
                    for (DataDB dog : dataOld ){
                        if (dog.getTitle().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(dog);
                        }
                    }

                    data = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = data;

                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (List<DataDB>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
