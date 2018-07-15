package com.poke;

public class Card implements Comparable<Card> {
	
	private String color;
	private String num;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Card(String color, String num) {
		setColor(color);
		setNum(num);
	}
	@Override
	public String toString() {
		return "[" + color + num + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		return true;
	}
	@Override
	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		if (this.getNum().equals(o.getNum())) {
			return this.getColor().compareTo(o.getColor());
		}else {
			return this.changeNum()-o.changeNum();
		}
	}
	private int changeNum() {
		String[] nums = {"2","3","4","5","6","7","8","9","10", "J","Q","K","A"};
		for (int i = 0; i < nums.length; i++) {
			if (nums[i].equals(num)) {
				return i;
			}
		}
		return -1;
	}
	

}
