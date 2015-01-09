package project.andi.client.material;

public class AndiToast {

	public AndiToast() {
		// TODO Auto-generated constructor stub
	}
	
	public static native void alert(String msg) /*-{
	  $wnd.toast(msg, 4000);
	}-*/;
	
}
