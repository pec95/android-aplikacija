package hr.tvz.android.projektpecic.fragmenti;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hr.tvz.android.projektpecic.R;
import hr.tvz.android.projektpecic.baza.Igrac;
import hr.tvz.android.projektpecic.baza.Mec;
import hr.tvz.android.projektpecic.baza.Teren;
import hr.tvz.android.projektpecic.databinding.FragmentRezervacijaBinding;

public class RezervacijaFragment extends Fragment {

    private Context context;
    private String turnir, vrsta, kompleks, link;
    private List<Igrac> igraci;
    private String[] igraciImena = { "0", "1", "2", "3", "4", "5" };
    private Igrac igrac1, igrac2;
    private String datum, igracIme1, igracIme2;
    private float brojZvjezdica = 2.5f;

    private DatePickerDialog dpd;

    private FragmentRezervacijaBinding binding;

    public RezervacijaFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRezervacijaBinding.inflate(inflater);
        View view = binding.getRoot();

        context = view.getContext();

        igraci = SQLite.select().from(Igrac.class).queryList();

        for(int i = 0; i < igraci.size(); i++) {
            igraciImena[i] = igraci.get(i).getIme();
        }

        turnir = getArguments().getString("turnir");
        vrsta =  getArguments().getString("vrsta");
        kompleks =  getArguments().getString("kompleks");
        link = getArguments().getString("link");
        Uri draweeUri = Uri.parse(link);

        binding.turnir.setText(turnir);
        binding.vrsta.setText(vrsta);
        binding.kompleks.setText(kompleks);
        binding.rezervacijaDrawee.setImageURI(draweeUri);

        Animation animacija = AnimationUtils.loadAnimation(context, R.anim.animacija);

        binding.turnir.setAnimation(animacija);
        binding.vrsta.setAnimation(animacija);
        binding.kompleks.setAnimation(animacija);

        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, igraciImena);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.igrac1Spinner.setAdapter(aa);
        binding.igrac2Spinner.setAdapter(aa);

        binding.igrac1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                igracIme1 = igraciImena[position];
                igrac1 = igraci.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.igrac2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                igracIme2 = igraciImena[position];
                igrac2 = igraci.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.datumMeca.setShowSoftInputOnFocus(false);
        binding.datumMeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar kalendar = Calendar.getInstance();
                int dan = kalendar.get(Calendar.DAY_OF_MONTH);
                int mjesec = kalendar.get(Calendar.MONTH);
                int godina = kalendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                datum = dayOfMonth + "." + (month + 1) + "." + year + ".";
                                binding.datumMeca.setText(datum);
                            }
                        }, godina, mjesec, dan);
                dpd.show();
            }
        });

        binding.zvjezdice.setRating(brojZvjezdica);
        binding.zvjezdice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                brojZvjezdica = rating;
            }
        });

        binding.gumbRezervacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datum == null || datum.isEmpty() || igracIme1.equals(igracIme2)) {
                    Toast.makeText(context, R.string.greska_rezervacija, Toast.LENGTH_LONG).show();
                }
                else {
                    Mec mec = new Mec();
                    mec.setIgrac1(igrac1);
                    mec.setIgrac2(igrac2);
                    mec.setMjestoOdrzavanja(kompleks);
                    mec.setDatumOdrzavanja(datum);
                    mec.setZvjezdiceMec(brojZvjezdica);
                    FlowManager.getModelAdapter(Mec.class).save(mec);

                    Toast.makeText(context, R.string.uspjesno_rezervacija, Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
