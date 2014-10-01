package com.nbempire.android.sample.component.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.nbempire.android.sample.domain.Item;
import com.nbempire.android.sample.repository.impl.ItemRepositoryImpl;
import com.nbempire.android.sample.service.ItemService;
import com.nbempire.android.sample.service.impl.ItemServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in a service on a
 * separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static helper methods.
 */
public class ItemTrackerService extends IntentService {

    /**
     * Used for log messages.
     */
    private static final String TAG = "ItemTrackerService";
    private ItemService itemService;

    public static class Action {
        public static final String CHECK_ITEMS = "com.nbempire.android.sample.component.service.action.CHECK_ITEMS";
        private static final String TRACK_ITEM = "com.nbempire.android.sample.component.service.action.TRACK_ITEM";
        private static final String STOP_TRACKING_ITEM = "com.nbempire.android.sample.component.service.action.STOP_TRACKING_ITEM";
    }

    private static final String ITEM_ID = "com.nbempire.android.sample.component.service.extra.ITEM_ID";
    private static final String ITEM_PRICE = "com.nbempire.android.sample.component.service.extra.ITEM_PRICE";
    private static final String ITEM_STOP_TIME = "com.nbempire.android.sample.component.service.extra.ITEM_STOP_TIME";

    public ItemTrackerService() {
        super("ItemTrackerService");
        itemService = new ItemServiceImpl(this, new ItemRepositoryImpl(this));
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "onHandleIntent...");

        if (intent != null) {
            final String action = intent.getAction();

            if (Action.CHECK_ITEMS.equals(action)) {
                handleActionCheckItems();
            } else if (Action.TRACK_ITEM.equals(action)) {
                itemService.trackItem(intent.getStringExtra(ITEM_ID), intent.getLongExtra(ITEM_PRICE, 0), intent.getLongExtra(ITEM_STOP_TIME, 0));
            } else if (Action.STOP_TRACKING_ITEM.equals(action)) {
                itemService.stopTracking(intent.getStringExtra(ITEM_ID));
            } else {
                Log.e(TAG, "No action mapped for value: " + action);
                //  TODO : Should I do something here?
            }
        }
    }

    private void handleActionCheckItems() {
        Log.v(TAG, "handleActionCheckItems...");
        List<Item> trackedItems = itemService.getTrackedItems();

        for (Item trackedItem : trackedItems) {
            Log.d(TAG, "Checking item id: " + trackedItem.getId() + ", price: " + trackedItem.getPrice() + ", stopTime: " + trackedItem.getStopTime());
        }

        if (trackedItems.size() == 0) {
            Log.d(TAG, "There are no tracked items in database.");
        }
    }

    //  TODO : Refactor public methods...

    /**
     * Starts this service to perform action track item with the given parameters. If the service is
     * already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionTrackItem(Context context, String id, Long price, Date stopTime) {
        Intent intent = new Intent(context, ItemTrackerService.class);
        intent.setAction(Action.TRACK_ITEM);
        intent.putExtra(ITEM_ID, id);
        intent.putExtra(ITEM_PRICE, price);
        intent.putExtra(ITEM_STOP_TIME, stopTime.getTime());
        context.startService(intent);
    }

    /**
     * Starts this service to perform action stop tracking item with the given parameters. If the
     * service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionStopTrackingItem(Activity context, String id) {
        Intent intent = new Intent(context, ItemTrackerService.class);
        intent.setAction(Action.STOP_TRACKING_ITEM);
        intent.putExtra(ITEM_ID, id);
        context.startService(intent);
    }

}