package rightchamps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WorkCode.
 */
@Entity
@Table(name = "work_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WorkCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private WorkCenterMaster wcCode1;

    @ManyToOne
    private WorkCenterMaster wcCode2;

    @ManyToOne
    private WorkCenterMaster wcCode3;

    @ManyToOne
    private WorkCenterMaster wcCode4;

    @ManyToOne
    private WorkCenterMaster wcCode5;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public WorkCode user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkCenterMaster getWcCode1() {
        return wcCode1;
    }

    public WorkCode wcCode1(WorkCenterMaster workCenterMaster) {
        this.wcCode1 = workCenterMaster;
        return this;
    }

    public void setWcCode1(WorkCenterMaster workCenterMaster) {
        this.wcCode1 = workCenterMaster;
    }

    public WorkCenterMaster getWcCode2() {
        return wcCode2;
    }

    public WorkCode wcCode2(WorkCenterMaster workCenterMaster) {
        this.wcCode2 = workCenterMaster;
        return this;
    }

    public void setWcCode2(WorkCenterMaster workCenterMaster) {
        this.wcCode2 = workCenterMaster;
    }

    public WorkCenterMaster getWcCode3() {
        return wcCode3;
    }

    public WorkCode wcCode3(WorkCenterMaster workCenterMaster) {
        this.wcCode3 = workCenterMaster;
        return this;
    }

    public void setWcCode3(WorkCenterMaster workCenterMaster) {
        this.wcCode3 = workCenterMaster;
    }

    public WorkCenterMaster getWcCode4() {
        return wcCode4;
    }

    public WorkCode wcCode4(WorkCenterMaster workCenterMaster) {
        this.wcCode4 = workCenterMaster;
        return this;
    }

    public void setWcCode4(WorkCenterMaster workCenterMaster) {
        this.wcCode4 = workCenterMaster;
    }

    public WorkCenterMaster getWcCode5() {
        return wcCode5;
    }

    public WorkCode wcCode5(WorkCenterMaster workCenterMaster) {
        this.wcCode5 = workCenterMaster;
        return this;
    }

    public void setWcCode5(WorkCenterMaster workCenterMaster) {
        this.wcCode5 = workCenterMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkCode workCode = (WorkCode) o;
        if (workCode.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workCode.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkCode{" +
            "id=" + getId() +
            "}";
    }
}
