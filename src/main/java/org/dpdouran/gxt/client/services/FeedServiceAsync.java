package org.dpdouran.gxt.client.services;

import org.dpdouran.gxt.shared.model.Feed;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

public interface FeedServiceAsync {

	void createNewFeed(AsyncCallback<Feed> callback);
	void saveFeed(Feed feed, AsyncCallback<Void> callback);


	void addExistingFeed(String feedUrl, AsyncCallback<Void> async);

	void loadFeedList(AsyncCallback<ArrayList<Feed>> async);
}
