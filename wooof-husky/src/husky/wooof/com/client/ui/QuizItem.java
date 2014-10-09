package husky.wooof.com.client.ui;

import husky.wooof.com.client.dialog.CreateQuiz;
import husky.wooof.com.client.main.WorkspaceMain;
import husky.wooof.com.shared.HuskyQuizItem;
import husky.wooof.com.shared.HuskyQuizMultiplechoice;
import husky.wooof.com.shared.IHuskyConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class QuizItem extends Composite {

	private static QuizItemUiBinder uiBinder = GWT
			.create(QuizItemUiBinder.class);

	interface QuizItemUiBinder extends UiBinder<Widget, QuizItem> {
	}
	
	private String title="Sample Item Question";
	private double points = 1;
	private String name = "mChoice";
	private String choices = "";
	private int choiceIndex = 0;
	private Label lblTitle = new Label();
	private WorkspaceMain workspaceMain;

	@UiField Label lblItemNo, lblAddExplanation,lblItemNext;
	@UiField TextBox txtPoints;
	@UiField HTMLPanel quizPanel, multipleChoicePanel, titlePanel, explanationPanel;
	
	@UiField Image btnAddItem, btnEditTitle, btnDelete;
	@UiField HuskyButton btnNext;
	
	private CreateQuiz createQuiz;
	private HuskyQuizItem quizItem;
	
	public QuizItem(HuskyQuizItem quizItem){
		initWidget(uiBinder.createAndBindUi(this));
		this.quizItem = quizItem;
		lblItemNo.setText(String.valueOf(quizItem.getItemNo()));
		txtPoints.setText(String.valueOf(quizItem.getPoint()));
		titlePanel.add(new HTMLPanel(quizItem.getTitle()));
		btnAddItem.removeFromParent();
		btnEditTitle.removeFromParent();
		txtPoints.setEnabled(false);
		txtPoints.getElement().getStyle().setBackgroundColor("#fff");
		lblAddExplanation.removeFromParent();
		btnDelete.removeFromParent();
		if(quizItem instanceof HuskyQuizMultiplechoice){
			HuskyQuizMultiplechoice multiChoice = (HuskyQuizMultiplechoice)quizItem;
			
			multipleChoicePanel.clear();
			String[] choices = multiChoice.getItems().split(IHuskyConstants.DELIMITER);
			for(String s : choices){
				if(!s.isEmpty()){
					ChoiceItem choice = new ChoiceItem();
					choice.getRbItem().setText("");
					choice.setName(String.valueOf(quizItem.getId()) + quizItem.getItemNo());
					choice.getChoicePanel().clear();
					choice.getChoicePanel().add(new HTMLPanel(s));
					choice.getBtnEditChoice().removeFromParent();
					choice.getBtnDelete().removeFromParent();
					multipleChoicePanel.add(choice);
				}
			}
		}
	}
	
	public QuizItem(HuskyQuizItem quizItem, int itemNo, CreateQuiz createQuiz) {
		initWidget(uiBinder.createAndBindUi(this));
		this.createQuiz = createQuiz;
		this.quizItem = quizItem;
		
		quizItem.setTitle(title);
		quizItem.setPoint(points);
		quizItem.setItemNo(itemNo);
		
		txtPoints.setText(String.valueOf(quizItem.getPoint()));
		lblTitle.setText(quizItem.getTitle());
		lblItemNo.setText(String.valueOf(itemNo));
		titlePanel.add(lblTitle);
		quizPanel.clear();
		btnAddItem.setVisible(false);
		btnNext.removeFromParent();
		
		
		if(quizItem instanceof HuskyQuizMultiplechoice){
			btnAddItem.setVisible(true);
			quizPanel.add(multipleChoicePanel);
			setChoices(itemNo);
		}
		
		
	}
	
	public void setChoices(int itemNo){
		choices = "";
		for(Widget w : multipleChoicePanel){
			if(w instanceof ChoiceItem){
				((ChoiceItem) w).setName(name + String.valueOf(itemNo));
				((ChoiceItem) w).setCom(this);
				String item = ((ChoiceItem) w).getChoicePanel().getElement().toString();
				choices = choices + IHuskyConstants.DELIMITER + item; 
			}
		}
		((HuskyQuizMultiplechoice) getQuizItem()).setItems(choices);
	}
	
	@UiHandler("btnEditTitle")
	void onEditTitle(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Edit your quiz title", new HuskyCkeditor(titlePanel, IHuskyConstants.CK_QUIZ_ITEM_TITLE, this, false),60);
	}
	
	@UiHandler("btnDelete")
	void onDelete(ClickEvent e){
		HuskyActionDialog.show(IHuskyConstants.ACTION_WARN, "Are you sure you want to delete this quiz item?", "Oops wait a second...", this, 40);
	}
	
	@UiHandler("lblAddExplanation")
	void onAddExplanation(ClickEvent e){
		lblAddExplanation.setText("Edit Explanation");
		HuskyActionDialog.show(IHuskyConstants.ACTION_INFO, "", "Add your explanation on this quiz item", new HuskyCkeditor(explanationPanel, IHuskyConstants.CK_QUIZ_ITEM_EXPLANATION, this, false),60);
	}

	@UiHandler("btnAddItem")
	void onAddItem(ClickEvent e){
		ChoiceItem item = new ChoiceItem(((ChoiceItem)multipleChoicePanel.getWidget(0)).rbItem.getName());
		item.setCom(this);
		multipleChoicePanel.add(item);
	}
	
	@UiHandler("txtPoints")
	void onKeyUpPoints(KeyUpEvent e){
		quizItem.setPoint(Double.parseDouble(txtPoints.getText()));
	}
	
	public void deleteQuizItem(){
		this.removeFromParent();
		createQuiz.setQuizNoItems(createQuiz.getQuizNoItems() - 1);
	}

	public HuskyQuizItem getQuizItem() {
		return quizItem;
	}

	public void setQuizItem(HuskyQuizItem quizItem) {
		this.quizItem = quizItem;
	}

	public int getChoiceIndex() {
		return choiceIndex;
	}

	public void setChoiceIndex(int choiceIndex) {
		this.choiceIndex = choiceIndex;
	}

	public HTMLPanel getMultipleChoicePanel() {
		return multipleChoicePanel;
	}

	public void setMultipleChoicePanel(HTMLPanel multipleChoicePanel) {
		this.multipleChoicePanel = multipleChoicePanel;
	}
	
	@UiHandler("btnNext")
	void onNextItem(ClickEvent e){
		workspaceMain.onNextQuizItem();
	}

	public WorkspaceMain getWorkspaceMain() {
		return workspaceMain;
	}

	public void setWorkspaceMain(WorkspaceMain workspaceMain) {
		this.workspaceMain = workspaceMain;
	}
}
