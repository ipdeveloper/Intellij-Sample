package org.dpdouran.gxt.client.forms;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.dpdouran.gxt.client.RSSReaderConstants;
import org.dpdouran.gxt.client.services.FeedServiceAsync;
import org.dpdouran.gxt.shared.model.Feed;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

public class FeedForm extends FormPanel {
    public FeedForm() {
        setHeaderVisible(false);
    }

    private final TextField<String> tfTitle = new TextField<String>();
    private final TextArea taDescription = new TextArea();
    private final TextField<String> tfLink = new TextField<String>();

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        tfTitle.setFieldLabel("Title");
        taDescription.setFieldLabel("Description");
        tfLink.setFieldLabel("Link");
        add(tfTitle);
        add(taDescription);
        add(tfLink);
    }

    public void save(final Feed feed) {
        feed.setTitle(tfTitle.getValue());
        feed.setDescription(taDescription.getValue());
        feed.setLink(tfLink.getValue());
        final FeedServiceAsync feedService = Registry
                .get(RSSReaderConstants.FEED_SERVICE);
        feedService.saveFeed(feed, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
                Info.display("RSS Reader", "Failed to save feed: "
                        + feed.getTitle());
            }

            @Override
            public void onSuccess(Void result) {
                final ListStore<BeanModel> feedStore = Registry
                        .get(RSSReaderConstants.FEED_STORE);
                BeanModelLookup beanModelLookup = BeanModelLookup.get();
                BeanModelFactory beanModelFactory =
                        beanModelLookup.getFactory(feed.getClass());
                feedStore.add(beanModelFactory.createModel(feed));
                Info.display("RSS Reader", "Feed " +
                        feed.getTitle()
                        + " saved sucessfully");
            }
        });
    }
}
