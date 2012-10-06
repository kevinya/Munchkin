package munchkin.app.adapter;

import java.util.ArrayList;
import java.util.List;

import munchkin.app.R;
import munchkin.app.entities.Player;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlayerAdapter extends ArrayAdapter<Player> {
	private ArrayList<Player> playerList;

	public PlayerAdapter(Context context, int textViewResourceId,
			List<Player> objects) {
		super(context, textViewResourceId, objects);
		playerList = (ArrayList<Player>) objects;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View returnedView = convertView;
		if (returnedView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			returnedView = layoutInflater.inflate(R.layout.player_row, null);
		}
		Player player = playerList.get(position);
		Log.v("adapter.getView", "player:" + player.getName() + ";level:" + player.getLevel() + ";bonus:" + player.getBonus());
		if (player != null) {
			TextView playerName = (TextView) returnedView.findViewById(R.id.textView1);
			TextView playerLevel = (TextView) returnedView.findViewById(R.id.textView2);
			TextView playerBonus = (TextView) returnedView.findViewById(R.id.textView3);
			playerName.setText(player.getName());
			playerLevel.setText("" + player.getLevel());
			playerBonus.setText("" + player.getBonus());
		}
		return returnedView;
	}

}
