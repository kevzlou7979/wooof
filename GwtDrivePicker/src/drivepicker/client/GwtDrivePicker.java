package drivepicker.client;

import com.floreysoft.gwt.picker.client.callback.AbstractPickerCallback;
import com.floreysoft.gwt.picker.client.domain.Feature;
import com.floreysoft.gwt.picker.client.domain.ImageSearchView;
import com.floreysoft.gwt.picker.client.domain.MapsView;
import com.floreysoft.gwt.picker.client.domain.PhotosView;
import com.floreysoft.gwt.picker.client.domain.Picker;
import com.floreysoft.gwt.picker.client.domain.PickerBuilder;
import com.floreysoft.gwt.picker.client.domain.VideoSearchView;
import com.floreysoft.gwt.picker.client.domain.ViewGroup;
import com.floreysoft.gwt.picker.client.domain.ViewId;
import com.floreysoft.gwt.picker.client.domain.result.BaseResult;
import com.floreysoft.gwt.picker.client.domain.result.DocumentResult;
import com.floreysoft.gwt.picker.client.domain.result.ImageSearchResult;
import com.floreysoft.gwt.picker.client.domain.result.MapResult;
import com.floreysoft.gwt.picker.client.domain.result.PhotoResult;
import com.floreysoft.gwt.picker.client.domain.result.VideoResult;
import com.floreysoft.gwt.picker.client.domain.result.ViewToken;
import com.floreysoft.gwt.picker.client.utils.PickerLoader;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.oauth2.client.Callback;
import com.google.appengine.api.users.UserService;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import drivepicker.shared.User;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtDrivePicker implements EntryPoint {
	private String API_KEY = "AIzaSyA5Et0lW5wJdjQm4J8z4o6qtIn8SP5o3E8";

	private Image image = new Image();
	private Label label = new Label();
	
	String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	String CLIENT_ID = "674045101011-457aakispnhr59r7ksl90847qpbbas0j.apps.googleusercontent.com"; // available from the APIs console
	private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
	private static final String DRIVE_SCOPE = "https://www.googleapis.com/auth/drive.file";
	AuthRequest req = new AuthRequest(AUTH_URL, CLIENT_ID).withScopes(PLUS_ME_SCOPE, DRIVE_SCOPE);
	
	  public void onModuleLoad() {
	   
		  Auth.get().login(req, new Callback<String, Throwable>() {
			  @Override
			  public void onSuccess(final String token) {
				 /* GreetingService.Connect.getService().login(token, new AsyncCallback<User>() {
					
					@Override
					public void onSuccess(User result) {
						image.setUrl(result.getPictureUrl());
						label.setText(result.getEmailAddress());
						RootPanel.get().add(image);
						RootPanel.get().add(label);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});*/
				  PickerLoader.loadApi(API_KEY, new Runnable() {
				      public void run() {
				        onPickerLoaded(token);
				      }
				    });
			  }
			  @Override
			  public void onFailure(Throwable caught) {
			    // The authentication process failed for some reason, see caught.getMessage()
			  }
			});
		  
		  
	  }

	  private void onPickerLoaded(final String token) {
	    for (final ViewId viewId : ViewId.values()) {
	      final Button button = new Button(viewId.name().toLowerCase());
	      button.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent clickEvent) {
	          onCreatePicker(viewId, token);
	        }
	      });

	      RootPanel.get().add(button);
	    }

	    final Button button = new Button("View Group");
	    RootPanel.get().add(button);

	    button.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent clickEvent) {
	        final ViewGroup group = ViewGroup.create(MapsView.create());
	        group.addView(ImageSearchView.create());
	        group.addView(PhotosView.create());
	        group.addView(VideoSearchView.create());
	        group.addView(ViewId.DOCUMENTS);

	        final Picker picker = PickerBuilder.create()
	        		.setOAuthToken(token)
	                .addViewGroup(group).addView(ViewId.VIDEO_SEARCH)
	                .setLocale("de").setTitle("my big fat picker example")
	                .setUploadToAlbumId("albumId")
	                .enableFeature(Feature.MULTISELECT_ENABLED)
	                .disableFeature(Feature.NAV_HIDDEN)
	                .addCallback(new AbstractPickerCallback() {
	                  @Override
	                  public void onPicked(ViewToken viewToken, BaseResult baseResult) {
	                    final ViewId viewId = viewToken.getViewId();
	                    DOM.getElementById("output")
	                            .setInnerHTML("Document from " + (viewId != null ? viewId : viewToken.getNativeViewId()) + " view was picked.");
	                  }

	                  @Override
	                  public void onCanceled() {
	                    doOnCancel();
	                  }
	                })
	                .addCallback(new AbstractPickerCallback() {
	                  @Override
	                  public void onPicked(ViewToken viewToken, BaseResult baseResult) {
	                    GWT.log("This must be a map:" + (baseResult.getViewToken().getViewId().equals(ViewId.MAPS)));
	                  }

	                  @Override
	                  public void onCanceled() {
	                  }

	                  @Override
	                  public boolean isApplicable(ViewToken token) {
	                    return token.getViewId().equals(ViewId.MAPS);
	                  }
	                })
	                .build();
	        picker.setVisible(true);
	      }
	    }

	    );
	  }

	  private void onCreatePicker(ViewId viewId, String oauthToken) {
	    final Picker picker = PickerBuilder.create().setOAuthToken(oauthToken).addView(viewId)
	            .addCallback(buildPickerCallback(viewId)).build();
	    picker.setVisible(true);
	  }

	  private AbstractPickerCallback buildPickerCallback(final ViewId viewId) {
	    return new AbstractPickerCallback() {
	      @Override
	      public void onPicked(ViewToken viewToken, BaseResult result) {
	        switch (viewId) {
	          case DOCS:
	          case DOCUMENTS:
	          case SPREADSHEETS:
	          case FORMS:
	          case PRESENTATIONS:
	          case FOLDERS:
	          case PDFS:
	          case RECENTLY_PICKED:
	            ResultPrinter.print(viewToken, result.<DocumentResult>cast());
	            break;
	          case IMAGE_SEARCH:
	            ResultPrinter.print(viewToken, result.<ImageSearchResult>cast());
	            break;
	          case MAPS:
	            ResultPrinter.print(viewToken, result.<MapResult>cast());
	            break;
	          case PHOTOS:
	          case PHOTO_UPLOAD:
	            ResultPrinter.print(viewToken, result.<PhotoResult>cast());
	            break;
	          case VIDEO_SEARCH:
	          case YOUTUBE:
	            ResultPrinter.print(viewToken, result.<VideoResult>cast());
	            break;
	          default:
	            GWT.log("Unknown view id " + viewId);
	        }
	      }

	      @Override
	      public void onCanceled() {
	        doOnCancel();
	      }
	    };
	  }

	  private void doOnCancel() {
	    DOM.getElementById("output").setInnerHTML("canceled");
	  }
}
