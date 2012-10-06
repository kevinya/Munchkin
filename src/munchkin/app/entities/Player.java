package munchkin.app.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
	public static final String PLAYER_ID = "PLAYER_ID";
	public static final String PLAYER_NAME = "PLAYER_NAME";
	public static final String PLAYER_LEVEL = "PLAYER_LEVEL";
	public static final String PLAYER_BONUS = "PLAYER_BONUS";
	public static final Creator<Player> creator = new Creator<Player>() {
		
		@Override
		public Player[] newArray(int size) {
			return new Player[size];
		}
		
		@Override
		public Player createFromParcel(Parcel source) {
			return new Player(source);
		}
	};
	
	private String name;
	private int level;
	private int bonus;
	
	public Player(String name) {
		this.name = name;
		this.level = 1;
		this.bonus = 0;
	}

	public Player(Parcel source) {
		this.name = source.readString();
		this.level = source.readInt();
		this.bonus = source.readInt();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	public int describeContents() {
		return 3;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(this.name);
		arg0.writeInt(this.level);
		arg0.writeInt(this.bonus);
	}
}
