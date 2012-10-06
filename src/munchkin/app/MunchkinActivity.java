package munchkin.app;

import java.util.ArrayList;

import munchkin.app.adapter.PlayerAdapter;
import munchkin.app.entities.Player;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class MunchkinActivity extends ListActivity {
	private static final String PLAYER_LIST = "PLAYER_LIST";
	private static final int CREATE_PLAYER_DIALOG = 0;
	private static final int EDIT_PLAYER_DIALOG = 1;
	
	private Button addPlayerButton;
	private PlayerAdapter playerAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayList<Player> playerList;
        if (savedInstanceState == null) {
        	playerList = new ArrayList<Player>();
        }
        else {
        	playerList = savedInstanceState.getParcelableArrayList(PLAYER_LIST);
        }
    	playerAdapter = new PlayerAdapter(this, R.id.linearLayout1, playerList);
        setListAdapter(playerAdapter);

        addPlayerButton = (Button) findViewById(R.id.button1);
        addPlayerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startCreatePlayerDialog();
			}
		});
        
        getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startEditPlayerDialog(arg2);
			}
		});
    }

	protected void startCreatePlayerDialog() {
		Intent intent = new Intent(this, CreatePlayerDialog.class);
		startActivityForResult(intent, CREATE_PLAYER_DIALOG);
	}

	protected void startEditPlayerDialog(int playerId) {
		Intent intent = new Intent(this, EditPlayerDialog.class);
		Player player = playerAdapter.getPlayerList().get(playerId);
		Bundle bundle = new Bundle();
		bundle.putInt(Player.PLAYER_ID, playerId);
		bundle.putString(Player.PLAYER_NAME, player.getName());
		bundle.putInt(Player.PLAYER_LEVEL, player.getLevel());
		bundle.putInt(Player.PLAYER_BONUS, player.getBonus());
		intent.putExtras(bundle);
		startActivityForResult(intent, EDIT_PLAYER_DIALOG);
	}
	
	protected void refreshPlayerList() {
		playerAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList(PLAYER_LIST, playerAdapter.getPlayerList());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
			case(CREATE_PLAYER_DIALOG): {
				if (resultCode == Activity.RESULT_OK) {
					String playerName = data.getStringExtra(CreatePlayerDialog.PLAYER_NAME);
					Player player = new Player(playerName);
					playerAdapter.getPlayerList().add(player);
					refreshPlayerList();
				}
				break;
			}
			case(EDIT_PLAYER_DIALOG): {
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					if (bundle.getInt(EditPlayerDialog.ACTION_TYPE) == EditPlayerDialog.EDIT_PLAYER) {
						int playerId = bundle.getInt(Player.PLAYER_ID);
						String playerName = bundle.getString(Player.PLAYER_NAME);
						int playerLevel = bundle.getInt(Player.PLAYER_LEVEL);
						int playerBonus = bundle.getInt(Player.PLAYER_BONUS);
						Player player = playerAdapter.getPlayerList().get(playerId);
						player.setName(playerName);
						player.setLevel(playerLevel);
						player.setBonus(playerBonus);
						refreshPlayerList();
					}
					if (bundle.getInt(EditPlayerDialog.ACTION_TYPE) == EditPlayerDialog.DELETE_PLAYER) {
						int playerId = bundle.getInt(Player.PLAYER_ID);
						playerAdapter.getPlayerList().remove(playerId);
						refreshPlayerList();
					}
				}
				break;
			}
		}
	}
	
}