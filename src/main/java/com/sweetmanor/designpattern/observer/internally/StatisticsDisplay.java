package com.sweetmanor.designpattern.observer.internally;

import java.util.Observable;
import java.util.Observer;

import com.sweetmanor.designpattern.observer.DisplayElement;

public class StatisticsDisplay implements Observer, DisplayElement {
	private Observable observable;
	private float sumTemperature;
	private int count;
	private float maxTemperature;
	private float minTemperature;

	public StatisticsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
		minTemperature = 1000;
	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + sumTemperature
				/ count + "/" + maxTemperature + "/" + minTemperature);
	}

	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) observable;
			float temperature = weatherData.getTemperature();
			sumTemperature += temperature;
			count++;
			if (temperature > maxTemperature)
				maxTemperature = temperature;
			if (temperature < minTemperature)
				minTemperature = temperature;
			display();
		}
	}

}
