package mat.client.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mat.DTO.OperatorDTO;
import mat.client.Enableable;
import mat.client.admin.service.AdminService;
import mat.client.admin.service.AdminServiceAsync;
import mat.client.audit.service.AuditService;
import mat.client.audit.service.AuditServiceAsync;
import mat.client.clause.QDMAppliedSelectionView;
import mat.client.clause.QDMAvailableValueSetWidget;
import mat.client.clause.QDSAppliedListView;
import mat.client.clause.QDSAttributesService;
import mat.client.clause.QDSAttributesServiceAsync;
import mat.client.clause.QDSCodeListSearchView;
import mat.client.codelist.AdminManageCodeListSearchModel;
import mat.client.codelist.HasListBox;
import mat.client.codelist.ListBoxCodeProvider;
import mat.client.codelist.service.CodeListService;
import mat.client.codelist.service.CodeListServiceAsync;
import mat.client.event.ForgottenPasswordEvent;
import mat.client.event.MeasureSelectedEvent;
import mat.client.login.LoginModel;
import mat.client.login.service.LoginResult;
import mat.client.login.service.LoginService;
import mat.client.login.service.LoginServiceAsync;
import mat.client.login.service.SessionManagementService;
import mat.client.login.service.SessionManagementServiceAsync;
import mat.client.measure.ManageMeasureSearchModel;
import mat.client.measure.ManageMeasureSearchView;
import mat.client.measure.service.MeasureService;
import mat.client.measure.service.MeasureServiceAsync;
import mat.client.measurepackage.service.PackageService;
import mat.client.measurepackage.service.PackageServiceAsync;
import mat.client.myAccount.service.MyAccountService;
import mat.client.myAccount.service.MyAccountServiceAsync;
import mat.client.umls.service.VSACAPIService;
import mat.client.umls.service.VSACAPIServiceAsync;
import mat.client.umls.service.VsacApiResult;
import mat.client.util.ClientConstants;
import mat.model.GlobalCopyPasteObject;
import mat.model.VSACExpansionIdentifier;
import mat.model.cql.CQLKeywords;
import mat.shared.ConstantMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
// TODO: Auto-generated Javadoc
//import mat.client.measure.AdminManageMeasureSearchView;

// TODO: Auto-generated Javadoc
/**
 * The Class MatContext.
 */
public class MatContext implements IsSerializable {
	
	/** The is umls logged in. */
	private boolean isUMLSLoggedIn = false;
	
	/** The do measure lock updates. */
	private boolean doMeasureLockUpdates = false;
	
	/** The do user lock updates. */
	private boolean doUserLockUpdates = false;
	// how often to perform lock time updates
	/** The lock update time. */
	private final int lockUpdateTime = 2*60*1000;
	
	/** The user lock update time. */
	private final int userLockUpdateTime = 2*60*1000;
	
	/** The Constant PLEASE_SELECT. */
	public static final String PLEASE_SELECT = "--Select--";
	
	/** The cql keywords. */
	public CQLKeywords cqlKeywords = new CQLKeywords();
	
	/** The instance. */
	private static MatContext instance = new MatContext();
	
	/** The current module. */
	private String currentModule;
	
	/** The login service. */
	private LoginServiceAsync loginService;
	
	/** The measure service. */
	private MeasureServiceAsync measureService;
	
	/** The measure package service. */
	private PackageServiceAsync measurePackageService;
	
	/** The session service. */
	private SessionManagementServiceAsync sessionService;
	
	/** The admin service. */
	private AdminServiceAsync adminService;
	
	/** The my account service. */
	private MyAccountServiceAsync myAccountService;
	
	/** The code list service. */
	private CodeListServiceAsync codeListService;
	
	/** The vsacapi service async. */
	private VSACAPIServiceAsync vsacapiServiceAsync;
	
	/** The qds attributes service async. */
	private QDSAttributesServiceAsync qdsAttributesServiceAsync;
	
	/** The event bus. */
	private HandlerManager eventBus;
	
	/** The timeout manager. */
	private TimeoutManager timeoutManager;
	
	/** The current measure info. */
	private MeasureSelectedEvent currentMeasureInfo;
	
	/** The is measure deleted. */
	private boolean isMeasureDeleted;
	
	/** The list box code provider. */
	private ListBoxCodeProvider listBoxCodeProvider;
	
	/** The audit service. */
	private AuditServiceAsync auditService;
	
	/** The user id. */
	private String userId;
	
	/** The user email. */
	private String userEmail;
	
	/** The login id. */
	private String loginId;
	
	/** The user role. */
	private String userRole;
	
	
	/** The zoom factor service. */
	private ZoomFactorService zoomFactorService = new ZoomFactorService();
	
	
	/** The qds view. */
	private QDSCodeListSearchView qdsView;
	
	/** The vsac profile view. */
	private QDMAppliedSelectionView qdmAppliedSelectionView;
	
	/** The modify qdm pop up widget. */
	private QDMAvailableValueSetWidget modifyQDMPopUpWidget;
	
	/** The manage measure search view. */
	private ManageMeasureSearchView manageMeasureSearchView;
	
	/** The admin manage measure search view. */
	//private AdminManageMeasureSearchView adminManageMeasureSearchView;
	
	/** The manage measure search model. */
	private ManageMeasureSearchModel manageMeasureSearchModel;
	
	/** The manage code list search view. */
	//private ManageCodeListSearchView manageCodeListSearchView;
	
	/** The manage code list search model. */
	private AdminManageCodeListSearchModel manageCodeListSearchModel;
	
	
	/** The synchronization delegate. */
	private SynchronizationDelegate synchronizationDelegate = new SynchronizationDelegate();
	
	/** The error tab index. */
	private int errorTabIndex;
	
	/** The is error tab. */
	private boolean isErrorTab;
	
	/** The timings. */
	public List<String> timings = new ArrayList<String>();
	
	/** The functions. */
	public List<String> functions = new ArrayList<String>();
	
	/** The relationships. */
	public List<String> relationships = new ArrayList<String>();
	
	/** The comparison ops. */
	public List<String> comparisonOps = new ArrayList<String>();
	
	/** The logical ops. */
	public List<String> logicalOps = new ArrayList<String>();
	
	/** The set ops. */
	public List<String> setOps = new ArrayList<String>();
	
	/** The operator map key short. */
	public Map<String, String> operatorMapKeyShort = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
	
	/** The operator map key long. */
	public Map<String, String> operatorMapKeyLong = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
	
	/** The removed relationship types. */
	public Map<String, String> removedRelationshipTypes = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
	
	/** The data type list. */
	private List<String> dataTypeList = new ArrayList<String>();
		
