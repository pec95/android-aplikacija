package hr.tvz.android.projektpecic.fragmenti;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Locale;

import hr.tvz.android.projektpecic.R;
import hr.tvz.android.projektpecic.databinding.FragmentJezikBinding;

public class JezikFragment extends Fragment {

    private Locale trenutniLocale;
    private Context context;

    private FragmentJezikBinding binding;

    public JezikFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJezikBinding.inflate(inflater);
        View view = binding.getRoot();

        context = view.getContext();

        trenutniLocale = getResources().getConfiguration().getLocales().get(0);

        if(trenutniLocale.toString().startsWith("en")) {
            binding.rg.check(R.id.engRB);
        }
        if(trenutniLocale.toString().startsWith("hr")) {
            binding.rg.check(R.id.hrRB);
        }

        binding.engRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String promjenaJezika = getResources().getString(R.string.toast_jezik);
                Toast.makeText(context, promjenaJezika, Toast.LENGTH_LONG).show();

                Locale jezik = new Locale("en_US");
                postaviLocale(jezik);
            }
        });

        binding.hrRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String promjenaJezika = getResources().getString(R.string.toast_jezik);
                Toast.makeText(context, promjenaJezika, Toast.LENGTH_LONG).show();

                Locale jezik = new Locale("hr");
                postaviLocale(jezik);
            }
        });

        return view;
    }

    private void postaviLocale(Locale jezik) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        conf.setLocale(jezik);
        res.updateConfiguration(conf, dm);
    }
}
