package mat.client.shared;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;


// TODO: Auto-generated Javadoc
/**
 * The Class CQLButtonToolBar.
 */
public class CQLButtonToolBar extends Composite {
	
	/** The Insert button. */
	private Button insertButton = new Button();
	
	/** The save button. */
	private Button saveButton = new Button();
	
	/** The delete button. */
	private Button deleteButton = new Button();
		
	/** The erase button. */
	private Button eraseButton = new Button();
	
	/** The info button. */
	private Button infoButton = new Button();
	
	private Button timingExpIcon = new Button();
	
	/** The button layout. */
	private HorizontalPanel buttonLayout = new HorizontalPanel();
	
	/**
	 * Instantiates a new CQL Button tool bar.
	 */
	public CQLButtonToolBar(String sectionName) {
		
		
		buttonLayout.getElement().setId("cql_buttonLayout_HorizontalPanel");
		buttonLayout.setStylePrimaryName("myAccountButtonLayout continueButton");
		
		insertButton.setType(ButtonType.LINK);
		insertButton.getElement().setId("insertButton_"+sectionName);
		insertButton.setMarginTop(10);
		insertButton.setTitle("Insert");
		insertButton.setIcon(IconType.PLUS_SQUARE);
		insertButton.setIconSize(IconSize.LARGE);
		insertButton.setColor("#0964A2");
		insertButton.setSize("30px", "30px");
		insertButton.getElement().setAttribute("aria-label", "Insert");
		
		saveButton.setType(ButtonType.LINK);
		saveButton.getElement().setId("saveButton_"+sectionName);
		saveButton.setMarginTop(10);
		saveButton.setTitle("Save");
		saveButton.setIcon(IconType.SAVE);
		saveButton.setIconSize(IconSize.LARGE);
		saveButton.setColor("#0964A2");
		saveButton.setSize("30px", "30px");
		saveButton.getElement().setAttribute("aria-label", "Save");
		
		deleteButton.setType(ButtonType.LINK);
		deleteButton.getElement().setId("deleteButton_"+sectionName);
		deleteButton.setMarginTop(10);
		deleteButton.setTitle("Delete");
		// MAT-7737, Use the trash.png image instead of the IconType.TRASH
		Image trash = new Image("images/trash.png");
		deleteButton.getElement().appendChild(trash.getElement());
		deleteButton.setSize("30px", "30px");
		deleteButton.getElement().setAttribute("aria-label", "Delete");
		
		eraseButton.setType(ButtonType.LINK);
		eraseButton.getElement().setId("eraseButton_"+sectionName);
		eraseButton.setMarginTop(10);
		eraseButton.setTitle("Erase");
		eraseButton.setIcon(IconType.ERASER);
		eraseButton.setIconSize(IconSize.LARGE);
		eraseButton.setColor("#0964A2");
		eraseButton.setSize("30px", "30px");
		eraseButton.getElement().setAttribute("aria-label", "Erase");
		
		infoButton.setType(ButtonType.LINK);
		infoButton.getElement().setId("infoButton_"+sectionName);
		infoButton.setMarginTop(10);
		infoButton.setTitle("Information");
		infoButton.setIcon(IconType.INFO_CIRCLE);
		infoButton.setIconSize(IconSize.LARGE);
		infoButton.setColor("#0964A2");
		infoButton.setSize("30px", "30px");
		infoButton.getElement().setAttribute("aria-label", "Information");
		
		timingExpIcon.setType(ButtonType.LINK);
		timingExpIcon.getElement().setId("timingExpButton_"+sectionName);
		timingExpIcon.setMarginTop(10);
		timingExpIcon.setTitle("Timing Expression");
		timingExpIcon.setIcon(IconType.TEXT_WIDTH);
		timingExpIcon.setIconSize(IconSize.LARGE);
		timingExpIcon.setColor("#0964A2");
		timingExpIcon.setSize("30px", "30px");
		timingExpIcon.getElement().setAttribute("aria-label", "Timing Expression");
		
		buttonLayout.add(saveButton);
		buttonLayout.add(eraseButton);
		buttonLayout.add(insertButton);
		buttonLayout.add(timingExpIcon);
		buttonLayout.add(infoButton);
		buttonLayout.add(deleteButton);
		initWidget(buttonLayout);
		
	}
	
	
	/**
	 * Sets the enabled.
	 *
	 * @param isEnabled the new enabled
	 */
	public void setEnabled(boolean isEnabled){
		insertButton.setEnabled(isEnabled);
		saveButton.setEnabled(isEnabled);
		deleteButton.setEnabled(isEnabled);
		eraseButton.setEnabled(isEnabled);
		infoButton.setEnabled(isEnabled);
	}
	
	/**
	 * Gets the insert button.
	 *
	 * @return the insert button
	 */
	public Button getInsertButton() {
		return insertButton;
	}
	/**
	 * Gets the save button.
	 *
	 * @return the save button
	 */
	public Button getSaveButton() {
		return saveButton;
	}
	
	
	/**
	 * Gets the delete button.
	 *
	 * @return the delete button
	 */
	public Button getDeleteButton() {
		return deleteButton;
	}
	
	
	/**
	 * Gets the erase button.
	 *
	 * @return the erase button
	 */
	public Button getEraseButton() {
		return eraseButton;
	}
	
	/**
	 * Gets the info button.
	 *
	 * @return the info button
	 */
	public Button getInfoButton(){
		return infoButton;
	}
	
	public Button getTimingExpButton(){
		return timingExpIcon;
	}
	
	
	/**
	 * Gets the button layout.
	 *
	 * @return the button layout
	 */
	public HorizontalPanel getButtonLayout() {
		return buttonLayout;
	}
	
}
