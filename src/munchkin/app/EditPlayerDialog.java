package munchkin.app;

import munchkin.app.entities.Player;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditPlayerDialog extends Activity {
	public static final String ACTION_TYPE = "ACTION_TYPE";
	public static final int EDIT_PLAYER = 0;
	public static final int DELETE_PLAYER = 1;
	private EditText playerNameEdit;
	private Button deleteButton;
	private Button levelPlusButton;
	private EditText levelEdit;
	private Button levelMinusButton;
	private Button bonusPlusButton;
	private EditText bonusEdit;
	private Button bonusMinusButton;
	private Button editButton;
	private Button cancelButton;
	
	private int playerId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_player_dialog);
		playerNameEdit = (EditText) findViewById(R.id.editText1);
		deleteButton = (Button) findViewById(R.id.delete_button);
		levelPlusButton = (Button) findViewById(R.id.level_plus_button);
		levelMinusButton = (Button) findViewById(R.id.level_minus_button);
		levelEdit = (EditText) findViewById(R.id.level_edittext);
		bonusPlusButton = (Button) findViewById(R.id.bonus_plus_button);
		bonusMinusButton = (Button) findViewById(R.id.bonus_minus_button);
		bonusEdit = (EditText) findViewById(R.id.bonus_edittext);
		deleteButton = (Button) findViewById(R.id.delete_button);
		editButton = (Button) findViewById(R.id.edit_button);
		cancelButton = (Button) findViewById(R.id.cancel_button);
		initializeStatistics();

		levelPlusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				changeLevel(1);
			}
		});

		levelMinusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				changeLevel(-1);
			}
		});

		bonusPlusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				changeBonus(1);
			}
		});

		bonusMinusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				changeBonus(-1);
			}
		});
		
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				deletePlayer();
			}
		});
		
		editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editPlayer();
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancel();
			}
		});
	}

	protected void changeLevel(int modifier) {
		if (levelEdit.getText().toString().equals("") || levelEdit.getText().toString().equals("-")) {
			levelEdit.setText("0");
		}
		int currentLevel = new Integer(levelEdit.getText().toString()).intValue();
		int level = currentLevel + modifier;
		levelEdit.setText("" + level);
	}

	protected void changeBonus(int modifier) {
		if (bonusEdit.getText().toString().equals("") || bonusEdit.getText().toString().equals("-")) {
			bonusEdit.setText("0");
		}
		int currentBonus = new Integer(bonusEdit.getText().toString()).intValue();
		int bonus = currentBonus + modifier;
		bonusEdit.setText("" + bonus);
	}
	
	protected void initializeStatistics() {
		Bundle extras = getIntent().getExtras();
		playerId = extras.getInt(Player.PLAYER_ID);
		playerNameEdit.setText(extras.getString(Player.PLAYER_NAME));
		levelEdit.setText("" + extras.getInt(Player.PLAYER_LEVEL));
		bonusEdit.setText("" + extras.getInt(Player.PLAYER_BONUS));
	}

	protected void deletePlayer() {
		Intent resultIntent = new Intent(this, MunchkinActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(EditPlayerDialog.ACTION_TYPE, EditPlayerDialog.DELETE_PLAYER);
		bundle.putInt(Player.PLAYER_ID, playerId);
		resultIntent.putExtras(bundle);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	protected void editPlayer() {
		if (levelEdit.getText().toString().equals("") || levelEdit.getText().toString().equals("-")) {
			levelEdit.setText("0");
		}
		if (bonusEdit.getText().toString().equals("") || bonusEdit.getText().toString().equals("-")) {
			bonusEdit.setText("0");
		}
		int level = new Integer(levelEdit.getText().toString()).intValue();
		int bonus = new Integer(bonusEdit.getText().toString()).intValue();
		
		Intent resultIntent = new Intent(this, MunchkinActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(EditPlayerDialog.ACTION_TYPE, EditPlayerDialog.EDIT_PLAYER);
		bundle.putInt(Player.PLAYER_ID, playerId);
		bundle.putString(Player.PLAYER_NAME, playerNameEdit.getText().toString().trim());
		bundle.putInt(Player.PLAYER_LEVEL, level);
		bundle.putInt(Player.PLAYER_BONUS, bonus);
		resultIntent.putExtras(bundle);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}

	protected void cancel() {
		initializeStatistics();
		editPlayer();
	}

}
