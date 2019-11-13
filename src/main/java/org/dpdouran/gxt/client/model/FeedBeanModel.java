package org.dpdouran.gxt.client.model;


import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BeanModelMarker;
import org.dpdouran.gxt.shared.model.Feed;
@BeanModelMarker.BEAN(Feed.class)
public class FeedBeanModel extends BaseModelData {

    public final static String key_key = "Key";
    public final static String Display_key = "Display";

    private Feed feed;

    public FeedBeanModel() {

    }

    public FeedBeanModel(Feed feed) {
        set(key_key, feed);
        set(Display_key, feed.getTitle());
    }
}