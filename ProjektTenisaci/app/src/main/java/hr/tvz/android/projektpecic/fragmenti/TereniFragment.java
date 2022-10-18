package hr.tvz.android.projektpecic.fragmenti;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.android.projektpecic.adapteri.GridAdapter;
import hr.tvz.android.projektpecic.R;
import hr.tvz.android.projektpecic.baza.Teren;
import hr.tvz.android.projektpecic.databinding.FragmentTereniBinding;

public class TereniFragment extends Fragment {

    private List<Teren> tereni;
    private Context context;

    private FragmentTereniBinding binding;

    public TereniFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTereniBinding.inflate(inflater);
        View view = binding.getRoot();

        context = view.getContext();

        if(tereni != null) {
            tereni.clear();
        }

        tereni = SQLite.select().from(Teren.class).queryList();

        binding.gw.setAdapter(new GridAdapter(context, tereni));

        binding.gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RezervacijaFragment rezervacija = new RezervacijaFragment();
                Bundle bundle = new Bundle();
                Teren teren = tereni.get(position);

                bundle.putString("turnir", teren.getTurnir());
                bundle.putString("vrsta", teren.getVrsta());
                bundle.putString("kompleks", teren.getKompleks());
                bundle.putString("link", teren.getLink());

                rezervacija.setArguments(bundle);

                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();

                manager.beginTransaction()
                       .replace(R.id.fragmentContainer, rezervacija)
                       .commit();
            }
        });

        return view;
    }
}