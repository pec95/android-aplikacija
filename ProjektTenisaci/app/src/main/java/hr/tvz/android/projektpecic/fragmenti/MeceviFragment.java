package hr.tvz.android.projektpecic.fragmenti;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import hr.tvz.android.projektpecic.adapteri.RCVAdapter;
import hr.tvz.android.projektpecic.baza.Mec;
import hr.tvz.android.projektpecic.databinding.FragmentMeceviBinding;

public class MeceviFragment extends Fragment {

    private Context context;
    private List<Mec> mecevi;

    private FragmentMeceviBinding binding;

    public MeceviFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeceviBinding.inflate(inflater);
        View view = binding.getRoot();

        context = view.getContext();

        mecevi = SQLite.select().from(Mec.class).queryList();

        binding.rcv.setLayoutManager(new LinearLayoutManager(context));
        binding.rcv.setAdapter(new RCVAdapter(context, mecevi));

        return view;
    }
}