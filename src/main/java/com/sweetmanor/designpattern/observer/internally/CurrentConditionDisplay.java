package com.sweetmanor.designpattern.observer.internally;

import java.util.Observable;
import java.util.Observer;

import com.sweetmanor.designpattern.observer.DisplayElement;

public class CurrentConditionDisplay implements Observer, DisplayElement {
	private Observable observable;
	private float temperature;
	private float humidity;

	public CurrentConditionDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature
				+ "F degrees and " + humidity + "% humidity");
	}

	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) observable;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}

}
