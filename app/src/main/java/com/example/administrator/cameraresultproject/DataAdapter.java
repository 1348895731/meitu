package com.example.administrator.cameraresultproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Dajavu on 25/10/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private int[] images = {R.drawable.cicle, R.drawable.cicle, R.drawable.cicle,
            R.drawable.cicle, R.drawable.cicle, R.drawable.cicle, R.drawable.cicle,
            R.drawable.cicle, R.drawable.cicle, R.drawable.cicle};
//    private int[] images = {R.drawable.cicle, R.drawable.cicle, R.drawable.cicle};
    private boolean isLinearlayout;
    private boolean isFirst;
    private Context mContext;
    public DataAdapter(Onclick onclick){
        this.onclick=onclick;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.imageView.setTag(position);
//        if (isFirst) {
//            ViewGroup.LayoutParams layoutParams= holder.mLlroot.getLayoutParams();
//            if (isLinearlayout) {
//                Log.d("isLinearlayout","xian++++++++++");
//                layoutParams.width=Util.Dp2px(mContext,50);
//                layoutParams.height=Util.Dp2px(mContext,50);
//            }else{
//                Log.d("isLinearlayout","yuan++++++++++");
//                layoutParams.width=Util.Dp2px(mContext,100);
//                layoutParams.height=Util.Dp2px(mContext,100);
//            }
//            holder.mLlroot.setLayoutParams(layoutParams);
//            isFirst=false;
//        }
        onclick.SetOnclick(holder.imageView);

    }
    interface Onclick{
        public void SetOnclick(ImageView imageView);
    }
    private Onclick onclick;
    public void setOnclick(Onclick onclick){
        this.onclick=onclick;
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout mLlroot;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            mLlroot = itemView.findViewById(R.id.ll_root);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "clicked:" + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void changeLinearLayoutSize(boolean isLinearlayout,boolean isFirst){
        this.isLinearlayout=isLinearlayout;
        this.isFirst=isFirst;
        notifyDataSetChanged();
    }
}
