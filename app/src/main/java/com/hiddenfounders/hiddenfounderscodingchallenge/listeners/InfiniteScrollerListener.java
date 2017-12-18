package com.hiddenfounders.hiddenfounderscodingchallenge.listeners;

import android.widget.AbsListView;

/**
 * Created by Redaa on 12/18/2017.
 */

public abstract class InfiniteScrollerListener implements AbsListView.OnScrollListener {
    //we can use this class wherever we want, and with whatever listview we fill like
    //we can also change some logic

    private int currentPage;
    private boolean loading = true;
    private int previousItemsCount = 0;
    private int startingPageindex = 0;

    public InfiniteScrollerListener() {

    }
    @Override
    public void onScroll(AbsListView absListView, int firstItem, int visibleItems, int totalItems) {

        if (totalItems < previousItemsCount) {
            currentPage = startingPageindex;
            previousItemsCount = totalItems;
            if (totalItems == 0) {
                loading = true;
            }
        }

        //we stop loading more if total items > previousitems count and we update currentPage to load
        // the next values next time
        if (loading && (totalItems > previousItemsCount)) {
            loading = false;
            previousItemsCount = totalItems;
            currentPage++;
        }
        //basically when we scroll down near the end we load more items firstItem being the index
        //of the first shown item on the screen and visibleItems is a value defined statically (5)
        //to basically load before we arrive at the very end
        if (!loading && (firstItem + visibleItems ) >= totalItems ) {
            loading = onItemsLoaded(currentPage + 1, totalItems);
        }
    }

    public abstract boolean onItemsLoaded(int page, int totalItems);
}
