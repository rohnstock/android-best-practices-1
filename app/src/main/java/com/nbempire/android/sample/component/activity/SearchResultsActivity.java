package com.nbempire.android.sample.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.nbempire.android.sample.R;
import com.nbempire.android.sample.component.fragment.SearchFragment;
import com.nbempire.android.sample.domain.Item;

/**
 * Created by nbarrios on 24/09/14.
 */
public class SearchResultsActivity extends Activity implements SearchFragment.OnFragmentInteractionListener {

    /**
     * Used for log messages.
     */
    private static final String TAG = "SearchResultsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate...");
        setContentView(R.layout.activity_search_results);
    }

    @Override
    public void onSearchItemSelected(Item item) {
        Log.v(TAG, "onSearchItemSelected...");
        Log.i(TAG, "Opening VIP for item: " + item.getTitle());

        Intent vipIntent = new Intent(this, VIPActivity.class);
        vipIntent.putExtra(VIPActivity.Keys.ITEM, item);
        startActivity(vipIntent);
    }

}
