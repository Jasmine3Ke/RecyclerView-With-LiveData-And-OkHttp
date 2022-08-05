/** 制定recycler view的adapter來handle item view呈現的內容 */
package com.example.recyclerview_with_livedata_okhttp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Post> data;
    public CustomAdapter(){ this.data = null; }

    public List<Post> getData() {
        return data;
    }

    public void setData(List<Post> data) {
        this.data = data;
    }

    /** 這邊的ViewHolder繼承RecyclerView裡的ViewHolder class，初始化list component */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvSymbol;
        private TextView tvPriceChange;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.tvSymbol = view.findViewById(R.id.textview);
            this.tvPriceChange = view.findViewById(R.id.textview2);
        }

        @Override
        public void onClick(View view) {
            System.out.println("!!!!!!!!");
        }
    }

    /** 在onCreateViewHolder中inflate layout */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(rowItem);
    }

    /** 實作binding data，將list中的每筆資料放進cell item */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post dataPosition = this.getData().get(position);
        holder.tvSymbol.setText(dataPosition.getSymbol());
        holder.tvPriceChange.setText("\n" + dataPosition.getPriceChange());
    }

    /** Recycler view依據getItemCount()決定要顯示幾筆資料 */
    @Override
    public int getItemCount() {
        if (this.getData() == null){
            return 0;
        }
        return this.getData().size();
    }
}
