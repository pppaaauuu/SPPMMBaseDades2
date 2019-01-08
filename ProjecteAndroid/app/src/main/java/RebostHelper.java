import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RebostHelper extends SQLiteOpenHelper {

    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_IDINGREDIENT = "id";
    public static final String COLUMN_NOMINGREDIENT = "nom";
    public static final String COLUMN_BASICINGREDIENT = "basic";
    public static final String COLUMN_QUEDAINGREDIENT = "queda";
    public static final String COLUMN_COMPRAINGREDIENT = "compra";
    public static final String TABLE_RECEPTA = "recepta";
    public static final String COLUMN_IDRECEPTA = "id";
    public static final String COLUMN_NOMRECEPTA = "nom";
    public static final String COLUMN_TEXTRECEPTA = "text";
    public static final String TABLE_RECPING = "receptaingredients";
    public static final String COLUMN_RECEPTA = "id_recepta";
    public static final String COLUMN_INGREDIENT = "id_ingredient";
    private static final String DATABASE_NAME = "receptes";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_ING = "create table "
            + TABLE_INGREDIENTS + "(" + COLUMN_IDINGREDIENT
            + " integer primary key autoincrement, "
            + COLUMN_NOMINGREDIENT + " text not null,"
            + COLUMN_BASICINGREDIENT + " integer,"
            + COLUMN_QUEDAINGREDIENT + " integer,"
            + COLUMN_COMPRAINGREDIENT  + " integer);";

    private static final String DATABASE_CREATE_REC = "create table "
            + TABLE_RECEPTA + "(" + COLUMN_IDRECEPTA
            + " integer primary key autoincrement, "
            + COLUMN_NOMRECEPTA + " text not null,"
            + COLUMN_TEXTRECEPTA + " text);";

    private static final String DATABASE_CREATE_RECING = "create table "
            + TABLE_RECPING + "("
            + COLUMN_RECEPTA + " integer,"
            + COLUMN_INGREDIENT + " integer "
            + "CONSTRAINT fk_rec "
            + "FOREIGN KEY ("+COLUMN_RECEPTA + ")"
            + "REFERENCES " + TABLE_RECEPTA + "("
            + COLUMN_IDRECEPTA + "),"
            + "CONSTRAINT fk_ing "
            + "FOREIGN KEY ("+COLUMN_INGREDIENT + ")"
            + "REFERENCES " + TABLE_INGREDIENTS + "("
            + COLUMN_IDINGREDIENT + "),"
            + "PRIMARY KEY (" + COLUMN_RECEPTA + ", " + COLUMN_INGREDIENT + ");";

    public RebostHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_ING);
        database.execSQL(DATABASE_CREATE_REC);
        database.execSQL(DATABASE_CREATE_RECING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(RebostHelper.class.getName(),
                "Modificant desde la versió " + oldVersion + " a "+ newVersion );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEPTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECPING);
        onCreate(db);
    }

}
