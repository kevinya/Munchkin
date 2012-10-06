package munchkin.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreatePlayerDialog extends Activity {
	public static final String PLAYER_NAME = "PLAYER_NAME";
	private EditText playerNameEdit;
	private Button addButton;
	private Button cancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_player_dialog);
		playerNameEdit = (EditText) findViewById(R.id.editText1);
		addButton = (Button) findViewById(R.id.button2);
		cancelButton = (Button) findViewById(R.id.button3);
		
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addPlayer();
			}

		});
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancelDialog();
			}
		});
	}

	protected void addPlayer() {
		Intent resultIntent = new Intent(this, MunchkinActivity.class);
		resultIntent.putExtra(PLAYER_NAME, playerNameEdit.getText().toString().trim());
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}

	protected void cancelDialog() {
		finish();
	}

}
