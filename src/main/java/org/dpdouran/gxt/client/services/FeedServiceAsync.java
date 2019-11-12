package org.dpdouran.gxt.client.services;

import org.dpdouran.gxt.shared.model.Feed;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FeedServiceAsync {

	void createNewFeed(AsyncCallback<Feed> callback);
	void saveFeed(Feed feed, AsyncCallback<Void> callback);


}
