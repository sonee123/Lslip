package rightchamps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WorkCenterMaster.
 */
@Entity
@Table(name = "work_center_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WorkCenterMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "work_center_master_layslip",
               joinColumns = @JoinColumn(name="work_center_masters_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="layslips_id", referencedColumnName="id"))
    private Set<LayslipKeyHeader> layslips = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public WorkCenterMaster title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<LayslipKeyHeader> getLayslips() {
        return layslips;
    }

    public WorkCenterMaster layslips(Set<LayslipKeyHeader> layslipKeyHeaders) {
        this.layslips = layslipKeyHeaders;
        return this;
    }

    public WorkCenterMaster addLayslip(LayslipKeyHeader layslipKeyHeader) {
        this.layslips.add(layslipKeyHeader);
        return this;
    }

    public WorkCenterMaster removeLayslip(LayslipKeyHeader layslipKeyHeader) {
        this.layslips.remove(layslipKeyHeader);
        return this;
    }

    public void setLayslips(Set<LayslipKeyHeader> layslipKeyHeaders) {
        this.layslips = layslipKeyHeaders;
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
        WorkCenterMaster workCenterMaster = (WorkCenterMaster) o;
        if (workCenterMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workCenterMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkCenterMaster{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
