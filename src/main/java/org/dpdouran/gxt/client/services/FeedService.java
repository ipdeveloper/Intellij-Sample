package org.dpdouran.gxt.client.services;

import org.dpdouran.gxt.shared.model.Feed;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("feed-service")
public interface FeedService extends RemoteService {
	Feed createNewFeed();
	void saveFeed(Feed feed);

}
