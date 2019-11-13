package org.dpdouran.gxt.client.lists;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.dpdouran.gxt.client.RSSReaderConstants;

import org.dpdouran.gxt.client.services.FeedServiceAsync;
import org.dpdouran.gxt.shared.model.Feed;

import java.util.ArrayList;
import java.util.List;

public class FeedList extends LayoutContainer {
    public FeedList() {
        setLayout(new FitLayout());
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        if (false) {
//            final ListField<Feed> feedList = new
////                    ListField<Feed>();
//            final ListStore<Feed> feedStore = Registry.get(RSSReaderConstants.FEED_STORE);
//            final FeedServiceAsync feedService = Registry
//                    .get(RSSReaderConstants.FEED_SERVICE);
//            feedService.loadFeedList(new AsyncCallback<List<Feed>>() {
//                @Override
//                public void onFailure(Throwable caught) {
//                    caught.printStackTrace();
//
//                }
//
//                @Override
//                public void onSuccess(List<Feed> result) {
//                    if (result != null) {
//                        for (Feed feed : result) {
//                            feedStore.add(feed);
//                        }
//                    }
//
//                }
//            });
//            feedList.setStore(feedStore);
//            feedList.setDisplayField("title");
//            add(feedList);

        } else {

            final FeedServiceAsync feedService = Registry.get(RSSReaderConstants.FEED_SERVICE);

            RpcProxy<ArrayList<Feed>> proxy = new RpcProxy<ArrayList<Feed>>() {
                @Override
                protected void load(Object loadConfig, AsyncCallback<ArrayList<Feed>> callback) {
                    feedService.loadFeedList(callback);
                }
            };
            BeanModelReader reader = new BeanModelReader();
            ListLoader<ListLoadResult<Feed>> loader = new BaseListLoader<ListLoadResult<Feed>>(proxy, reader);

            ListStore<BeanModel> feedStore = new ListStore<BeanModel>(loader);
//            Store<BeanModel> store = new Store<BeanModel>() {

//            };

            loader.load();
            Registry.register(RSSReaderConstants.FEED_STORE, feedStore);


            final ListField<BeanModel> feedList = new ListField<BeanModel>();
            feedList.setStore(feedStore);
            feedList.setDisplayField("title");
            add(feedList);
        }
    }

}
