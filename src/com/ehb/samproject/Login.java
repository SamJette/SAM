package com.ehb.samproject;

import org.json.JSONException;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

/**
 * 
 * @author Patricia Meeremans, Kristof Polleunis, Anderson Muela, Collin Koolenbrander<br>
 * @version 1.0<br>
 * 04-juni-2012<br>
 */
public class Login extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
		RestClient.client.setCookieStore(myCookieStore);
	}

	@Override
	protected void onStart() {
		super.onStart();
		showAccessPopUpDialog();
	}

	private void showAccessPopUpDialog() {
		final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle(R.string._loginscreen_title);
		helpBuilder.setMessage(R.string._login_message);
		helpBuilder.setIcon(R.drawable.key_stroke_32x32);

		final TextView LabelName = new TextView(this);
		LabelName.setWidth(100);
		LabelName.setText(R.string._email);

		final EditText inputName = new EditText(this);
		inputName.setSingleLine();
		inputName.setWidth(200);
		inputName.setInputType(InputType.TYPE_CLASS_TEXT);
		inputName.setText("");

		final TextView LabelClass = new TextView(this);
		LabelClass.setWidth(100);
		LabelClass.setText(R.string._class);

		final EditText inputClass = new EditText(this);
		inputClass.setSingleLine();
		inputClass.setWidth(200);
		inputClass.setInputType(InputType.TYPE_CLASS_TEXT);
		inputClass.setText("");

		final TextView LabelPass = new TextView(this);
		LabelPass.setWidth(100);
		LabelPass.setText(R.string._password);

		final EditText inputPass = new EditText(this);
		inputPass.setSingleLine();
		inputPass.setWidth(200);
		inputPass.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		inputPass.setText("");

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER_HORIZONTAL);
		layout.setPadding(10, 5, 0, 5);

		LinearLayout layoutUser = new LinearLayout(this);

		layoutUser.setLayoutParams(params);
		layoutUser.addView(LabelName);
		layoutUser.addView(inputName);

		LinearLayout layoutPass = new LinearLayout(this);

		layoutPass.setLayoutParams(params);
		layoutPass.addView(LabelPass);
		layoutPass.addView(inputPass);

		LinearLayout layoutClass = new LinearLayout(this);

		layoutClass.setLayoutParams(params);
		layoutClass.addView(LabelClass);
		layoutClass.addView(inputClass);

		layout.addView(layoutUser);
		layout.addView(layoutPass);
		layout.addView(layoutClass);

		helpBuilder.setView(layout);

		helpBuilder.setPositiveButton(R.string._login,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing but close the dialog
						String inputUserTxt = inputName.getText().toString();
						String inputPasswordTxt = inputPass.getText()
								.toString();
						String inputClassTxt = inputClass.getText().toString();

						RequestParams params = new RequestParams();
						params.put("teacher[email]", inputUserTxt);
						params.put("teacher[password]", inputPasswordTxt);
						params.put("teacher[classid]", inputClassTxt);

						try {
							login(params);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

		helpBuilder.setNegativeButton(R.string._cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
						finish();
					}
				});

		AlertDialog helpDialog = helpBuilder.create();

		helpDialog.setCanceledOnTouchOutside(false);

		helpDialog.show();

		helpDialog.getWindow().setLayout(450, 430); // Controlling width and
													// height.
	}

	public void login(RequestParams params) throws JSONException {

		RestClient.post("", params, new JsonHttpResponseHandler() {
			private ProgressDialog dialog;
			private String _id;
			private String _classid;
			private String _email;
			private String _allowed = null;

			@Override
			public void onStart() {
				dialog = ProgressDialog.show(Login.this, "Loading",
						"Data Loading", true, true, new OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								dialog.dismiss();
							}
						});
			}

			@Override
			public void onSuccess(JSONObject response) {
				if (this.dialog.isShowing())
					this.dialog.dismiss();

				try {
					_id = response.getString("id");
					_classid = response.getString("classid");
					_email = response.getString("email");
					_allowed = response.getString("allowed");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(_id);
				System.out.println(_classid);
				System.out.println(_email);
				if (_allowed.equals("No")) {
					onFailure();
				} else {
					System.out.println("ok");
				}

			}

			public void onFailure() {
				Toast toast = Toast.makeText(getBaseContext(), R.string._error,
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

				showAccessPopUpDialog();

			}
		});

	}
}
