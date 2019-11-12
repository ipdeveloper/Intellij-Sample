package org.dpdouran.gxt.client;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.store.ListStore;
import org.dpdouran.gxt.client.services.FeedService;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import org.dpdouran.gxt.shared.model.Feed;

public class FeedRSSStartUp implements EntryPoint {

	@Override
	public void onModuleLoad() {

        GWT.<Feed> create(Feed.class);
		Registry.register(RSSReaderConstants.FEED_SERVICE,
				 GWT.create(FeedService.class));
//        Registry.register(RSSReaderConstants.FEED_SERVICE,
//                GWT.create(FeedService.class));
        Registry.register(RSSReaderConstants.FEED_STORE, new
                ListStore<BeanModel>());

        Feed feed=new Feed();
        feed.setTitle("hello");
        feed.setDescription("congratuation");
        feed.setLink("aaaaaa");
//        final ListStore<BeanModel> feedStore =
//                Registry.get(RSSReaderConstants.FEED_STORE);
//        BeanModelFactory beanModelFactory =
//                BeanModelLookup.get().getFactory(feed.getClass());
//        feedStore.add(beanModelFactory.createModel(feed));

		Viewport viewport = new Viewport();
		final BorderLayout borderLayout = new BorderLayout();

		viewport.setLayout(borderLayout);

		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 20);
		northData.setCollapsible(false);
		northData.setSplit(false);
		HTML headerHtml = new HTML();
		headerHtml.setHTML("<h1>RSS Reader</h1>");

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setCollapsible(true);
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200, 150, 300);
		westData.setCollapsible(true);
		westData.setSplit(true);
		viewport.setLoadingPanelId("loading");

		// ContentPanel mainPanel = new ContentPanel();
		// ContentPanel navPanel = new ContentPanel();
		// viewport.add(mainPanel, centerData);
		// viewport.add(navPanel, westData);

		RssMainPanel mainPanel = new RssMainPanel();
		RssNavigationPanel navPanel = new RssNavigationPanel();
		viewport.add(mainPanel, centerData);
		viewport.add(navPanel, westData);
		viewport.add(headerHtml, northData);

		RootPanel.get().add(viewport);

	}

}
