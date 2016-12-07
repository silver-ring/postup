package com.appzone.postup.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Mohamed Morsy
 * 
 * comment entity representer
 * 
 */
@Entity
public class CommentEntity implements PostUpEntity {

    @Id
    @GeneratedValue
    private long id;
    private String text;
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static enum Status {
        WAITING_FOR_APPROVE, APPROVED, REJECTED;
    }

}
