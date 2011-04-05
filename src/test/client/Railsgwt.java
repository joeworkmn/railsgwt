package test.client;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;


public class Railsgwt implements EntryPoint, ClickHandler
{
	VerticalPanel mainPanel = new VerticalPanel();
	TextArea textarea = new TextArea();
	TextBox firstNameBox = new TextBox();
	TextBox lastNameBox = new TextBox();
	TextBox userNameBox = new TextBox();
	TextBox passWordBox = new TextBox();
	TextBox divisionBox = new TextBox();
	TextBox departmentBox = new TextBox();
	Button okButton = new Button("Submit");
	
	/*
	public void onModuleLoad()
	{
		okButton.addClickHandler(this);
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(textarea);
		textarea.setCharacterWidth(50);
		textarea.setVisibleLines(25);
		VerticalPanel inputPanel = new VerticalPanel();
		Label fnLabel = new Label("First Name:");
		HorizontalPanel row1 = new HorizontalPanel();
		row1.add(fnLabel);
		row1.add(firstNameBox);
		inputPanel.add(row1);
		HorizontalPanel row2 = new HorizontalPanel();
		Label lnLabel = new Label("Last Name:");
		row2.add(lnLabel);
		row2.add(lastNameBox);
		inputPanel.add(row2);
		HorizontalPanel row3 = new HorizontalPanel();
		Label emLabel = new Label("email:");
		row3.add(emLabel);
		row3.add(emailBox);
		inputPanel.add(row3);
		inputPanel.add(okButton);
		mainPanel.add(panel);
		panel.add(inputPanel);
		RootPanel.get().add(mainPanel);
		String url = "http://localhost:3000/users.xml";
		getRequest(url);
	}
	*/
	
	public void onModuleLoad()
	{
		okButton.addClickHandler(this);
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(textarea);
		textarea.setCharacterWidth(50);
		textarea.setVisibleLines(25);
		VerticalPanel inputPanel = new VerticalPanel();
		Label fnLabel = new Label("First Name:");
		HorizontalPanel row1 = new HorizontalPanel();
		row1.add(fnLabel);
		row1.add(firstNameBox);
		inputPanel.add(row1);
		HorizontalPanel row2 = new HorizontalPanel();
		Label lnLabel = new Label("Last Name:");
		row2.add(lnLabel);
		row2.add(lastNameBox);
		inputPanel.add(row2);
		HorizontalPanel row3 = new HorizontalPanel();
		Label unLabel = new Label("username:");
		row3.add(unLabel);
		row3.add(userNameBox);
		inputPanel.add(row3);
		HorizontalPanel row4 = new HorizontalPanel();
		Label pwLabel = new Label("Password:");
		row4.add(pwLabel);
		row4.add(passWordBox);
		inputPanel.add(row4);
		HorizontalPanel row5 = new HorizontalPanel();
		Label divLabel = new Label("Division:");
		row5.add(divLabel);
		row5.add(divisionBox);
		inputPanel.add(row5);
		HorizontalPanel row6 = new HorizontalPanel();
		Label depLabel = new Label("Department:");
		row6.add(depLabel);
		row6.add(departmentBox);
		inputPanel.add(row6);
		inputPanel.add(okButton);
		mainPanel.add(panel);
		panel.add(inputPanel);
		RootPanel.get().add(mainPanel);
		String url = "http://localhost:3000/users/index.xml";
		getRequest(url);
	}
	
	/*
	public void onClick(ClickEvent event)
	{
		Object source = event.getSource();
		if (source == okButton) {
			String encData = URL.encode("first_name") + "=" +
				URL.encode(firstNameBox.getText()) + "&";
			encData += URL.encode("last_name") + "=" +
				URL.encode(lastNameBox.getText()) + "&";
			encData += URL.encode("email") + "=" +
				URL.encode(emailBox.getText()) + ;
		    String url = "http://localhost:3000/users/create";
			postRequest(url,encData);
		}
	}
	*/
	
	public void onClick(ClickEvent event)
	{
		Object source = event.getSource();
		if (source == okButton) {
			String encData = URL.encode("first_name") + "=" +
				URL.encode(firstNameBox.getText()) + "&";
			encData += URL.encode("last_name") + "=" +
				URL.encode(lastNameBox.getText()) + "&";
			encData += URL.encode("username") + "=" +
				URL.encode(userNameBox.getText());
		    encData += URL.encode("pword") + "=" +
				URL.encode(passWordBox.getText());
		    encData += URL.encode("division") + "=" +
				URL.encode(divisionBox.getText());
			encData += URL.encode("department") + "=" +
				URL.encode(departmentBox.getText());
			String url = "http://localhost:3000/users/create";
			postRequest(url,encData);
		}
	}
	
	private void getRequest(String url)
	{
		final RequestBuilder rb =
			new RequestBuilder(RequestBuilder.GET,url);
		try {
			rb.sendRequest(null, new RequestCallback()
			{
				public void onError(final Request request,
					final Throwable exception)
				{
					Window.alert(exception.getMessage());
				}
				public void onResponseReceived(final Request request,
					final Response response)
				{
					textarea.setText(response.getText());
				}
			});
		}
		catch (final Exception e) {
			Window.alert(e.getMessage());
		}
	} // end getRequest
	private void postRequest(String url, String data)
	{
		final RequestBuilder rb =
			new RequestBuilder(RequestBuilder.POST,url);
		rb.setHeader("Content-type",
			"application/x-www-form-urlencoded");
		try {
			rb.sendRequest(data, new RequestCallback()
			{
				public void onError(final Request request,
					final Throwable exception)
				{
					Window.alert(exception.getMessage());
				}
				public void onResponseReceived(final Request request,
					final Response response)
				{
					firstNameBox.setText("");
					lastNameBox.setText("");
					userNameBox.setText("");
					passWordBox.setText("");
					divisionBox.setText("");
					departmentBox.setText("");
					String url1 = "http://localhost:3000/users.xml";
					getRequest(url1);
				}
			});
		}
		catch (final Exception e) {
			Window.alert(e.getMessage());
		}
	} // end postRequst
}