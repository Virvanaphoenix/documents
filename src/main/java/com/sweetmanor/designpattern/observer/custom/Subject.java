package com.sweetmanor.designpattern.observer.custom;

public interface Subject {

	public void registerObserver(Observer o);// 注册观察者

	public void removeObserver(Observer o);// 删除观察者

	public void notifyObservers();// 通知所有观察者

}
