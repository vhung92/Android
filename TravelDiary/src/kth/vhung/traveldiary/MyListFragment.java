package kth.vhung.traveldiary;

import kth.vhung.traveldiary.database.DestinationDatabaseHelper;
import kth.vhung.traveldiary.database.DestinationTableContract.DestinationTable;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;



public class MyListFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;
private SQLiteDatabase database;
    // The container Activity must implement this interface so the fragment can deliver messages
    public interface OnHeadlineSelectedListener {
        
        public void onSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        Context myContext = getActivity();
        DestinationDatabaseHelper db = new DestinationDatabaseHelper(myContext);
        database = db.getReadableDatabase();
        String[] projection = {DestinationTable.COLUMN_DESCRIPTION};
        Cursor c = database.query(DestinationTable.TABLE_NAME, projection, null, null, null, null, null);
        
        int[] to = new int[] {R.id.description};
        SimpleCursorAdapter da = new SimpleCursorAdapter(myContext, layout, c, projection, to, 0);
        setListAdapter(da);
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onSelected(position);
        
        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}