	/** The profile list. */
	private List<String> expIdentifierList = new ArrayList<String>();
	
	/** The vsac exp identifier list. */
	private List<VSACExpansionIdentifier> vsacExpIdentifierList = new ArrayList<VSACExpansionIdentifier>();
	
	/** The global copy paste. */
	private GlobalCopyPasteObject globalCopyPaste;
	
	
	/** The definitions. */
	public List<String> definitions = new ArrayList<String>(); 
	
	/** The parameters. */
	public List<String> parameters = new ArrayList<String>();
	
	/** The funcs. */
	public List<String> funcs = new ArrayList<String>();
	
	/** The all attribute list. */
	public List<String> allAttributeList = new ArrayList<String>();
	
	public List<String> allUnitsList = new ArrayList<String>();
	
	private List<String> allCQLUnitsList = new ArrayList<String>();
	
	
	//private GlobalCopyPaste copyPaste;
	
	/*
	 * POC Global Copy Paste.
	 * public CellTreeNode copiedNode;*/
	/**
	 * Clear dvi messages.
	 */
	public void clearDVIMessages(){
		if(qdsView !=null){
			qdsView.getSuccessMessageDisplay().clear();
			qdsView.getErrorMessageDisplay().clear();
		}
	}
	
	
	/**
	 * Clear modify pop up messages.
	 */
	public void clearModifyPopUpMessages(){
		if(modifyQDMPopUpWidget !=null){
			modifyQDMPopUpWidget.getApplyToMeasureSuccessMsg().clear();
			modifyQDMPopUpWidget.getErrorMessageDisplay().clear();
			modifyQDMPopUpWidget.getSuccessMessagePanel().clear();
			modifyQDMPopUpWidget.getErrorMessagePanel().clear();
		}
	}
	
	/**
	 * Gets the admin manage measure search view.
	 *
	 * @param view the new QDS view
	 * @return the admin manage measure search view
	 */
	/*public AdminManageMeasureSearchView getAdminManageMeasureSearchView() {
		return adminManageMeasureSearchView;
	}*/
	
	/**
	 * Sets the admin manage measure search view.
	 * 
	 * @param view
	 *            the new admin manage measure search view
	 */
	/*public void setAdminManageMeasureSearchView(AdminManageMeasureSearchView view){
		adminManageMeasureSearchView=view;
	}*/
	
	/**
	 * Sets the qDS view.
	 * 
	 * @param view
	 *            the new qDS view
	 */
	public void setQDSView(QDSCodeListSearchView view){
		qdsView=view;
	}
	
	/**
	 * Sets the VSAC profile view.
	 *
	 * @param view the new VSAC profile view
	 */
	public void setQDMAppliedSelectionView(QDMAppliedSelectionView view){
		qdmAppliedSelectionView = view;
	}
	
	/**
	 * Sets the qds applied list view.
	 * 
	 * @param qdsAppliedListView
	 *            the new qds applied list view
	 */
	public void setQdsAppliedListView(QDSAppliedListView qdsAppliedListView) {
	}
	
	//register the Value Set search messages
	//register the property editor messages
	
	/**
	 * Sets the error message1.
	 * 
	 * @param msg
	 *            the new error message1
	 */
	public void setErrorMessage1(ErrorMessageDisplay msg){
	}
	
	
	/**
	 * Gets the list box code provider.
	 * 
	 * @return the list box code provider
	 */
	public ListBoxCodeProvider getListBoxCodeProvider() {
		return listBoxCodeProvider;
	}
	
	/**
	 * Sets the list box code provider.
	 * 
	 * @param listBoxCodeProvider
	 *            the new list box code provider
	 */
	public void setListBoxCodeProvider(ListBoxCodeProvider listBoxCodeProvider) {
		this.listBoxCodeProvider = listBoxCodeProvider;
	}
	
	/**
	 * Sets the user info.
	 * 
	 * @param userId
	 *            the user id
	 * @param userEmail
	 *            the user email
	 * @param userRole
	 *            the user role
	 * @param loginId
	 *            the login id
	 */
	public void setUserInfo(String userId, String userEmail, String userRole,String loginId) {
		this.userId = userId;
		this.userEmail = userEmail;
		this.userRole = userRole;
		this.loginId=loginId;
		//setUserSignInDate(userId);
	}
	
