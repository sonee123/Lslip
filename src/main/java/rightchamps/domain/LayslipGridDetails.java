package rightchamps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LayslipGridDetails.
 */
@Entity
@Table(name = "layslip_grid_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LayslipGridDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lay_slip_no")
    private String laySlipNo;

    @Column(name = "sub_layslip_no")
    private String subLayslipNo;

    @Column(name = "main_grid")
    private String mainGrid;

    @Column(name = "pillows")
    private Integer pillows;

    @Column(name = "wast_fabric")
    private Integer wastFabric;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaySlipNo() {
        return laySlipNo;
    }

    public LayslipGridDetails laySlipNo(String laySlipNo) {
        this.laySlipNo = laySlipNo;
        return this;
    }

    public void setLaySlipNo(String laySlipNo) {
        this.laySlipNo = laySlipNo;
    }

    public String getSubLayslipNo() {
        return subLayslipNo;
    }

    public LayslipGridDetails subLayslipNo(String subLayslipNo) {
        this.subLayslipNo = subLayslipNo;
        return this;
    }

    public void setSubLayslipNo(String subLayslipNo) {
        this.subLayslipNo = subLayslipNo;
    }

    public String getMainGrid() {
        return mainGrid;
    }

    public LayslipGridDetails mainGrid(String mainGrid) {
        this.mainGrid = mainGrid;
        return this;
    }

    public void setMainGrid(String mainGrid) {
        this.mainGrid = mainGrid;
    }

    public Integer getPillows() {
        return pillows;
    }

    public LayslipGridDetails pillows(Integer pillows) {
        this.pillows = pillows;
        return this;
    }

    public void setPillows(Integer pillows) {
        this.pillows = pillows;
    }

    public Integer getWastFabric() {
        return wastFabric;
    }

    public LayslipGridDetails wastFabric(Integer wastFabric) {
        this.wastFabric = wastFabric;
        return this;
    }

    public void setWastFabric(Integer wastFabric) {
        this.wastFabric = wastFabric;
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
        LayslipGridDetails layslipGridDetails = (LayslipGridDetails) o;
        if (layslipGridDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), layslipGridDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LayslipGridDetails{" +
            "id=" + getId() +
            ", laySlipNo='" + getLaySlipNo() + "'" +
            ", subLayslipNo='" + getSubLayslipNo() + "'" +
            ", mainGrid='" + getMainGrid() + "'" +
            ", pillows=" + getPillows() +
            ", wastFabric=" + getWastFabric() +
            "}";
    }
}
