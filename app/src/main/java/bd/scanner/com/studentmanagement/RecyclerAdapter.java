package bd.scanner.com.studentmanagement;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faiza on 8/4/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{

    private List<Student> studentList;
    private Context context;

    private RecyclerItemClickListener recyclerItemClickListener;

    public RecyclerAdapter(Context context) {
        this.context = context;
        this.studentList = new ArrayList<>();
    }

    private void add(Student item) {
        studentList.add(item);
        notifyItemInserted(studentList.size() - 1);
    }

    public void addAll(List<Student> studentList) {
        for (Student contact : studentList) {
            add(contact);
        }
    }

    public void remove(Student item) {
        int position = studentList.indexOf(item);
        if (position > -1) {
            studentList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Student getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        final Holder contactHolder = new Holder(view);

        contactHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = contactHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemClickListener.onItemClick(adapterPos, contactHolder.itemView);
                    }
                }
            }
        });

        return contactHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Student student = studentList.get(position);

        holder.name.setText("Name: "+student.getStdName());
        holder.id.setText("ID: "+student.getStdId());
        holder.dpt.setText("Department: "+student.getStdDpt());
        holder.result.setText("Result: "+student.getStdResult());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView name,id,dpt,result;

        public Holder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.txtStdname);
            id = (TextView) itemView.findViewById(R.id.txtStdId);
            dpt = (TextView) itemView.findViewById(R.id.txtStdDpt);
            result = (TextView) itemView.findViewById(R.id.txtStdResult);


        }
    }
}