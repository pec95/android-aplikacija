package hr.tvz.android.projektpecic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import hr.tvz.android.projektpecic.baza.Mec;
import hr.tvz.android.projektpecic.baza.Mec_Table;
import hr.tvz.android.projektpecic.fragmenti.MeceviFragment;

public class Dijalog extends DialogFragment {

    private Context context;
    private String igrac1, igrac2;
    private int idMeca;

    public Dijalog(String igrac1, String igrac2, int idMeca) {
        this.igrac1 = igrac1;
        this.igrac2 = igrac2;
        this.idMeca = idMeca;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        context = getContext();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(getResources().getString(R.string.dijalog) + " " + igrac1.toUpperCase()
                            + " " + getResources().getString(R.string.vs) + " " + igrac2.toUpperCase() + "?")
               .setPositiveButton(R.string.da, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       SQLite.delete().from(Mec.class).where(Mec_Table.id.eq(idMeca)).execute();

                       MeceviFragment meceviFragment = new MeceviFragment();

                       FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                       manager.beginTransaction()
                              .replace(R.id.fragmentContainer, meceviFragment)
                              .commit();
                   }
               })
               .setNegativeButton(R.string.ne, null);

        return builder.create();
    }
}
