package org.dpdouran.gxt.server.services;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RPC;
import org.dpdouran.gxt.client.services.FeedService;
import org.dpdouran.gxt.shared.model.Feed;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class FeedServiceImpl extends RemoteServiceServlet implements FeedService {
    private final static Logger LOGGER =
            Logger.getLogger(FeedServiceImpl.class
                    .getName());

//    private Map<String, Feed> feeds = new HashMap<String, Feed>();
    private ArrayList<Feed> feeds=new ArrayList<Feed>();
//    private final Persistence persistence = new FilePersistence();

    private static final long serialVersionUID = 1L;


    public Feed createNewFeed() {
        UUID uuid = UUID.randomUUID();
        Feed feed = new Feed();
        feed.setUuid(uuid.toString());

        return feed;
    }

    public void saveFeed(Feed feed) {
        Element eleRoot = new Element("rss");
        eleRoot.setAttribute(new Attribute("version", "2.0"));

        // Create a document from the feed object
        Document document = new Document(eleRoot);

        Element eleChannel = new Element("channel");
        Element eleTitle = new Element("title");
        Element eleDescription = new Element("description");
        Element eleLink = new Element("link");

        eleTitle.setText(feed.getTitle());
        eleDescription.setText(feed.getDescription());
        eleLink.setText(feed.getLink());

        eleChannel.addContent(eleTitle);
        eleChannel.addContent(eleDescription);
        eleChannel.addContent(eleLink);

        eleRoot.addContent(eleChannel);
        if (false) {
//            persistence.saveFeedXml(feed.getUuid(), document);
        } else {
            try {
                XMLOutputter serializer = new XMLOutputter();
                Format prettyFormat = Format.getPrettyFormat();
                serializer.setFormat(prettyFormat);
                System.out.println("At this point we would serialize the feed "
                        + feed.getTitle()
                        + " to a file. For now we are just going to write it to the console.");
                serializer.output(document, System.out);
            } catch (IOException e) {
                System.out.println("Error saving feed");
            }
        }
    }

    public void addExistingFeed(String feedUrl) {
        Feed loadResult = loadFeed(feedUrl);
        if (loadResult.getTitle() != null) {
            feeds.add(loadFeed(feedUrl));
//            feeds.put(feedUrl, loadFeed(feedUrl));
//            persistence.saveFeedList(feeds.keySet());
        }
    }

    public ArrayList<Feed> loadFeedList() {
        feeds.clear();
        Set<String> feedUrls =new HashSet<String>();// persistence.loadFeedList();
//        for (String feedUrl : feedUrls) {
//            feeds.put(feedUrl, loadFeed(feedUrl));
//        }
        for (int i = 0; i < 10; i++) {
            Feed a=new Feed();
            a.setLink("http://"+(i+1)+".com");
            a.setDescription("desc "+i);
            a.setTitle("title "+i);
//            feeds.put(a.getLink(), a);
            feeds.add(a);

        }
        
        return feeds;//new ArrayList<Feed>(feeds.values());
    }

    private Feed loadFeed(String feedUrl) {
        Feed feed = new Feed();
        feed.setLink(feedUrl);
        try {
            SAXBuilder parser = new SAXBuilder();
            Document document = parser.build(new URL(feedUrl));
            Element eleRoot = document.getRootElement();
            Element eleChannel = eleRoot.getChild("channel");
            feed.setTitle(eleChannel.getChildText("title"));
            feed.setDescription(eleChannel.getChildText("description"));
            feed.setLink(eleChannel.getChildText("link"));
            return feed;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO Error loading feed", e);
            return feed;
        } catch (JDOMException e) {
            LOGGER.log(Level.SEVERE, "Error parsing feed", e);
            return feed;
        }
    }
}