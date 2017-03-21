package cn.jestar.statepagedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jestar on 17-3-17.
 */

class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {
    private List<String> mList;

    public void setNew(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void loadMore(List<String> list) {
        if (mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public TextViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }

        public void setText(CharSequence text) {
            mTextView.setText(text);
        }
    }
}
