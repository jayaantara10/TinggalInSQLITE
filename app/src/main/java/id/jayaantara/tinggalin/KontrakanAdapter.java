package id.jayaantara.tinggalin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class KontrakanAdapter extends RecyclerView.Adapter<KontrakanAdapter.ViewAdapter>{

    private List<Kontrakan> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public KontrakanAdapter(Context context, List<Kontrakan> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kontrakan_cardview,parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontrakanAdapter.ViewAdapter holder, int position) {
        holder.tv_kontrakan_name.setText(list.get(position).nama_kontrakan);
        holder.tv_owner_name.setText(list.get(position).nama_pemilik);
        holder.tv_harga_sewa.setText(list.get(position).harga);
        holder.tv_kontrakan_location.setText(list.get(position).alamat);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView tv_kontrakan_name;
        TextView tv_owner_name;
        TextView tv_harga_sewa;
        TextView tv_kontrakan_location;


        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            tv_kontrakan_name = itemView.findViewById(R.id.tv_kontrakan_name);
            tv_owner_name = itemView.findViewById(R.id.tv_owner_name);
            tv_harga_sewa = itemView.findViewById(R.id.tv_harga_sewa);
            tv_kontrakan_location = itemView.findViewById(R.id.tv_kontrakan_location);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });



        }
    }
}
