package com.datauser.goalmanagement.model.goals;

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

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "employeeId", nullable = false)
    private Long employeeId;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "employerId", nullable = false)
    private Long employerId;

    @Column(name = "approveDate")
    private Date approveDate;

    @Column(name = "rejectDate")
    private Date rejectDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    @JsonManagedReference
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employerId", insertable = false, updatable = false)
    @JsonManagedReference
    private Employer employer;

    public Goal() {
    }

    public Goal(Long id,
                String detail,
                Long employeeId,
                Date creationDate,
                Status status,
                Long employerId,
                Date approveDate,
                Date rejectDate) {
        this.id = id;
        this.detail = detail;
        this.employeeId = employeeId;
        this.creationDate = creationDate;
        this.status = status;
        this.employerId = employerId;
        this.approveDate = approveDate;
        this.rejectDate = rejectDate;
    }

    public Goal(String name,
                String detail,
                Long employeeId,
                Date creationDate,
                Status status,
                Long employerId,
                Date approveDate,
                Date rejectDate
                ) {
        this.name = name;
        this.detail = detail;
        this.employeeId = employeeId;
        this.creationDate = creationDate;
        this.status = status;
        this.employerId = employerId;
        this.approveDate = approveDate;
        this.rejectDate = rejectDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return id.equals(goal.id) && name.equals(goal.name) && detail.equals(goal.detail) &&
                employeeId.equals(goal.employeeId) && creationDate.equals(goal.creationDate) &&
                status == goal.status && employerId.equals(goal.employerId) &&
                Objects.equals(approveDate, goal.approveDate) && Objects.equals(rejectDate, goal.rejectDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, detail, employeeId, creationDate, status, employerId, approveDate, rejectDate);
    }
}
