package hr.tvz.android.projektpecic.baza;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = BP.class)
public class Teren {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String turnir;

    @Column
    String link;

    @Column
    String kompleks;

    @Column
    String vrsta;

    public int getId() { return id; }

    public String getTurnir() { return turnir; }

    public String getLink() { return link; }

    public String getKompleks() { return kompleks; }

    public String getVrsta() { return vrsta; }

    public void setTurnir(String turnir) { this.turnir = turnir; }

    public void setLink(String link) { this.link = link; }

    public void setKompleks(String kompleks) { this.kompleks = kompleks; }

    public void setVrsta(String vrsta) { this.vrsta = vrsta; }
}
