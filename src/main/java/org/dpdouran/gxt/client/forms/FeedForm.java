package org.dpdouran.gxt.client.forms;

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
	}
}
