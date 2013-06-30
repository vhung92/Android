package kth.vhung.traveldiary.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public final class DestinationTableContract {
	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	public DestinationTableContract() {
	}

	private static final String TEXT_TYPE = " TEXT NOT NULL";
	private static final String CREATE_DATABASE = "CREATE TABLE "
			+ DestinationTable.TABLE_NAME + " (" + DestinationTable._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DestinationTable.COLUMN_DESCRIPTION
			+ TEXT_TYPE + " )";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_DATABASE);
		database.execSQL("INSERT INTO destination (description) VALUES ('Trip to Japan')");
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(DestinationTable.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + DestinationTable.TABLE_NAME);
		onCreate(database);
	}

	/* Inner class that defines the table contents */
	public static abstract class DestinationTable implements BaseColumns {
		public static final String TABLE_NAME = "destination";
		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_DESCRIPTION = "description";
	}
}