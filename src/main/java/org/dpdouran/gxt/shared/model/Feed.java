package org.dpdouran.gxt.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelTag;

import java.io.Serializable;



@SuppressWarnings("serial")
public class Feed   extends BaseModel {
	public Feed () {
	}
	public Feed (String uuid) {
		set("uuid", uuid);
	}
	public String getDescription() {
		return get("description");
	}
	public String getLink() {
		return get("link");
	}
	public String getTitle() {
		return get("title");
	}
	public String getUuid() {
		return get("uuid");
	}
	public void setDescription(String description) {
		set("description", description);
	}
	public void setLink(String link) {
		set("link", link);
	}
	public void setTitle(String title) {
		set("title", title);
	}
}