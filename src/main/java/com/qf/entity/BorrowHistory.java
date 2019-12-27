package com.qf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BorrowHistory {
    private Integer historyId;

    private Integer readerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date borrowTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date repayTime;

    private Byte renew;

    private Byte overdue;

    private Float fine;

    private Integer bookId;

    private Integer isDelete;


    /**
     * 无 isDelete,Time 类型构造
     *
     * @param historyId
     * @param readerId
     * @param renew
     * @param overdue
     * @param fine
     * @param bookId
     */
    public BorrowHistory(Integer historyId, Integer readerId, Byte renew, Byte overdue, Float fine, Integer bookId) {
        this.historyId = historyId;
        this.readerId = readerId;
        this.renew = renew;
        this.overdue = overdue;
        this.fine = fine;
        this.bookId = bookId;
    }

    /**
     * 无 isDelete 构造
     *
     * @param historyId
     * @param readerId
     * @param borrowTime
     * @param repayTime
     * @param renew
     * @param overdue
     * @param fine
     * @param bookId
     */
    public BorrowHistory(Integer historyId, Integer readerId, Date borrowTime, Date repayTime, Byte renew, Byte overdue, Float fine, Integer bookId) {
        this.historyId = historyId;
        this.readerId = readerId;
        this.borrowTime = borrowTime;
        this.repayTime = repayTime;
        this.renew = renew;
        this.overdue = overdue;
        this.fine = fine;
        this.bookId = bookId;
    }


    public BorrowHistory() {
    }

    /**
     * 全参构造
     *
     * @param historyId
     * @param readerId
     * @param borrowTime
     * @param repayTime
     * @param renew
     * @param overdue
     * @param fine
     * @param bookId
     * @param isDelete
     */
    public BorrowHistory(Integer historyId, Integer readerId, Date borrowTime, Date repayTime, Byte renew, Byte overdue, Float fine, Integer bookId, Integer isDelete) {
        this.historyId = historyId;
        this.readerId = readerId;
        this.borrowTime = borrowTime;
        this.repayTime = repayTime;
        this.renew = renew;
        this.overdue = overdue;
        this.fine = fine;
        this.bookId = bookId;
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "BorrowHistory{" +
                "historyId=" + historyId +
                ", readerId=" + readerId +
                ", borrowTime=" + borrowTime +
                ", repayTime=" + repayTime +
                ", renew=" + renew +
                ", overdue=" + overdue +
                ", fine=" + fine +
                ", bookId=" + bookId +
                ", isDelete=" + isDelete +
                '}';
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Byte getRenew() {
        return renew;
    }

    public void setRenew(Byte renew) {
        this.renew = renew;
    }

    public Byte getOverdue() {
        return overdue;
    }

    public void setOverdue(Byte overdue) {
        this.overdue = overdue;
    }

    public Float getFine() {
        return fine;
    }

    public void setFine(Float fine) {
        this.fine = fine;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}