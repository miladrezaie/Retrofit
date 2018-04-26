package com.example.milad.retrofit.adapter;

import android.app.NativeActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.milad.retrofit.R;
import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.noteInfoActivity;

import java.util.List;


public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.MyViewHolder> {
    private Context context;
    private List<Note> notes;

    public MyRecyclerView(Context context, List<Note> users) {
        this.context = context;
        this.notes = users;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.receycler_view_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(notes.get(position).getName() + "  " + notes.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, noteInfoActivity.class);
                intent.putExtra("id", notes.get(position).get_id());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private Button btndelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            btndelete = (Button) itemView.findViewById(R.id.btndelete);
        }
    }
}
