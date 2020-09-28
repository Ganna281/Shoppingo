import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ItemsDataBase extends SQLiteOpenHelper {
    private static String databaseName = "items";
    private SQLiteDatabase db;
    ItemsDataBase(Context context)
    {
        super(context,databaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
