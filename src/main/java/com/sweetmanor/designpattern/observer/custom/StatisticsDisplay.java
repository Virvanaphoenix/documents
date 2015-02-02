package com.sweetmanor.designpattern.observer.custom;

import com.sweetmanor.designpattern.observer.DisplayElement;

public class StatisticsDisplay implements Observer, DisplayElement {
	private Subject weatherData;
	private float sumTemperature;
	private int count;
	private float maxTemperature;
	private float minTemperature;

	public StatisticsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
		count = 0;
		sumTemperature = 0;
		maxTemperature = 0;
		minTemperature = 1000;
	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + sumTemperature
				/ count + "/" + maxTemperature + "/" + minTemperature);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		sumTemperature += temperature;
		count++;
		if (temperature > maxTemperature)
			maxTemperature = temperature;
		if (temperature < minTemperature)
			minTemperature = temperature;
		display();
	}

}
