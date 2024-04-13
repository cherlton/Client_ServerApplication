/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.entity;

/**
 *
 * @author Student
 */
public class Message {
    private String text;
    private String originator;

    public Message(String text, String originator) {
        this.text = text;
        this.originator = originator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    @Override
    public String toString() {
        return "Message{" + "text=" + text + ", originator=" + originator + '}';
    }
   
}
