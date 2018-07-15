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
	 * �����˿���
	 * @param args
	 */
	public void creatPoke() {
		System.out.println("��ʼ�����˿���");
		String[] colors = {"����","����","÷��","��Ƭ"};
		String[] numbers = {"2","3","4","5","6","7","8","9","10", "J","Q","K","A"};
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				cardList.add(new Card(colors[i], numbers[j]));
			}
		}
		System.out.println("�˿��ƴ����ɹ�");
		for (Card card : cardList) {
			System.out.println(card.getColor()+card.getNum());
		}
	}
	
	/**
	 * ϴ��
	 * @param args
	 */
	public void shuffleCard() {
		System.out.println("��ʼϴ��");
		Collections.shuffle(cardList);
		System.out.println("ϴ�ƽ���");
//		for (Card card : cardList) {
//			System.out.println(card.getColor()+card.getNum());
//		}
	}
	
	/**
	 * �������
	 * @param args
	 */
	public void creatPlayer() {
		System.out.println("������ң�");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 2; i++) {
			//�����쳣������������id
			try {
				System.out.println("�������" + (i+1) + "λ��ҵ�id������");
				System.out.println("����ID��");
				int id = scanner.nextInt();
				//�ж�����id�Ƿ����
				if (peopleList.contains(new People(id + "", null))) {
					System.out.println("��id�Ѵ���");
					i--;
					continue;
				}else {
					System.out.println("����������");
					String name = scanner.next();
					peopleList.add(new People(id + "", name));
				}
			} catch (Exception e) {
                //��������У���Ȼ������ѭ����
				scanner = new Scanner(System.in);
				System.out.println("����������");
				i--;
				continue;
			}
			
		}
		for (People people : peopleList) {
			System.out.println("---��ӭ��ң�" + people.getName());
		}
	}
	
	/**
	 * ����
	 * @param args
	 */
	public void getCard() {
		System.out.println("��ʼ����");
		Random random = new Random();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println("��ң�" + peopleList.get(j).getName() + "����");
				//��ȡ��������ĳ���
				int length = cardList.size();
				//�����ȡһ���������ڵ�����ֵ
				int num = random.nextInt(length);
				//��Ӵ��������Ƹ����
				Card card = cardList.get(num);
				peopleList.get(j).cards.add(card);
				System.out.println(card);
				//���ó�����remove
				cardList.remove(num);
			}
		}
		System.out.println("���ƽ���");
	}
	
	/**
	 * ��ʼ��Ϸ����������������ʤ
	 * @param args
	 */
	public void startGame() {
		System.out.println("��ʼ��Ϸ");
		for (People people : peopleList) {
			Collections.sort(people.cards);
			System.out.println("��ң�"+people.getName()+"��������Ϊ"+people.cards.get(1));
		}
		int judge=peopleList.get(0).cards.get(1).compareTo(peopleList.get(1).cards.get(1));
        String name=judge>0 ? peopleList.get(0).getName():peopleList.get(1).getName();
        System.out.println("-----------��ң�"+name+"��ʤ��--------------");
	}
	
	//չʾ���� 
    public void showCards(){
        System.out.println("-----------�������չʾ--------------");
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
