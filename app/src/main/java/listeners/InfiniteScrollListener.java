package listeners;

import android.widget.AbsListView;

public abstract class InfiniteScrollListener implements AbsListView.OnScrollListener {

    private int pageSize = 20;
    private int currentPage = 1;
    private boolean isLoading = false;

    public InfiniteScrollListener() {
        // Do Nothing
    }

    public abstract void getMoreItems(int page, int pageSize);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Do Nothing
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (!isLoading && ((firstVisibleItem + visibleItemCount) >= (totalItemCount - 1))) {
            isLoading = true;
            getMoreItems(currentPage, pageSize);
            currentPage++;
        }
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }
}