package hr.tvz.android.projektpecic.baza;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = BP.class)
public class Mec {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @ForeignKey(stubbedRelationship = true)
    Igrac igrac1;

    @ForeignKey(stubbedRelationship = true)
    Igrac igrac2;

    @Column
    String mjestoOdrzavanja;

    @Column
    String datumOdrzavanja;

    @Column
    float zvjezdiceMec;

    public int getId() { return id; }

    public Igrac getIgrac1() { return igrac1; }

    public Igrac getIgrac2() { return igrac2; }

    public String getMjestoOdrzavanja() { return mjestoOdrzavanja; }

    public String getDatumOdrzavanja() { return datumOdrzavanja; }

    public float getZvjezdiceMec() { return zvjezdiceMec; }

    public void setIgrac1(Igrac igrac1) { this.igrac1 = igrac1; }

    public void setIgrac2(Igrac igrac2) { this.igrac2 = igrac2; }

    public void setMjestoOdrzavanja(String mjestoOdrzavanja) { this.mjestoOdrzavanja = mjestoOdrzavanja; }

    public void setDatumOdrzavanja(String datumOdrzavanja) { this.datumOdrzavanja = datumOdrzavanja; }

    public void setZvjezdiceMec(float zvjezdiceMec) { this.zvjezdiceMec = zvjezdiceMec; }
}
