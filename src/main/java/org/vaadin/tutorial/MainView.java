package org.vaadin.tutorial;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

/**
 * This example project shows how to use VerticalLayout and HorizontalLayout to build a typical responsive application disposition.
 */
@Route("")
@StyleSheet("styles/styles.css")
public class MainView extends VerticalLayout {

	public MainView() {
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("My application");
		Icon help = VaadinIcon.QUESTION_CIRCLE.create();
		HorizontalLayout header = new HorizontalLayout(drawer, title, help);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(createCard(), createCard(), createCard(), createCard());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// FOOTER
		Tab actionButton1 = new Tab(VaadinIcon.HOME.create(), new Span("Home"));
		Tab actionButton2 = new Tab(VaadinIcon.USERS.create(), new Span("Customers"));
		Tab actionButton3 = new Tab(VaadinIcon.PACKAGE.create(), new Span("Products"));
		actionButton1.setClassName("tab");
		actionButton2.setClassName("tab");
		actionButton3.setClassName("tab");
		Tabs buttonBar = new Tabs(actionButton1, actionButton2, actionButton3);
		HorizontalLayout footer = new HorizontalLayout(buttonBar);
		footer.setJustifyContentMode(JustifyContentMode.CENTER);
		footer.setWidth("100%");

		// MENU
		VerticalLayout sideMenu = new VerticalLayout();
		sideMenu.addClassName("sideMenu");
		sideMenu.setHeight("100%");
		sideMenu.setWidth("auto");
		sideMenu.setSpacing(false);
		drawer.getElement().addEventListener("click", ev->sideMenu.getStyle().set("left", "0px"));
		Icon avatar = VaadinIcon.USER.create();
		avatar.setSize("4em");
		sideMenu.add(avatar,
								 new Span("John Doe"),
								 createMenuOption("User profile"),
								 createMenuOption("Configuration"),
								 createMenuOption("About"));
		sideMenu.setAlignItems(Alignment.CENTER);

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(sideMenu, header, workspace, footer);
	}

	private Button createMenuOption(String title) {
		Button m1 = new Button(title);
		m1.setWidth("100%");
		m1.addClickListener(ev -> m1.getElement().getParent().getStyle().set("left", "-1000px"));
		m1.addClickListener(ev -> Notification.show("Button " + title + " clicked."));
		return m1;
	}

	private Component createCard() {
		Span card1Label = new Span("Card");
		FlexLayout card = new FlexLayout(card1Label);
		card.addClassName("card");
		card.setAlignItems(Alignment.CENTER);
		card.setJustifyContentMode(JustifyContentMode.CENTER);
		card.setWidth("100%");
		card.setHeight("200px");
		return card;
	}
}