package org.dpdouran.gxt.server.services;

import java.util.UUID;

import org.dpdouran.gxt.client.services.FeedService;
import org.dpdouran.gxt.shared.model.Feed;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FeedServiceImpl extends RemoteServiceServlet implements FeedService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Feed createNewFeed() {
		UUID uuid = UUID.randomUUID();
		return new Feed(uuid.toString());
	}

	public void saveFeed(Feed feed) {
//		Element eleRoot = new Element("rss");
//		eleRoot.setAttribute(new Attribute("version", "2.0"));
//
//		// Create a document from the feed object
//		Document document = new Document(eleRoot);
//
//		Element eleChannel = new Element("channel");
//		Element eleTitle = new Element("title");
//		Element eleDescription = new Element("description");
//		Element eleLink = new Element("link");
//
//		eleTitle.setText(feed.getTitle());
//		eleDescription.setText(feed.getDescription());
//		eleLink.setText(feed.getLink());
//
//		eleChannel.addContent(eleTitle);
//		eleChannel.addContent(eleDescription);
//		eleChannel.addContent(eleLink);
//
//		eleRoot.addContent(eleChannel);
	}
}