package hr.tvz.android.projektpecic.adapteri;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hr.tvz.android.projektpecic.R;
import hr.tvz.android.projektpecic.baza.Teren;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<Teren> tereni;
    private Uri uriSlike;
    private LayoutInflater inflater;

    public GridAdapter(Context context, List<Teren> tereni) {
        this.context = context;
        this.tereni = tereni;
    }

    @Override
    public int getCount() {
        return tereni.size();
    }

    @Override
    public Object getItem(int position) {
        return tereni.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        uriSlike = Uri.parse(tereni.get(position).getLink());

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.image_grid, parent, false);

        SimpleDraweeView draweeSlika = (SimpleDraweeView) convertView.findViewById(R.id.draweeGrid);
        draweeSlika.setImageURI(uriSlike);

        TextView tekstIspodSlike = (TextView) convertView.findViewById(R.id.tekstIspodSlike);
        tekstIspodSlike.setText(tereni.get(position).getTurnir());

        return convertView;
    }
}
