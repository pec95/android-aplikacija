package hr.tvz.android.projektpecic.baza;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = BP.NAME, version = BP.VERSION, foreignKeyConstraintsEnforced = true)
public class BP {
    public static final String NAME = "TenisBP";
    public static final int VERSION = 1;
}
