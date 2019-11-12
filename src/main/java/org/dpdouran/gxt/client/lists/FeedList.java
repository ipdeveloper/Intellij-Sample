package org.dpdouran.gxt.client.lists;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import org.dpdouran.gxt.client.RSSReaderConstants;

public class FeedList  extends LayoutContainer {
    public FeedList() {
        setLayout(new FitLayout());
    }
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        final ListField<BeanModel> feedList = new
                ListField<BeanModel>();
        final ListStore<BeanModel> feedStore =
                Registry.get(RSSReaderConstants.FEED_STORE);
        feedList.setStore(feedStore);
        feedList.setDisplayField("title");
        add(feedList);
    }

}
