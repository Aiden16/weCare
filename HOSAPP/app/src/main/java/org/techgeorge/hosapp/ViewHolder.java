package org.techgeorge.hosapp;



import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techgeorge.hosapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemlongClick(view, getAdapterPosition());
                return false;
            }
        });
    }
    public void setData(Context context,String name,String age,String gender,String rating){
        //TextView textView=itemView.findViewById(R.id.textview_row);
        //textView.setText("Name:"+name+"\n"+"\n"+"Number of ambulance :"+age+"\n"+"\n"+"Number of beds:"+gender+"\n"+"\n"+"Ratting"+rating);
        TextView nametv=itemView.findViewById(R.id.name_tv);
        TextView gendertv=itemView.findViewById(R.id.gender_tv);
        TextView ambulance_tv=itemView.findViewById(R.id.ambulance_tv);
        TextView rating_tv=itemView.findViewById(R.id.rating_tv);
        nametv.setText(name);
        gendertv.setText(age);
        ambulance_tv.setText(gender);
        rating_tv.setText(rating);
    }
    private ViewHolder.Clicklistener mClicklistener;
    public interface Clicklistener{
        void onItemlongClick(View view,int position);

    }
    public void setOnClickListener(ViewHolder.Clicklistener clickListener){
        mClicklistener=clickListener;
    }
}

