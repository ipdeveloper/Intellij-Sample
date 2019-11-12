package org.dpdouran.gxt.client;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import org.dpdouran.gxt.client.components.LinkFeedPopup;
import org.dpdouran.gxt.client.lists.FeedList;
import org.dpdouran.gxt.client.services.FeedServiceAsync;
import org.dpdouran.gxt.client.window.FeedWindow;
import org.dpdouran.gxt.shared.model.Feed;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RssNavigationPanel extends ContentPanel {
    final LinkFeedPopup linkFeedPopup = new LinkFeedPopup();

    public RssNavigationPanel() {
        setHeading("Navigation");
        final Button btnLinkFeed = new Button("Link feed");
        btnLinkFeed.setIconStyle("link-feed");
        setButtonAlign(HorizontalAlignment.LEFT);
        addButton(btnLinkFeed);
        ToolTipConfig linkFeedToolTipConfig = new ToolTipConfig();
        linkFeedToolTipConfig.setTitle("Link to existing RSS		 feed");
        linkFeedToolTipConfig
                .setText("Allows you to enter the URL		 of an existing RSS feed you would like to link to");
        btnLinkFeed.setToolTip(linkFeedToolTipConfig);
        linkFeedPopup.setConstrainViewport(true);

        btnLinkFeed.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!linkFeedPopup.isVisible()) {
                    linkFeedPopup.show(btnLinkFeed.getElement(), "bl-tl?");
                } else {
                    linkFeedPopup.hide();
                }
            }
        });

        final Button btnCreateFeed = new Button("Create feed");
        btnCreateFeed.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                createNewFeedWindow();
            }
        });
        btnCreateFeed.setIconStyle("create-feed");
        ToolTipConfig createNewToolTipConfig = new ToolTipConfig();
        createNewToolTipConfig.setTitle("Create a new RSS feed");
        createNewToolTipConfig.setText("Creates a new RSS feed");
        btnCreateFeed.setToolTip(createNewToolTipConfig);
        addButton(btnCreateFeed);
        setLayout(new FitLayout());
        add(new FeedList());

    }

    private void createNewFeedWindow() {
//		final Window newFeedWindow = new FeedWindow(new Feed());
//		newFeedWindow.show();
        final FeedServiceAsync feedService = Registry.get(RSSReaderConstants.FEED_SERVICE);
        feedService.createNewFeed(new AsyncCallback<Feed>() {
            @Override
            public void onFailure(Throwable caught) {
                Info.display("RSSReader", "Unable to create a new	feed");
            }

            @Override
            public void onSuccess(Feed feed) {
//                Info.display("RSSReader", "Unable to create a new	feed");
				final Window newFeedWindow = new FeedWindow(feed);
				newFeedWindow.show();
            }
        });

    }
}