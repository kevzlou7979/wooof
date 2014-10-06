package husky.wooof.com.client.dialog;

import husky.wooof.com.client.services.QuizService;
import husky.wooof.com.client.ui.HuskyActionDialog;
import husky.wooof.com.client.ui.HuskyCkeditor;
import husky.wooof.com.client.ui.HuskyTitle;
import husky.wooof.com.client.ui.QuizItem;
import husky.wooof.com.shared.HuskyCard;
import husky.wooof.com.shared.HuskyQuiz;
import husky.wooof.com.shared.HuskyQuizItem;
import husky.wooof.com.shared.HuskyQuizMultiplechoice;
import husky.wooof.com.shared.IHuskyConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CreateQuiz extends Composite {

	private static CreateQuizUiBinder uiBinder = GWT
			.create(CreateQuizUiBinder.class);

	interface CreateQuizUiBinder extends UiBinder<Widget, CreateQuiz> {
	}

	@UiField HTMLPanel quizPanel, titlePanel, descriptionPanel;
	@UiField HuskyTitle menuQuizMultiChoice, menuQuizTrueFalse, menuQuizDef, menuQuizMatching, menuQuizEssay, menuQuizCheckBox;
	private int quizNoItems = 1;
	private HuskyCard card;
	private List<HuskyQuizItem> items = new ArrayList<HuskyQuizItem>();

	public CreateQuiz() {
		initWidget(uiBinder.createAndBindUi(this));
		registerMenuHandler();
		titlePanel.add(new Label("Quiz Title"));
		descriptionPanel.add(new Label("Quiz Description"));
	}
	
	private void registerMenuHandler() {
		menuQuizMultiChoice.getPanel().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				quizPanel.add(new QuizItem(new HuskyQuizMultiplechoice(), quizNoItems, CreateQuiz.this));
				quizNoItems++;
			}
		});
	}

	public int getQuizNoItems() {
		return quizNoItems;
	}

	public void setQuizNoItems(int quizNoItems) {
		this.quizNoItems = quizNoItems;
	}
	
	@UiHandler("imgEditTitle")
	void onEditTitle(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your quiz title", new HuskyCkeditor(titlePanel),60);
	}
	
	@UiHandler("imgEditDescription")
	void onEditDescription(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your quiz description", new HuskyCkeditor(descriptionPanel),60);
	}
	
	@UiHandler("btnSave")
	void onSave(ClickEvent e){
		
		for(Widget w : quizPanel){
			if(w instanceof QuizItem){
				items.add(((QuizItem) w).getQuizItem());
			}
		}
		
		QuizService.Connect.getService().saveQuiz(new HuskyQuiz(card.getId(), titlePanel.getElement().toString(), descriptionPanel.getElement().toString(), 0, new Date()), items, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	public HuskyCard getCard() {
		return card;
	}

	public void setCard(HuskyCard card) {
		this.card = card;
	}	
	
	
	
}
