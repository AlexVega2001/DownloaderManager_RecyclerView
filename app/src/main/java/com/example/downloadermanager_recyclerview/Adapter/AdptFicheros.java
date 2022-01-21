package com.example.downloadermanager_recyclerview.Adapter;

import android.Manifest;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.downloadermanager_recyclerview.Model.DownloadManagerClass;
import com.example.downloadermanager_recyclerview.Model.Ficheros;
import com.example.downloadermanager_recyclerview.R;

import java.util.List;

public class AdptFicheros extends RecyclerView.Adapter<AdptFicheros.ViewHolder> {

    private List<Ficheros> lstficheros;
    private LayoutInflater inflater;
    private Context ctx;
    DownloadManagerClass dwc;

    public AdptFicheros(List<Ficheros> itemlst, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.ctx = context;
        this.lstficheros = itemlst;
        this.dwc = new DownloadManagerClass(context);
    }

    @Override
    public int getItemCount() {
        return lstficheros.size();
    }

    @Override
    public AdptFicheros.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_layout_list, null);
        return new AdptFicheros.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdptFicheros.ViewHolder holder, final int position) {
        Ficheros ficheros = lstficheros.get(position);

        holder.crdv.setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.slide));
        holder.description.setText(ficheros.getDescription());
        holder.tamanho.setText(ficheros.getTamanho());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView description, tamanho;
        Button btnDescarga;
        CardView crdv;

        ViewHolder(View itemView) {
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.txtvDescrpUrl);
            tamanho = (TextView) itemView.findViewById(R.id.txtvTamanhoUrl);
            btnDescarga = (Button) itemView.findViewById(R.id.btnPDF);
            crdv = (CardView) itemView.findViewById(R.id.cardView);

            btnDescarga.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //Position Adapter
            int position = getAdapterPosition();

            //Position Object Ficheros
            Ficheros obj = lstficheros.get(position);
            if (v.getId() == btnDescarga.getId()) {
                dwc.startDownload(obj.getUrl(), obj.getDescription());
            }
        }
    }

}
