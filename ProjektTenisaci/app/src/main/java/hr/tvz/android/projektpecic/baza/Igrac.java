package hr.tvz.android.projektpecic.baza;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

@Table(database = BP.class)
public class Igrac {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String ime;

    @Column
    String nacionalnost;

    @Column
    int godine;

    @Column
    int titule;

    @Column
    String ruka;

    @Column
    String linkIgrac;

    List<Mec> mecevi;

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "mecevi")
    public List<Mec> getMecevi() {
        if(mecevi == null || mecevi.isEmpty()) {
            List<Mec> meceviDrugi;
            mecevi = SQLite.select().from(Mec.class).where(Mec_Table.igrac1_id.eq(id)).queryList();
            meceviDrugi = SQLite.select().from(Mec.class).where(Mec_Table.igrac2_id.eq(id)).queryList();
            mecevi.addAll(meceviDrugi);
        }
        return mecevi;
    }

    public int getId() { return id; }

    public String getIme() { return ime; }

    public String getNacionalnost() { return nacionalnost; }

    public int getGodine() { return godine; }

    public int getTitule() { return titule; }

    public String getRuka() { return ruka; }

    public String getLinkIgrac() { return linkIgrac; }

    public void setIme(String ime) { this.ime = ime; }

    public void setNacionalnost(String nacionalnost) { this.nacionalnost = nacionalnost; }

    public void setGodine(int godine) { this.godine = godine; }

    public void setTitule(int titule) { this.titule = titule; }

    public void setRuka(String ruka) { this.ruka = ruka; }

    public void setLinkIgrac(String linkIgrac) { this.linkIgrac = linkIgrac; }
}
