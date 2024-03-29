package org.dpdouran.gxt.shared.model;

import com.extjs.gxt.ui.client.data.BeanModelTag;

import java.io.Serializable;


public class Feed implements Serializable,BeanModelTag {
    private String description;
    private String link;
    private String title;
    private String uuid;

    public Feed() {
    }

//	public Feed(String uuid)
//	{
//		this.uuid = uuid;
//	}


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}