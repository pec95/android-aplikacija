package hr.tvz.android.projektpecic.fragmenti;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

import hr.tvz.android.projektpecic.R;
import hr.tvz.android.projektpecic.baza.BP;
import hr.tvz.android.projektpecic.baza.Igrac;
import hr.tvz.android.projektpecic.baza.Igrac_Table;
import hr.tvz.android.projektpecic.baza.Mec;
import hr.tvz.android.projektpecic.databinding.FragmentIgracBinding;

public class IgracFragment extends Fragment {

    private Context context;
    private int idIgraca;

    private FragmentIgracBinding binding;

    public IgracFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIgracBinding.inflate(inflater);
        View view = binding.getRoot();
        context = view.getContext();

        idIgraca = getArguments().getInt("idIgraca");

        Igrac igrac = SQLite.select().from(Igrac.class).where(Igrac_Table.id.eq(idIgraca)).querySingle();

        Animation animacija = AnimationUtils.loadAnimation(context, R.anim.animacija);

        binding.igracIme.setText(igrac.getIme());
        binding.igracIme.setAnimation(animacija);
        binding.igracNac.setText(igrac.getNacionalnost());
        binding.igracNac.setAnimation(animacija);

        Uri uriIgraca = Uri.parse(igrac.getLinkIgrac());
        binding.igracSlika.setImageURI(uriIgraca);

        binding.godineIgrac.setText(igrac.getGodine() + "");
        binding.tituleIgrac.setText(igrac.getTitule() + "");

        String desna = getResources().getString(R.string.desna);
        binding.rukaIgrac.setText(igrac.getRuka().equals("R") ? desna : "L");

        int i = 0;
        String vs = getResources().getString(R.string.vs) + " ";
        String na = " " + getResources().getString(R.string.na) + " ";
        String mecevi = "";

        for(Mec mec : igrac.getMecevi()) {
            ModelAdapter<Igrac> ma = FlowManager.getModelAdapter(Igrac.class);
            ma.load(mec.getIgrac1(), FlowManager.getWritableDatabase(BP.class));
            ma.load(mec.getIgrac2(), FlowManager.getWritableDatabase(BP.class));

            if((i == igrac.getMecevi().size() - 1) && (mec.getIgrac1().getId() == idIgraca)) {
                mecevi = mecevi + vs + mec.getIgrac2().getIme().toUpperCase() + na + mec.getMjestoOdrzavanja().toUpperCase();
            }
            else if(mec.getIgrac1().getId() == idIgraca) {
                mecevi = mecevi + vs + mec.getIgrac2().getIme().toUpperCase() + na + mec.getMjestoOdrzavanja().toUpperCase() + "\n";
                i++;
            }

            if((i == igrac.getMecevi().size() - 1) && (mec.getIgrac2().getId() == idIgraca)) {
                mecevi = mecevi + vs + mec.getIgrac1().getIme().toUpperCase() + na + mec.getMjestoOdrzavanja().toUpperCase();
            }
            else if(mec.getIgrac2().getId() == idIgraca) {
                mecevi = mecevi + vs + mec.getIgrac1().getIme().toUpperCase() + na + mec.getMjestoOdrzavanja().toUpperCase() + "\n";
                i++;
            }
        }

        binding.meceviIgrac.setText(mecevi);

        return view;
    }
}
