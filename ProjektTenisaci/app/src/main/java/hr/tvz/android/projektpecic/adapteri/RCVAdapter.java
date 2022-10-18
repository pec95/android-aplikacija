package hr.tvz.android.projektpecic.adapteri;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

import java.util.List;

import hr.tvz.android.projektpecic.Dijalog;
import hr.tvz.android.projektpecic.R;
import hr.tvz.android.projektpecic.baza.BP;
import hr.tvz.android.projektpecic.baza.Igrac;
import hr.tvz.android.projektpecic.baza.Mec;
import hr.tvz.android.projektpecic.databinding.RcvItemBinding;
import hr.tvz.android.projektpecic.fragmenti.IgracFragment;

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.RCVViewHolder> {

    public static Context context;
    private List<Mec> mecevi;
    FragmentManager manager;

    public RCVAdapter(Context context, List<Mec> mecevi) {
        this.context = context;
        this.mecevi = mecevi;
    }

    public static class RCVViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView slikaIgraca1, slikaIgraca2;
        public TextView igrac1Ime, igrac2Ime, kompleks, datum;
        public RatingBar zvjezdice;
        public Button gumbIzbrisi;
        public ConstraintLayout slikaIIme1, slikaIIme2;

        public RCVViewHolder(View itemView) {
            super(itemView);

            slikaIgraca1 = (SimpleDraweeView) itemView.findViewById(R.id.igrac1Drawee);
            slikaIgraca2 = (SimpleDraweeView) itemView.findViewById(R.id.igrac2Drawee);
            igrac1Ime = (TextView) itemView.findViewById(R.id.igrac1Rcv);
            igrac2Ime = (TextView) itemView.findViewById(R.id.igrac2Rcv);
            kompleks = (TextView) itemView.findViewById(R.id.kompleksRcv);
            datum = (TextView) itemView.findViewById(R.id.datumRcv);
            zvjezdice = (RatingBar) itemView.findViewById(R.id.zvjezdiceRcv);
            slikaIIme1 = (ConstraintLayout) itemView.findViewById(R.id.slikaIIme1);
            slikaIIme2 = (ConstraintLayout) itemView.findViewById(R.id.slikaIIme2);
            gumbIzbrisi = (Button) itemView.findViewById(R.id.izbrisiGumbRcv);
        }
    }

    @NonNull
    @Override
    public RCVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_item, parent, false);
        RCVViewHolder viewHolder = new RCVViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RCVViewHolder holder, int position) {
        Mec mec = mecevi.get(position);

        manager = ((AppCompatActivity) context).getSupportFragmentManager();

        ModelAdapter<Igrac> ma = FlowManager.getModelAdapter(Igrac.class);
        ma.load(mec.getIgrac1(), FlowManager.getWritableDatabase(BP.class));
        ma.load(mec.getIgrac2(), FlowManager.getWritableDatabase(BP.class));

        Uri slika1Uri = Uri.parse(mec.getIgrac1().getLinkIgrac());
        holder.slikaIgraca1.setImageURI(slika1Uri);
        holder.igrac1Ime.setText(mec.getIgrac1().getIme());

        Uri slika2Uri = Uri.parse(mec.getIgrac2().getLinkIgrac());
        holder.slikaIgraca2.setImageURI(slika2Uri);
        holder.igrac2Ime.setText(mec.getIgrac2().getIme());

        holder.kompleks.setText(mec.getMjestoOdrzavanja());
        holder.datum.setText(mec.getDatumOdrzavanja());
        holder.zvjezdice.setRating(mec.getZvjezdiceMec());

        holder.slikaIIme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IgracFragment igracFragment = new IgracFragment();
                Bundle bundle = new Bundle();

                bundle.putInt("idIgraca", mec.getIgrac1().getId());
                igracFragment.setArguments(bundle);

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, igracFragment)
                        .commit();
            }
        });

        holder.slikaIIme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IgracFragment igracFragment = new IgracFragment();
                Bundle bundle = new Bundle();

                bundle.putInt("idIgraca", mec.getIgrac2().getId());
                igracFragment.setArguments(bundle);

                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                manager.beginTransaction()
                       .replace(R.id.fragmentContainer, igracFragment)
                       .commit();
            }
        });

        holder.gumbIzbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dijalog = new Dijalog(mec.getIgrac1().getIme(), mec.getIgrac2().getIme(), mec.getId());
                dijalog.show(manager, "Dijalog");
            }
        });
    }

    @Override
    public int getItemCount() { return mecevi.size(); }
}
