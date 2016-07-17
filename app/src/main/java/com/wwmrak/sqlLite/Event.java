package com.wwmrak.sqlLite;

/**
 * Created by wwmrak on 7/13/2016.
 */
public class Event {

    private int id;

    private int yearEventFrom;
    private int monthEventFrom;
    private int dayEventFrom;

    private int yearEventTo;
    private int monthEventTo;
    private int dayEventTo;

    private int yearRemainder;
    private int monthRemainder;
    private int dayRemainder;
    private int hourRemainder;
    private int minuteReminder;

    private String description;
    private String typeOfEvent;

    public Event() {
    }

    public Event(int yearEventFrom, int monthEventFrom, int dayEventFrom,
                 int yearEventTo, int monthEventTo, int dayEventTo,
                 int yearRemainder, int monthRemainder, int dayRemainder, int hourRemainder, int minuteReminder,
                 String typeOfEvent, String description) {

        this.yearEventFrom = yearEventFrom;
        this.monthEventFrom = monthEventFrom;
        this.dayEventFrom = dayEventFrom;
        this.yearEventTo = yearEventTo;
        this.monthEventTo = monthEventTo;
        this.dayEventTo = dayEventTo;
        this.yearRemainder = yearRemainder;
        this.monthRemainder = monthRemainder;
        this.dayRemainder = dayRemainder;
        this.hourRemainder = hourRemainder;
        this.minuteReminder = minuteReminder;
        this.typeOfEvent = typeOfEvent;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public int getMonthEventTo() {
        return monthEventTo;
    }

    public void setMonthEventTo(int monthEventTo) {
        this.monthEventTo = monthEventTo;
    }

    public int getYearEventFrom() {
        return yearEventFrom;
    }

    public void setYearEventFrom(int yearEventFrom) {
        this.yearEventFrom = yearEventFrom;
    }

    public int getMonthEventFrom() {
        return monthEventFrom;
    }

    public void setMonthEventFrom(int monthEventFrom) {
        this.monthEventFrom = monthEventFrom;
    }

    public int getDayEventFrom() {
        return dayEventFrom;
    }

    public void setDayEventFrom(int dayEventFrom) {
        this.dayEventFrom = dayEventFrom;
    }

    public int getYearEventTo() {
        return yearEventTo;
    }

    public void setYearEventTo(int yearEventTo) {
        this.yearEventTo = yearEventTo;
    }

    public int getDayEventTo() {
        return dayEventTo;
    }

    public void setDayEventTo(int dayEventTo) {
        this.dayEventTo = dayEventTo;
    }

    public int getYearRemainder() {
        return yearRemainder;
    }

    public void setYearRemainder(int yearRemainder) {
        this.yearRemainder = yearRemainder;
    }

    public int getMonthRemainder() {
        return monthRemainder;
    }

    public void setMonthRemainder(int monthRemainder) {
        this.monthRemainder = monthRemainder;
    }

    public int getDayRemainder() {
        return dayRemainder;
    }

    public void setDayRemainder(int dayRemainder) {
        this.dayRemainder = dayRemainder;
    }

    public int getHourRemainder() {
        return hourRemainder;
    }

    public void setHourRemainder(int hourRemainder) {
        this.hourRemainder = hourRemainder;
    }

    public int getMinuteReminder() {
        return minuteReminder;
    }

    public void setMinuteReminder(int minuteReminder) {
        this.minuteReminder = minuteReminder;
    }
}