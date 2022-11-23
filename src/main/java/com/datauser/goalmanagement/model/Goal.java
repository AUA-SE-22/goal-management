package com.datauser.goalmanagement.model;

import com.datauser.goalmanagement.utils.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "goal")
public class Goal implements Serializable {

    @Serial
    private static final long serialVersionUID = -7689263744115202288L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "employee_id", nullable = false, insertable = false, updatable = false)
    private Long employeeId;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "employer_id", nullable = false, insertable = false, updatable = false)
    private Long employerId;

    @Column(name = "approve_date")
    private Date approveDate;

    @Column(name = "reject_date")
    private Date rejectDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonManagedReference
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    @JsonManagedReference
    private Employer employer;

    public Goal() {
    }

    public Goal(Long id, String detail, Long employeeId, Date creationDate, Status status, Long employerId,
                Date approveDate, Date rejectDate, Employee employee, Employer employer) {
        this.id = id;
        this.detail = detail;
        this.employeeId = employeeId;
        this.creationDate = creationDate;
        this.status = status;
        this.employerId = employerId;
        this.approveDate = approveDate;
        this.rejectDate = rejectDate;
        this.employee = employee;
        this.employer = employer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Date rejectDate) {
        this.rejectDate = rejectDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return id.equals(goal.id) && detail.equals(goal.detail) && employeeId.equals(goal.employeeId) &&
                creationDate.equals(goal.creationDate) && status == goal.status && employerId.equals(goal.employerId) &&
                Objects.equals(approveDate, goal.approveDate) && Objects.equals(rejectDate, goal.rejectDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, detail, employeeId, creationDate, status, employerId, approveDate, rejectDate);
    }
}
