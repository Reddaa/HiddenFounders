# HiddenFounders

All right, simple app :D it allows you to view repositories as requested, infinite scrolling and when you click on a repo it takes you directly to it.

If you stumbled across this project and you don't know its intent, copy the abstract class InfiniteScrollerListener, and add a OnScrollListener to your listview

        listview.setOnScrollListener(new InfiniteScrollerListener() {
            @Override
            public boolean onItemsLoaded(int page, int totalItems) {
                getNextReposPage(page);
                return true;
            }

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }
        });
        
where getNextReposPage is your method to load next page. Happy scrolling.
