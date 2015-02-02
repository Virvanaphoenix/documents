package com.sweetmanor.designpattern.observer.custom;

public interface Observer {

	// 更新观察者状态，此处使用了简单参数，在实际设计中是不可取的，因为每一次参数的增加都要改变此接口
	public void update(float temperature, float humidity, float pressure);

}
