package hr.tvz.android.projektpecic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import hr.tvz.android.projektpecic.baza.Igrac;
import hr.tvz.android.projektpecic.baza.Mec;
import hr.tvz.android.projektpecic.baza.Teren;
import hr.tvz.android.projektpecic.fragmenti.JezikFragment;
import hr.tvz.android.projektpecic.fragmenti.MeceviFragment;
import hr.tvz.android.projektpecic.fragmenti.TereniFragment;
import hr.tvz.android.projektpecic.databinding.PocetniZaslonActivityBinding;

public class PocetniZaslonActivity extends AppCompatActivity {

    private PocetniZaslonActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(getApplicationContext());

        FlowManager.init(new FlowConfig.Builder(this).build());

        List<Teren> tereni = SQLite.select().from(Teren.class).queryList();
        if(tereni.size() == 0) {
            puniBazu();
        }


        binding = PocetniZaslonActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ActionBar actionBar = getSupportActionBar();
        Drawable bojaDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        actionBar.setBackgroundDrawable(bojaDrawable);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
               .replace(R.id.fragmentContainer, new TereniFragment())
               .commit();

        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment odabraniFragment = null;

                switch(item.getItemId()) {
                    case R.id.mecevi:
                        odabraniFragment = new MeceviFragment();
                        break;
                    case R.id.tereni:
                        odabraniFragment = new TereniFragment();
                        break;
                    case R.id.jezik:
                        odabraniFragment = new JezikFragment();
                }

                getSupportFragmentManager().beginTransaction()
                                           .replace(R.id.fragmentContainer, odabraniFragment)
                                           .commit();

                return true;
            }
        });
    }

    private void brisiBazu() {
        SQLite.delete().from(Teren.class).async().execute();
        SQLite.delete().from(Igrac.class).async().execute();
        // SQLite.delete().from(Mec.class).async().execute();
    }

    private void puniBazu() {
        // Tereni

        Teren teren1 = new Teren();
        teren1.setTurnir("Barcelona Open");
        teren1.setLink("https://cdn-images.imagevenue.com/88/b6/74/ME13IMNZ_o.png");
        teren1.setKompleks("Real Club de Tenis Barcelona");
        teren1.setVrsta("ATP 500");
        FlowManager.getModelAdapter(Teren.class).save(teren1);

        Teren teren2 = new Teren();
        teren2.setTurnir("Madrid Open");
        teren2.setLink("https://cdn-images.imagevenue.com/f0/0c/48/ME13IMNU_o.png");
        teren2.setKompleks("Caja Mágica");
        teren2.setVrsta("Masters 1000");
        FlowManager.getModelAdapter(Teren.class).save(teren2);

        Teren teren3 = new Teren();
        teren3.setTurnir("Monte-Carlo Masters");
        teren3.setLink("https://cdn-images.imagevenue.com/66/2e/2f/ME13IMNV_o.png");
        teren3.setKompleks("Monte Carlo Country Club");
        teren3.setVrsta("Masters 1000");
        FlowManager.getModelAdapter(Teren.class).save(teren3);

        Teren teren4 = new Teren();
        teren4.setTurnir("Roland Garros");
        teren4.setLink("https://cdn-images.imagevenue.com/cf/26/61/ME13IMNW_o.png");
        teren4.setKompleks("Stade Roland Garros");
        teren4.setVrsta("Grand Slam");
        FlowManager.getModelAdapter(Teren.class).save(teren4);

        Teren teren5 = new Teren();
        teren5.setTurnir("Internazionali d'Italia");
        teren5.setLink("https://cdn-images.imagevenue.com/aa/ac/8f/ME13IMNX_o.png");
        teren5.setKompleks("Foro Italico");
        teren5.setVrsta("Masters 1000");
        FlowManager.getModelAdapter(Teren.class).save(teren5);

        Teren teren6 = new Teren();
        teren6.setTurnir("Croatia Open");
        teren6.setLink("https://cdn-images.imagevenue.com/49/84/0b/ME13IMNY_o.png");
        teren6.setKompleks("ITC Stella Maris");
        teren6.setVrsta("ATP 250");
        FlowManager.getModelAdapter(Teren.class).save(teren6);

        // Igraci

        Igrac igrac1 = new Igrac();
        igrac1.setIme("Rafael Nadal");
        igrac1.setNacionalnost("ESP");
        igrac1.setGodine(35);
        igrac1.setTitule(88);
        igrac1.setRuka("L");
        igrac1.setLinkIgrac("https://cdn-images.imagevenue.com/f1/6c/e5/ME13INMK_o.png");
        FlowManager.getModelAdapter(Igrac.class).save(igrac1);

        Igrac igrac2 = new Igrac();
        igrac2.setIme("Roger Federer");
        igrac2.setNacionalnost("SUI");
        igrac2.setGodine(39);
        igrac2.setTitule(103);
        igrac2.setRuka("R");
        igrac2.setLinkIgrac("https://cdn-images.imagevenue.com/2f/34/e4/ME13INMP_o.png");
        FlowManager.getModelAdapter(Igrac.class).save(igrac2);

        Igrac igrac3 = new Igrac();
        igrac3.setIme("Marin Čilić");
        igrac3.setNacionalnost("HR");
        igrac3.setGodine(32);
        igrac3.setTitule(19);
        igrac3.setRuka("R");
        igrac3.setLinkIgrac("https://cdn-images.imagevenue.com/b6/0b/9b/ME13INML_o.png");
        FlowManager.getModelAdapter(Igrac.class).save(igrac3);

        Igrac igrac4 = new Igrac();
        igrac4.setIme("Fabio Fognini");
        igrac4.setNacionalnost("ITA");
        igrac4.setGodine(34);
        igrac4.setTitule(9);
        igrac4.setRuka("R");
        igrac4.setLinkIgrac("https://cdn-images.imagevenue.com/57/54/91/ME13INMQ_o.png");
        FlowManager.getModelAdapter(Igrac.class).save(igrac4);

        Igrac igrac5 = new Igrac();
        igrac5.setIme("Juan Martín del Potro");
        igrac5.setNacionalnost("ARG");
        igrac5.setGodine(32);
        igrac5.setTitule(22);
        igrac5.setRuka("R");
        igrac5.setLinkIgrac("https://cdn-images.imagevenue.com/3f/f8/7a/ME13INMM_o.png");
        FlowManager.getModelAdapter(Igrac.class).save(igrac5);

        Igrac igrac6 = new Igrac();
        igrac6.setIme("Novak Đoković");
        igrac6.setNacionalnost("SRB");
        igrac6.setGodine(34);
        igrac6.setTitule(84);
        igrac6.setRuka("R");
        igrac6.setLinkIgrac("https://cdn-images.imagevenue.com/1c/b8/51/ME13INMN_o.png");
        FlowManager.getModelAdapter(Igrac.class).save(igrac6);
    }
}