	/**
	 * Instantiates a new mat context.
	 */
	protected MatContext(){
		
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			
			@Override
			public void onUncaughtException(Throwable e) {
				GWT.log("An uncaught Exception Occured",e);
				MatContext.this.logException("Uncaught Client Exception",e);
			}
		});
		eventBus = new HandlerManager(null);
		
		eventBus.addHandler(MeasureSelectedEvent.TYPE, new MeasureSelectedEvent.Handler() {
			
			@Override
			public void onMeasureSelected(MeasureSelectedEvent event) {
				currentMeasureInfo = event;
			}
		});
		
		//US 439. Start the timeout timer when the user clicked the forgotten password link
		eventBus.addHandler(ForgottenPasswordEvent.TYPE, new ForgottenPasswordEvent.Handler(){
			@Override
			public void onForgottenPassword(ForgottenPasswordEvent event) {
				getTimeoutManager().startActivityTimers(ConstantMessages.LOGIN_MODULE);
			}
		});
	}
	
	/**
	 * Gets the event bus.
	 * 
	 * @return the event bus
	 */
	public HandlerManager getEventBus() {
		return eventBus;
	}
	
	/**
	 * Log exception.
	 * 
	 * @param message
	 *            the message
	 * @param t
	 *            the t
	 */
	public void logException(String message, Throwable t) {
		StackTraceElement[] elementArr = t.getStackTrace();
		for (StackTraceElement element : elementArr) {
			element.toString().concat("\r\n");
		}
	}
	
	/**
	 * Gets the my account service.
	 * 
	 * @return the my account service
	 */
	public MyAccountServiceAsync getMyAccountService(){
		if(myAccountService == null){
			myAccountService= (MyAccountServiceAsync) GWT.create(MyAccountService.class);
		}
		return myAccountService;
	}
	
	/**
	 * Gets the code list service.
	 * 
	 * @return the code list service
	 */
	public CodeListServiceAsync getCodeListService(){
		if(codeListService == null){
			codeListService= (CodeListServiceAsync) GWT.create(CodeListService.class);
		}
		return codeListService;
	}
	
	/**
	 * Gets the admin service.
	 * 
	 * @return the admin service
	 */
	public AdminServiceAsync getAdminService(){
		if(adminService == null){
			adminService = (AdminServiceAsync) GWT.create(AdminService.class);
		}
		return adminService;
	}
	
	/**
	 * Gets the login service.
	 * 
	 * @return the login service
	 */
	public LoginServiceAsync getLoginService(){
		if(loginService == null){
			loginService = (LoginServiceAsync) GWT.create(LoginService.class);
			/*ServiceDefTarget target = (ServiceDefTarget) loginService;
			RpcRequestBuilder reqBuilder = new RpcRequestBuilder() {
				@Override
				protected RequestBuilder doCreate(String serviceEntryPoint) {
					RequestBuilder rb = super.doCreate(serviceEntryPoint);
					rb.setHeader("HEADER_SIGNATURE", "your token");
					return rb;
				}
				@Override
				protected void doSetCallback(final RequestBuilder rb, final RequestCallback callback) {
					// TODO Auto-generated method stub
					super.doSetCallback(rb, new RequestCallback() {
						
						@Override
						public void onResponseReceived(Request request, Response response) {
							// TODO Auto-generated method stub
							String headerValue = response.getHeader("Set-Cookie");
							Window.alert(headerValue);
							rb.setHeader("Set-Cookie", "Secure;HttpOnly");
							Window.alert("new header :"+rb.getHeader("Set-Cookie"));
							// do sth...
							callback.onResponseReceived(request, response);
						}
						
						@Override
						public void onError(Request request, Throwable exception) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			};
			target.setRpcRequestBuilder(reqBuilder);*/
		}
		return loginService;
	}
	
	/**
	 * Gets the vsacapi service async.
	 * 
	 * @return the vsacapi service async
	 */
	public VSACAPIServiceAsync getVsacapiServiceAsync() {
		if(vsacapiServiceAsync == null){
			vsacapiServiceAsync = (VSACAPIServiceAsync) GWT.create(VSACAPIService.class);
		}
		return vsacapiServiceAsync;
	}
	
	
	/**
	 * Sets the vsacapi service async.
	 * 
	 * @param vsacapiServiceAsync
	 *            the new vsacapi service async
	 */
	public void setVsacapiServiceAsync(VSACAPIServiceAsync vsacapiServiceAsync) {
		this.vsacapiServiceAsync = vsacapiServiceAsync;
	}
	
	
	/**
	 * Gets the session service.
	 * 
	 * @return the session service
	 */
	private SessionManagementServiceAsync getSessionService(){
		if(sessionService == null){
			sessionService = (SessionManagementServiceAsync) GWT.create(SessionManagementService.class);
		}
		return sessionService;
	}
	
	/**
	 * Gets the measure service.
	 * 
	 * @return the measure service
	 */
	public MeasureServiceAsync getMeasureService(){
		if(measureService == null){
			measureService = (MeasureServiceAsync) GWT.create(MeasureService.class);
		}
		return measureService;
	}
	
	/**
	 * Gets the audit service.
	 * 
	 * @return the audit service
	 */
	public AuditServiceAsync getAuditService(){
		if(auditService == null){
			auditService = (AuditServiceAsync) GWT.create(AuditService.class);
		}
		return auditService;
	}
	
	
	/**
	 * Gets the package service.
	 * 
	 * @return the package service
	 */
	public PackageServiceAsync getPackageService(){
		if(measurePackageService == null){
			measurePackageService = (PackageServiceAsync) GWT.create(PackageService.class);
		}
		return measurePackageService;
	}
	
	/**
	 * Gets the.
	 * 
	 * @return the mat context
	 */
	public static MatContext get(){
		return instance;
	}
	
	/**
	 * Gets the logged in user role.
	 * 
	 * @return the logged in user role
	 */
	public String getLoggedInUserRole() {
		return userRole;
	}
	
	/**
	 * Gets the loggedin user id.
	 * 
	 * @return the loggedin user id
	 */
	public String getLoggedinUserId(){
		return userId;
	}
	
	/**
	 * Gets the loggedin login id.
	 * 
	 * @return the loggedin login id
	 */
	public String getLoggedinLoginId(){
		return loginId;
	}
	
	/**
	 * Gets the logged in user email.
	 * 
	 * @return the logged in user email
	 */
	public String getLoggedInUserEmail() {
		return userEmail;
	}
	
	/**
	 * Change password security questions.
	 * 
	 * @param model
	 *            the model
	 * @param asyncCallback
	 *            the async callback
	 */
	public void changePasswordSecurityQuestions(LoginModel model, AsyncCallback<LoginResult> asyncCallback) {
		getLoginService().changePasswordSecurityAnswers(model, asyncCallback);
	}
	
	/**
	 * Checks if is valid user.
	 *
	 * @param username            the username
	 * @param Password            the password
	 * @param oneTimePassword the one time password
	 * @param callback            the callback
	 */
	public void isValidUser(String username, String Password, String oneTimePassword, AsyncCallback<LoginModel> callback){
		getLoginService().isValidUser(username, Password, oneTimePassword, callback);
	}
	
	/**
	 * Gets the list box data.
	 * 
	 * @param listBoxCallback
	 *            the list box callback
	 * @return the list box data
	 */
	public void getListBoxData(AsyncCallback<CodeListService.ListBoxData> listBoxCallback){
		getCodeListService().getListBoxData(listBoxCallback);
	}
	
	/**
	 * Gets the current user role.
	 * 
	 * @param userRoleCallback
	 *            the user role callback
	 * @return the current user role
	 */
	public void getCurrentUserRole(AsyncCallback<SessionManagementService.Result> userRoleCallback){
		getSessionService().getCurrentUserRole(userRoleCallback);
	}
	
	
	/**
	 * Restart timeout warning.
	 */
	public void restartTimeoutWarning() {
		getTimeoutManager().startActivityTimers(ConstantMessages.MAT_MODULE);
	}
	
	/**
	 * Restart umls signout.
	 */
	public void restartUMLSSignout() {
		getTimeoutManager().startUMLSTimer();
	}
	
	/**
	 * Gets the current measure id.
	 * 
	 * @return the current measure id
	 */
	public String getCurrentMeasureId() {
		if(currentMeasureInfo != null) {
			return currentMeasureInfo.getMeasureId();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Gets the current measure name.
	 * 
	 * @return the current measure name
	 */
	public String getCurrentMeasureName() {
		if(currentMeasureInfo != null) {
			return currentMeasureInfo.getMeasureName();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Gets the current measure version.
	 * 
	 * @return the current measure version
	 */
	public String getCurrentMeasureVersion() {
		if(currentMeasureInfo != null) {
			return currentMeasureInfo.getMeasureVersion();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Sets the current measure version.
	 * 
	 * @param s
	 *            the new current measure version
	 */
	public void setCurrentMeasureVersion(String s) {
		if(currentMeasureInfo != null) {
			currentMeasureInfo.setMeasureVersion(s);
		}
	}
	
	
	/**
	 * Sets the current measure scoring type.
	 * 
	 * @param s
	 *            the new current measure scoring type
	 */
	public void setCurrentMeasureScoringType(String s){
		
		if(currentMeasureInfo!=null) {
			currentMeasureInfo.setScoringType(s);
		}
	}
	
	/**
	 * Sets the current module.
	 * 
	 * @param moduleName
	 *            the new current module
	 */
	public void setCurrentModule(String moduleName){
		currentModule = moduleName;
	}
	
	/**
	 * Gets the current module.
	 * 
	 * @return the current module
	 */
	public String getCurrentModule(){
		return currentModule;
	}
	
	/**
	 * Gets the current measure scoring type.
	 * 
	 * @return the current measure scoring type
	 */
	public String getCurrentMeasureScoringType(){
		if(currentMeasureInfo != null){
			return currentMeasureInfo.getScoringType();
		}
		else{
			return "";
		}
	}
	
	/**
	 * Sets the current measure name.
	 * 
	 * @param measureName
	 *            the new current measure name
	 */
	public void setCurrentMeasureName(String measureName) {
		if(currentMeasureInfo != null) {
			currentMeasureInfo.setMeasureName(measureName);
		}
	}
	
	/**
	 * Gets the current short name.
	 * 
	 * @return the current short name
	 */
	public String getCurrentShortName() {
		if(currentMeasureInfo != null) {
			return currentMeasureInfo.getShortName();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Sets the current short name.
	 * 
	 * @param shortName
	 *            the new current short name
	 */
	public void setCurrentShortName(String shortName) {
		if(currentMeasureInfo != null) {
			currentMeasureInfo.setShortName(shortName);
		}
	}
	
	/**
	 * Checks if is current measure editable.
	 * 
	 * @return true, if is current measure editable
	 */
	public boolean isCurrentMeasureEditable() {
		if(currentMeasureInfo != null) {
			return currentMeasureInfo.isEditable();
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks if is current measure locked.
	 * 
	 * @return true, if is current measure locked
	 */
	public boolean isCurrentMeasureLocked(){
		if(currentMeasureInfo != null) {
			return currentMeasureInfo.isLocked();
		}
		else {
			return false;
		}
	}
	
	/**
	 * Renew session.
	 */
	public void renewSession() {
		getSessionService().renewSession(new AsyncCallback<Void>() {
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Error renewing session " + arg0.getMessage());
			}
			
			@Override
			public void onSuccess(Void arg0) {
			}
			
		});
	}
	
	/**
	 * Redirect to html page.
	 * 
	 * @param html
	 *            the html
	 */
	public void redirectToHtmlPage(String html) {
		UrlBuilder urlBuilder = Window.Location.createUrlBuilder();
		String path = Window.Location.getPath();
		path=path.substring(0, path.lastIndexOf('/'));
		path += html;
		urlBuilder.setPath(path);
		Window.Location.replace(urlBuilder.buildString());
	}
	
	/**
	 * Open url.
	 * 
	 * @param html
	 *            the html
	 */
	public void openURL(String html){
		Window.open(html, "User_Guide", "");
		
	}
	
	
	/**
	 * Open new html page.
	 * 
	 * @param html
	 *            the html
	 */
	public void openNewHtmlPage(String html) {
		String windowFeatures = "toolbar=no, location=no, personalbar=no, menubar=yes, scrollbars=yes, resizable=yes";
		UrlBuilder urlBuilder = Window.Location.createUrlBuilder();
		String path = Window.Location.getPath();
		path=path.substring(0, path.lastIndexOf('/'));
		path += html;
		urlBuilder.setPath(path);
		//		Window.open(urlBuilder.buildString(),"_self",windowFeatures);
		Window.open(urlBuilder.buildString(),"_blank",windowFeatures);
	}
	
	/**
	 * Sets the aria hidden.
	 * 
	 * @param widget
	 *            the widget
	 * @param value
	 *            the value
	 */
	public void setAriaHidden(Widget widget, Boolean value){
		setAriaHidden(widget, value.toString());
	}
	
	/**
	 * Sets the aria hidden.
	 * 
	 * @param widget
	 *            the widget
	 * @param value
	 *            the value
	 */
	public void setAriaHidden(Widget widget, String value){
		widget.getElement().setAttribute("aria-hidden", value);
	}
	/**
	 * This method is the hub for dynamic visibility for The app.
	 * The setting of aria attributes or styles is delegated to a shared location
	 * and that logic is invoked where needed.
	 * 
	 * If we need another way of making objects "invisible" then we need to modify
	 * widget.setVisible(visible);
	 * 
	 * @param widget The widget to be rendered or no longer rendered
	 * @param visible Widget's rendering status
	 */
	public void setVisible(Widget widget, Boolean visible){
		// TODO likely we will change this invocation
		widget.setVisible(visible);
		// disable the widget, maybe best to check if this is a FocusWidget and make an explicit setEnabled call
		DOM.setElementPropertyBoolean(widget.getElement(), "disabled", !visible);
		//NOTE: if (visible = true) then (ARIA not hidden)
		setAriaHidden(widget, !visible);
	}
	
	/** The enable registry. */
	public HashMap enableRegistry = new HashMap<String, Enableable>();
	
	/** The tab registry. */
	public HashMap tabRegistry = new HashMap<String, TabPanel>();
	
	/**
	 * Gets the zoom factor service.
	 * 
	 * @return the zoom factor service
	 */
	public ZoomFactorService getZoomFactorService(){
		return zoomFactorService;
	}
	
	/**
	 * Gets the current measure info.
	 * 
	 * @return the current measure info
	 */
	public MeasureSelectedEvent getCurrentMeasureInfo(){
		return currentMeasureInfo;
	}
	
	/**
	 * Sets the current measure info.
	 * 
	 * @param evt
	 *            the new current measure info
	 */
	public void setCurrentMeasureInfo(MeasureSelectedEvent evt){
		currentMeasureInfo = evt;
	}
	
	/*
	 * MeasureLock Service --- contains logic to set and release the lock.
	 *
	 */
	/** The measure lock service. */
	private MeasureLockService measureLockService = new MeasureLockService();
	
	/**
	 * Gets the measure lock service.
	 * 
	 * @return the measure lock service
	 */
	public MeasureLockService getMeasureLockService(){
		return measureLockService;
	}
	
	/*
	 * Loading queue
	 * used to track loading behavior in the MAT
	 *
	 * add operation on MainLayout.showLoadingMessage
	 * poll operation on MainLayout.hideLoadingMessage
	 *
	 * when the queue is empty, then loading is done
	 * NOTE a queue is required because >= 1 add operations could be invoked before a subsequent poll
	 */
	/** The loading queue. */
	private MATQueue loadingQueue = new MATQueue();
	
	/**
	 * Gets the loading queue.
	 * 
	 * @return the loading queue
	 */
	public MATQueue getLoadingQueue(){
		return loadingQueue;
	}
	
	/**
	 * Checks if is loading.
	 * 
	 * @return true, if is loading
	 */
	public boolean isLoading(){
		return !getLoadingQueue().isEmpty();
	}
	
	/*
	 * Message store to prevent duplicated messages
	 */
	/** The message delegate. */
	private MessageDelegate messageDelegate = new MessageDelegate();
	
	/**
	 * Gets the message delegate.
	 * 
	 * @return the message delegate
	 */
	public MessageDelegate getMessageDelegate(){
		return messageDelegate;
	}
	
	/**
	 * Fire loading alert.
	 */
	public void fireLoadingAlert(){
		Window.alert(MatContext.get().getMessageDelegate().getAlertLoadingMessage());
	}
	
	/**
	 * Gets the timeout manager.
	 * 
	 * @return the timeout manager
	 */
	private TimeoutManager getTimeoutManager(){
		if (timeoutManager == null){
			timeoutManager = new TimeoutManager();
		}
		return timeoutManager;
	}
	
	/**
	 * run a repeating process that updates the current measure lock while flag doMeasureLockUpdates returns true.
	 */
	public void startMeasureLockUpdate(){
		if (!doMeasureLockUpdates) {
			doMeasureLockUpdates = true;
			Timer t = new Timer() {
				@Override
				public void run() {
					if (doMeasureLockUpdates) {
						getMeasureLockService().setMeasureLock();
					} else {
						//terminate job
						this.cancel();
					}
					
				}
			};
			t.scheduleRepeating(lockUpdateTime);
		}
	}
	/**
	 * set flag doMeasureLockUpdates to false the repeating process will verify based on its value.
	 */
	public void stopMeasureLockUpdate(){
		doMeasureLockUpdates = false;
	}
	
	/**
	 * run a repeating process that updates the current measure lock while flag doMeasureLockUpdates returns true.
	 */
	public void startUserLockUpdate(){
		if (!doUserLockUpdates) {
			doUserLockUpdates = true;
			Timer t = new Timer() {
				@Override
				public void run() {
					if (doUserLockUpdates) {
						setUserSignInDate(getLoggedinUserId());
					} else {
						//terminate job
						this.cancel();
					}
					
				}
			};
			t.scheduleRepeating(userLockUpdateTime);
		}
	}
	
	/**
	 * Sets the user sign in date.
	 * 
	 * @param userid
	 *            the new user sign in date
	 */
	public void setUserSignInDate(String userid) {
		if (userid != null) {
			getMyAccountService().setUserSignInDate(userid, new AsyncCallback<Void>() {
				@Override
				public void onFailure(final Throwable caught) { }
				@Override
				public void onSuccess(final Void result) { }
			});
		}
	}
	
	/**
	 * set flag doUserLockUpdates to false the repeating process will verify
	 * based on its value.
	 */
	public void stopUserLockUpdate() {
		doUserLockUpdates = false;
		String userid = getLoggedinUserId();
		if (userid != null) {
			getMyAccountService().setUserSignOutDate(userid, new AsyncCallback<Void>() {
				@Override
				public void onFailure(final Throwable caught) { }
				@Override
				public void onSuccess(final Void result) { }
			});
		}
	}
	
	/*
	 * assuming text is of form *:<<category>>-<<oid>>
	 * where * could contain a -
	 * return text without -<<oid>>
	 */
	/**
	 * Gets the text sans oid.
	 * 
	 * @param text
	 *            the text
	 * @return the text sans oid
	 */
	public String getTextSansOid(String text) {
		if (text == null) {
			return text;
		}
		int d = text.lastIndexOf('-');
		int c = text.lastIndexOf(':');
		if ((d > 0) && (d > c)) {
			return text.substring(0, d);
		} else {
			return text;
		}
	}
	
	/**
	 * Strip off oid.
	 * 
	 * @param item
	 *            the item
	 * @return the string
	 */
	public String stripOffOID(String item) {
		int idx = item.lastIndexOf('-');
		if(idx < 0){
			return item;
		}
		return item.substring(0,idx).trim();
	}
	
	/**
	 * Record transaction event.
	 * 
	 * @param primaryId
	 *            the primary id
	 * @param secondaryId
	 *            the secondary id
	 * @param activityType
	 *            the activity type
	 * @param additionalInfo
	 *            the additional info
	 * @param logLevel
	 *            the log level
	 */
	public void recordTransactionEvent(String primaryId, String secondaryId, String activityType, String additionalInfo, int logLevel){
		String userId = getLoggedinUserId();
		String userEmail = "["+getLoggedInUserEmail()+"] ";
		getAuditService().recordTransactionEvent(primaryId, secondaryId, activityType, userId,
				userEmail + additionalInfo, logLevel, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(final Throwable caught) { }
			@Override
			public void onSuccess(final Boolean result) { }
		});
	}
	
	
	//recording the User Events
	/**
	 * Record user event.
	 *
	 * @param userId the user id
	 * @param event the event
	 * @param additionalInfo the additional info
	 * @param isChildLogRequired the is child log required
	 */
	public void recordUserEvent(String userId, List<String> event, String additionalInfo, boolean isChildLogRequired) {
		MatContext.get()
		.getAuditService().recordUserEvent(userId, event, additionalInfo, isChildLogRequired, new AsyncCallback<Boolean>() {
			
			@Override
			public void onFailure(Throwable caught) {
			}
			
			@Override
			public void onSuccess(Boolean result) {
			}
		});
	}
	
	/**
	 * Gets the synchronization delegate.
	 * 
	 * @return the synchronization delegate
	 */
	public SynchronizationDelegate getSynchronizationDelegate() {
		return synchronizationDelegate;
	}
	
	/**
	 * Checks if is already signed in.
	 * 
	 * @param lastSignOut
	 *            the last sign out
	 * @param lastSignIn
	 *            the last sign in
	 * @param current
	 *            the current
	 * @return true, if is already signed in
	 */
	public boolean isAlreadySignedIn(Date lastSignOut, Date lastSignIn, Date current){
		/*
		 * ASSUMPTION: while signed in... lastSignIn is updated every 2 minutes
		 * (1) lastSignOut == null --> see if last sign in time > 3 minutes ago
		 * (2) lastSignOut < lastSignIn --> see if last sign in time > 3 minutes ago
		 * (3)lastSignOut > lastSignIn --> not signed in
		 */
		boolean alreadySignedIn = (lastSignIn == null) ? false :
			((lastSignOut == null) || lastSignOut.before(lastSignIn)) ?
					((current.getTime() - lastSignIn.getTime()) < (3 * 60 * 1000)) :
						false;
					return alreadySignedIn;
	}
	
	
	/**
	 * Gets the manage measure search view.
	 * 
	 * @return the manageMeasureSearchView
	 */
	public ManageMeasureSearchView getManageMeasureSearchView() {
		return manageMeasureSearchView;
	}
	
	
	/**
	 * Sets the manage measure search view.
	 * 
	 * @param manageMeasureSearchView
	 *            the manageMeasureSearchView to set
	 */
	public void setManageMeasureSearchView(
			ManageMeasureSearchView manageMeasureSearchView) {
		this.manageMeasureSearchView = manageMeasureSearchView;
	}
	
	
	/**
	 * Gets the manage measure search model.
	 * 
	 * @return the manageMeasureSearchModel
	 */
	public ManageMeasureSearchModel getManageMeasureSearchModel() {
		return manageMeasureSearchModel;
	}
	
	
	/**
	 * Sets the manage measure search model.
	 * 
	 * @param manageMeasureSearchModel
	 *            the manageMeasureSearchModel to set
	 */
	public void setManageMeasureSearchModel(
			ManageMeasureSearchModel manageMeasureSearchModel) {
		this.manageMeasureSearchModel = manageMeasureSearchModel;
	}
	
	
	/**
	 * Checks if is error tab.
	 * 
	 * @return the isErrorTab
	 */
	public boolean isErrorTab() {
		return isErrorTab;
	}
	
	
	/**
	 * Sets the error tab.
	 * 
	 * @param isErrorTab
	 *            the isErrorTab to set
	 */
	public void setErrorTab(boolean isErrorTab) {
		this.isErrorTab = isErrorTab;
	}
	
	
	/**
	 * Gets the error tab index.
	 * 
	 * @return the errorTabIndex
	 */
	public int getErrorTabIndex() {
		return errorTabIndex;
	}
	
	
	/**
	 * Sets the error tab index.
	 * 
	 * @param errorTabIndex
	 *            the errorTabIndex to set
	 */
	public void setErrorTabIndex(int errorTabIndex) {
		this.errorTabIndex = errorTabIndex;
	}
	
	
	/**
	 * Sets the manage code list search view.
	 *
	 * @param manageCodeListSearchModel the new manage code list searc model
	 */
	//	public void setManageCodeListSearchView(ManageCodeListSearchView manageCodeListSearchView) {
	//		this.manageCodeListSearchView = manageCodeListSearchView;
	//	}
	//
	//
	//	/**
	//	 * Gets the manage code list search view.
	//	 *
	//	 * @return the manageCodeListSearchView
	//	 */
	//	public ManageCodeListSearchView getManageCodeListSearchView() {
	//		return manageCodeListSearchView;
	//	}
	
	
	/**
	 * Sets the manage code list searc model.
	 * 
	 * @param manageCodeListSearchModel
	 *            the new manage code list searc model
	 */
	public void setManageCodeListSearcModel(AdminManageCodeListSearchModel manageCodeListSearchModel) {
		this.manageCodeListSearchModel =  manageCodeListSearchModel;
	}
	
	
	/**
	 * Gets the manage code list search model.
	 * 
	 * @return the manageCodeListSearcModel
	 */
	public AdminManageCodeListSearchModel getManageCodeListSearchModel() {
		return manageCodeListSearchModel;
	}
	
	/**
	 * Method is called on SignOut/ X out / Time Out.
	 * 
	 * @param activityType
	 *            the activity type
	 * @param isRedirect
	 *            the is redirect
	 */
	public void handleSignOut(String activityType, final boolean isRedirect) {
		MatContext.get().getSynchronizationDelegate().setLogOffFlag();
		MatContext.get().setUMLSLoggedIn(false);
		MatContext.get().getLoginService().updateOnSignOut(MatContext.get().getLoggedinUserId(),
				MatContext.get().getLoggedInUserEmail(), activityType, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(final String result) {
				if (isRedirect) {
					MatContext.get().redirectToHtmlPage(ClientConstants.HTML_LOGIN);
				}
			}
			
			@Override
			public void onFailure(final Throwable caught) {
				if (isRedirect) {
					MatContext.get().redirectToHtmlPage(ClientConstants.HTML_LOGIN);
				}
			}
		});
	}
	
	
	/**
	 * Gets the all operators.
	 * 
	 * @return the all operators
	 */
	public void getAllOperators(){
		getCodeListService().getAllOperators(new AsyncCallback<List<OperatorDTO>>() {
			
			@Override
			public void onFailure(final Throwable caught) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSuccess(List<OperatorDTO> result) {
				for (OperatorDTO operatorDTO : result) {
					operatorMapKeyShort.put(operatorDTO.getId(), operatorDTO.getOperator());
					operatorMapKeyLong.put(operatorDTO.getOperator(), operatorDTO.getId());
					if(operatorDTO.getOperatorType().equals("1")){
						logicalOps.add(operatorDTO.getOperator());
					}else if(operatorDTO.getOperatorType().equals("2")){
						timings.add(operatorDTO.getOperator());
					}else if(operatorDTO.getOperatorType().equals("3")){
						relationships.add(operatorDTO.getOperator());
					}else if(operatorDTO.getOperatorType().equals("4")){
						functions.add(operatorDTO.getOperator());
					}else if(operatorDTO.getOperatorType().equals("5")){
						comparisonOps.add(operatorDTO.getOperator());
					}else if(operatorDTO.getOperatorType().equals("6")){
						setOps.add(operatorDTO.getOperator());
						//Collections.sort(setOps);
						Collections.reverse(setOps);
					}
					
				}
				//for adding Removed Relationship Types
				addRemovedRelationShipTypes();
			}
			
			private void addRemovedRelationShipTypes() {
				
				removedRelationshipTypes.put("Is Authorized By", "AUTH");
				removedRelationshipTypes.put("Causes", "CAUS");
				removedRelationshipTypes.put("Is Derived From", "DRIV");
				removedRelationshipTypes.put("Has Goal Of", "GOAL");
				removedRelationshipTypes.put("Has Outcome Of", "OUTC");
			}
		});
	}
	
	
	/**
	 * Gets the all data type.
	 *
	 * @return the all data type
	 */
	public void getAllDataType() {
		
		listBoxCodeProvider.getAllDataType(
				new AsyncCallback<List<? extends HasListBox>>() {
					
					@Override
					public void onFailure(final Throwable caught) {
					}
					
					@Override
					public void onSuccess(
							final List<? extends HasListBox> result) {
						Collections.sort(result,
								new HasListBox.Comparator());
						setAllDataTypeOptions(result);
					}
				});
	}
	// Get all CQL Data types/Timings/Functions from cqlTemplate.xml
	// And All QDM Data types except Attributes.
	/**
	 * Gets the all cql keywords and qdm datatypes for cql work space.
	 *
	 * @return the all cql keywords and qdm datatypes for cql work space
	 */
	public void getAllCqlKeywordsAndQDMDatatypesForCQLWorkSpace(){
				
		measureService.getCQLKeywordsList(new AsyncCallback<CQLKeywords>() {
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSuccess(CQLKeywords result) {
				cqlKeywords = result;
				
			}
		});
		
		
		listBoxCodeProvider.getAllDataType(
				new AsyncCallback<List<? extends HasListBox>>() {
					
					@Override
					public void onFailure(final Throwable caught) {
					}
					
					@Override
					public void onSuccess(
							final List<? extends HasListBox> result) {
						Collections.sort(result,
								new HasListBox.Comparator());
						dataTypeList.clear();
						dataTypeList.add(MatContext.PLEASE_SELECT);
						if (result != null) {
							for (HasListBox listBoxContent : result) {
								if(! listBoxContent.getItem().equalsIgnoreCase("attribute")){
									dataTypeList.add(listBoxContent.getItem());
								}
								
							}
							
						}
					}
				});
		
	}
	
	/**
	 * Gets of all of the units and updates the all units list. 
	 */
	public void getAllUnits() {
		
		listBoxCodeProvider.getUnitList(new AsyncCallback<List<? extends HasListBox>>() {

			@Override
			public void onFailure(Throwable caught) {				
			}

			@Override
			public void onSuccess(List<? extends HasListBox> result) {
				if(result != null){
					allUnitsList.clear();
					allUnitsList.add(MatContext.PLEASE_SELECT);
					for(HasListBox listBoxContent : result) {
						allUnitsList.add(listBoxContent.getItem());
					}
				}
			}
		}); 
	}
	
	/**
	 * Gets of all of the units and updates the all units list. 
	 * @return all cql list.
	 */
	public List<String> getAllCQLUnits() {
		getCodeListService().getAllCqlUnits(new AsyncCallback<List<String>> (){

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(List<String> result) {
				if(result != null){
					allCQLUnitsList.clear();
					allCQLUnitsList.add(MatContext.PLEASE_SELECT);
					for(String listBoxContent : result) {
						allCQLUnitsList.add(listBoxContent);
					}
				}
			}
		});
		return allCQLUnitsList;
		
	}
	
	/**
	 * Sets the all data type options.
	 *
	 * @param texts the new all data type options
	 */
	public void setAllDataTypeOptions(List<? extends HasListBox> texts) {
		setListBoxItems(texts, MatContext.PLEASE_SELECT);
	}
		
	/**
	 * Sets the list box items.
	 *
	 * @param itemList the item list
	 * @param defaultOption the default option
	 */
	private void setListBoxItems(List<? extends HasListBox> itemList, String defaultOption) {
		dataTypeList.clear();
		dataTypeList.add(defaultOption);
		if (itemList != null) {
			for (HasListBox listBoxContent : itemList) {
				//MAT-4366
				if(! listBoxContent.getItem().equalsIgnoreCase("Patient Characteristic Birthdate") &&
						! listBoxContent.getItem().equalsIgnoreCase("Patient Characteristic Expired")){
					dataTypeList.add(listBoxContent.getItem());
				}
				
			}
			
		}
	}
	
	
	/**
	 * Gets the all versions by oid.
	 *
	 * @param oid the oid
	 * @param index the index
	 * @return the all versions by oid
	 */
	public void getAllVersionsByOID(String oid, int index){
		vsacapiServiceAsync.getAllVersionListByOID(oid, new AsyncCallback<VsacApiResult>() {
			
			@Override
			public void onSuccess(VsacApiResult result) {
				if (result.getVsacExpIdentifierResp() != null) {
					
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/**
	 * Gets the all attributes list.
	 *
	 * @return the all attributes list
	 */
	public void getAllAttributesList(){
		getQdsAttributesServiceAsync().getAllAttributes(new AsyncCallback<List<String>>() {
			
			@Override
			public void onSuccess(List<String> result) {
				setAllAttributesList(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/**
	 * Sets the all attributes list.
	 *
	 * @param result the new all attributes list
	 */
	protected void setAllAttributesList(List<String> result) {
		if(result != null){
			allAttributeList.clear();
			Collections.sort(result);
			allAttributeList.addAll(result);
		}
		
	}


	/**
	 * Gets the all profile list.
	 *
	 * @return the all profile list
	 */
	public void getAllExpIdentifierList(){
		vsacapiServiceAsync
		.getAllExpIdentifierList(new AsyncCallback<VsacApiResult>() {
			
			@Override
			public void onSuccess(
					VsacApiResult result) {
				if (result.getVsacExpIdentifierResp() != null) {
					vsacExpIdentifierList = result.getVsacExpIdentifierResp();
					expIdentifierList = getExpIdentifierList(result.getVsacExpIdentifierResp());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	/**
	 * Gets the vsac exp identifier list.
	 *
	 * @return the vsac exp identifier list
	 */
	public List<VSACExpansionIdentifier> getVsacExpIdentifierList() {
		return vsacExpIdentifierList;
	}
	
	
	/**
	 * Sets the modify qdm pop up widget.
	 * 
	 * @param modifyQDMPopUpWidget
	 *            the new modify qdm pop up widget
	 */
	public void setModifyQDMPopUpWidget(
			QDMAvailableValueSetWidget modifyQDMPopUpWidget) {
		this.modifyQDMPopUpWidget = modifyQDMPopUpWidget;
	}
	
	
	/**
	 * Checks if is measure deleted.
	 * 
	 * @return true, if is measure deleted
	 */
	public boolean isMeasureDeleted() {
		return isMeasureDeleted;
	}
	
	
	/**
	 * Sets the measure deleted.
	 * 
	 * @param isMeasureDeleted
	 *            the new measure deleted
	 */
	public void setMeasureDeleted(boolean isMeasureDeleted) {
		this.isMeasureDeleted = isMeasureDeleted;
	}
	
	
	/**
	 * Checks if is uMLS logged in.
	 * 
	 * @return true, if is uMLS logged in
	 */
	public boolean isUMLSLoggedIn() {
		return isUMLSLoggedIn;
	}
	
	
	/**
	 * Gets the data type list.
	 *
	 * @return the data type list
	 */
	public List<String> getDataTypeList() {
		return dataTypeList;
	}
	
	
	/**
	 * Sets the uMLS logged in.
	 * 
	 * @param isUMLSLoggedIn
	 *            the new uMLS logged in
	 */
	public void setUMLSLoggedIn(boolean isUMLSLoggedIn) {
		this.isUMLSLoggedIn = isUMLSLoggedIn;
	}
	
	/**
	 * Gets the allowed populations in package.
	 *
	 * @return the allowed populations in package
	 */
	public List<String> getAllowedPopulationsInPackage(){
		
		List<String> allowedPopulationsInPackage = new ArrayList<String>();
		
		allowedPopulationsInPackage.add("initialPopulation");
		allowedPopulationsInPackage.add("stratification");
		allowedPopulationsInPackage.add("measurePopulation");
		allowedPopulationsInPackage.add("measurePopulationExclusions");
		allowedPopulationsInPackage.add("measureObservation");
		allowedPopulationsInPackage.add("denominator");
		allowedPopulationsInPackage.add("denominatorExclusions");
		allowedPopulationsInPackage.add("denominatorExceptions");
		allowedPopulationsInPackage.add("numerator");
		allowedPopulationsInPackage.add("numeratorExclusions");
		return allowedPopulationsInPackage;
	}
	
	/**
	 * Gets the profile list.
	 *
	 * @param list the list
	 * @return the copiedNode
	 */
	/*
	 * POC Global Copy Paste
	 * public CellTreeNode getCopiedNode() {
		return copiedNode;
	}*/
	
	
	
	
	/**
	 * @param copiedNode the copiedNode to set
	 */
	/*
	 * POC GLobal Copy Paste.
	 * public void setCopiedNode(CellTreeNode copiedNode) {
		this.copiedNode = copiedNode;
	}*/
	private List<String> getExpIdentifierList(List<VSACExpansionIdentifier> list) {
		List<String> expIdentifier = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			expIdentifier.add(list.get(i).getName());
		}
		return expIdentifier;
	}
	
	
	/**
	 * Gets the profile list.
	 *
	 * @return the profile list
	 */
	public List<String> getExpIdentifierList() {
		return expIdentifierList;
	}
	
	
	/**
	 * Sets the profile list.
	 *
	 * @param profileList the new profile list
	 */
	public void setExpIdentifierList(List<String> profileList) {
		expIdentifierList = profileList;
	}
	
	/**
	 * Gets the global copy paste.
	 *
	 * @return the global copy paste
	 */
	public GlobalCopyPasteObject getGlobalCopyPaste() {
		return globalCopyPaste;
	}
	
	/**
	 * Sets the global copy paste.
	 *
	 * @param globalCopyPaste the new global copy paste
	 */
	public void setGlobalCopyPaste(GlobalCopyPasteObject globalCopyPaste) {
		this.globalCopyPaste = globalCopyPaste;
	}
	
	public void getCurrentReleaseVersion(AsyncCallback<String> currentReleaseVersionCallback){
		getSessionService().getCurrentReleaseVersion(currentReleaseVersionCallback);
	}

	
	/**
	 * Gets the cql grammar data type.
	 *
	 * @return the cql grammar data type
	 */
	public CQLKeywords getCqlGrammarDataType() {
		return cqlKeywords;
	}


	/**
	 * Gets the definitions.
	 *
	 * @return the definitions
	 */
	public List<String> getDefinitions() {
		return definitions;
	}


	/**
	 * Sets the definitions.
	 *
	 * @param definitions the new definitions
	 */
	public void setDefinitions(List<String> definitions) {
		this.definitions = definitions;
	}


	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public List<String> getParameters() {
		return parameters;
	}


	/**
	 * Sets the parameters.
	 *
	 * @param parameters the new parameters
	 */
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}


	/**
	 * Gets the funcs.
	 *
	 * @return the funcs
	 */
	public List<String> getFuncs() {
		return funcs;
	}


	/**
	 * Sets the funcs.
	 *
	 * @param funcs the new funcs
	 */
	public void setFuncs(List<String> funcs) {
		this.funcs = funcs;
	}


	/**
	 * Gets the qds attributes service async.
	 *
	 * @return the qds attributes service async
	 */
	public QDSAttributesServiceAsync getQdsAttributesServiceAsync() {
		
		if(qdsAttributesServiceAsync == null){
			qdsAttributesServiceAsync = (QDSAttributesServiceAsync) GWT.create(QDSAttributesService.class);
		}
		
		return qdsAttributesServiceAsync;
	}


	/**
	 * Sets the qds attributes service async.
	 *
	 * @param qdsAttributesServiceAsync the new qds attributes service async
	 */
	public void setQdsAttributesServiceAsync(QDSAttributesServiceAsync qdsAttributesServiceAsync) {
		this.qdsAttributesServiceAsync = qdsAttributesServiceAsync;
	}


	/**
	 * Gets the all attribute list.
	 *
	 * @return the all attribute list
	 */
	public List<String> getAllAttributeList() {
		return allAttributeList;
	}


	/**
	 * Sets the all attribute list.
	 *
	 * @param allAttributeList the new all attribute list
	 */
	public void setAllAttributeList(List<String> allAttributeList) {
		this.allAttributeList = allAttributeList;
	}	
	
	/**
	 * Gets the list of all of the units
	 * @return the list of all of the units
	 */
	public List<String> getAllUnitsList() {
		return this.allUnitsList;
	}
	
	/**
	 * Sets the list of all of the units
	 * @param allUnitsList the list of all of the units
	 */
	public void setAllUnitsList(List<String> allUnitsList) {
		this.allUnitsList = allUnitsList;
	}
	
	/**
	 * Gets the list of all of the cql units
	 * @return the list of all of the cql units
	 */
	public List<String> getAllCQLUnitsList() {
		return this.allCQLUnitsList;
	}
	
	/**
	 * Sets the list of all of the cql units
	 * @param allUnitsList the list of all of the cql units
	 */
	public void setAllCQLUnitsList(List<String> allCQLUnitsList) {
		this.allCQLUnitsList = allCQLUnitsList;
	}
	
	/*public GlobalCopyPaste getCopyPaste() {
		return copyPaste;
	}
	
	
	public void setCopyPaste(GlobalCopyPaste copyPaste) {
		this.copyPaste = copyPaste;
	}*/
	
	
}
