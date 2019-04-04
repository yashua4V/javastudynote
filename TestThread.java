package com.yashua.day1;

public class TestThrowd {
	public static void main(String[] arg) {
		Shop icecreamShop = new Shop();
		Productor productor = new Productor(icecreamShop);
		Customer eatMan = new Customer(icecreamShop);
		
		Thread t1 = new Thread(productor);
		Thread t2 = new Thread(eatMan);
		
		t1.setName("北极熊");
		t2.setName("小企鹅");
		
		t1.start();
		t2.start();
	}

}
class Shop  {
	int icecream;
	public synchronized void addIcecream() {
		if (icecream < 20) {
			icecream ++;
			System.out.println(Thread.currentThread().getName() + "拉出来了第" + icecream + "个冰淇淋");
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notifyAll();
		}
		else {
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	public synchronized void eatIcecream() {
		if (icecream > 0) {
			System.out.println(Thread.currentThread().getName() + "吃掉了第" + icecream + "坨冰淇淋");
			icecream --;
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notifyAll();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
}

class Customer implements Runnable {
	Shop shop;
	public Customer (Shop shop) {
		this.shop = shop;
		}
	public void run() {
		while(true) {
		shop.eatIcecream();
		}
	}
}
class Productor implements Runnable {
	Shop shop;
	public Productor (Shop shop) {
		this.shop = shop;
	}
	public void run() {
		while(true) {
			shop.addIcecream();

		}	
	}
}
