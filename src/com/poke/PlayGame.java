package com.poke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PlayGame {
	
	public List<Card> cardList;
	public List<People> peopleList;
	
	public PlayGame() {
		this.cardList = new ArrayList<Card>();
		this.peopleList = new ArrayList<People>();
	}
	
	/**
	 * 创建扑克牌
	 * @param args
	 */
	public void creatPoke() {
		System.out.println("开始创建扑克牌");
		String[] colors = {"黑桃","红桃","梅花","方片"};
		String[] numbers = {"2","3","4","5","6","7","8","9","10", "J","Q","K","A"};
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				cardList.add(new Card(colors[i], numbers[j]));
			}
		}
		System.out.println("扑克牌创建成功");
		for (Card card : cardList) {
			System.out.println(card.getColor()+card.getNum());
		}
	}
	
	/**
	 * 洗牌
	 * @param args
	 */
	public void shuffleCard() {
		System.out.println("开始洗牌");
		Collections.shuffle(cardList);
		System.out.println("洗牌结束");
//		for (Card card : cardList) {
//			System.out.println(card.getColor()+card.getNum());
//		}
	}
	
	/**
	 * 创建玩家
	 * @param args
	 */
	public void creatPlayer() {
		System.out.println("创建玩家：");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 2; i++) {
			//加入异常处理，输入整数id
			try {
				System.out.println("请输入第" + (i+1) + "位玩家的id和姓名");
				System.out.println("输入ID：");
				int id = scanner.nextInt();
				//判断输入id是否存在
				if (peopleList.contains(new People(id + "", null))) {
					System.out.println("该id已存在");
					i--;
					continue;
				}else {
					System.out.println("输入姓名：");
					String name = scanner.next();
					peopleList.add(new People(id + "", name));
				}
			} catch (Exception e) {
                //这个必须有，不然就是死循环了
				scanner = new Scanner(System.in);
				System.out.println("请输入整数");
				i--;
				continue;
			}
			
		}
		for (People people : peopleList) {
			System.out.println("---欢迎玩家：" + people.getName());
		}
	}
	
	/**
	 * 发牌
	 * @param args
	 */
	public void getCard() {
		System.out.println("开始发牌");
		Random random = new Random();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println("玩家：" + peopleList.get(j).getName() + "拿牌");
				//获取整个牌组的长度
				int length = cardList.size();
				//随机获取一个长度以内的索引值
				int num = random.nextInt(length);
				//添加此索引的牌给玩家
				Card card = cardList.get(num);
				peopleList.get(j).cards.add(card);
				System.out.println(card);
				//将拿出的牌remove
				cardList.remove(num);
			}
		}
		System.out.println("发牌结束");
	}
	
	/**
	 * 开始游戏，两人手中牌最大获胜
	 * @param args
	 */
	public void startGame() {
		System.out.println("开始游戏");
		for (People people : peopleList) {
			Collections.sort(people.cards);
			System.out.println("玩家："+people.getName()+"最大的手牌为"+people.cards.get(1));
		}
		int judge=peopleList.get(0).cards.get(1).compareTo(peopleList.get(1).cards.get(1));
        String name=judge>0 ? peopleList.get(0).getName():peopleList.get(1).getName();
        System.out.println("-----------玩家："+name+"获胜！--------------");
	}
	
	//展示手牌 
    public void showCards(){
        System.out.println("-----------玩家手牌展示--------------");
        for (People people : peopleList) {
			System.out.println(people.getName() + ":" + people.cards);
		}
    }
	

	public static void main(String[] args) {
		PlayGame pGame = new PlayGame();
		pGame.creatPoke();
		pGame.shuffleCard();
		pGame.creatPlayer();
		pGame.getCard();
		pGame.startGame();
		pGame.showCards();

	}

}
