package rightchamps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LayslipRollDetails.
 */
@Entity
@Table(name = "layslip_roll_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LayslipRollDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "po_no")
    private String poNo;

    @Column(name = "com_material_code")
    private String comMaterialCode;

    @Column(name = "roll_number")
    private String rollNumber;

    @Column(name = "roll_qty")
    private Integer rollQty;

    @Column(name = "grade")
    private String grade;

    @Column(name = "shade")
    private String shade;

    @Column(name = "flat_start")
    private Integer flatStart;

    @Column(name = "fitted_start")
    private Integer fittedStart;

    @Column(name = "pillow_start")
    private Integer pillowStart;

    @Column(name = "flat_end")
    private Integer flatEnd;

    @Column(name = "fitted_end")
    private Integer fittedEnd;

    @Column(name = "pillow_end")
    private Integer pillowEnd;

    @Column(name = "full_length")
    private Integer fullLength;

    @Column(name = "half_length")
    private Integer halfLength;

    @Column(name = "endbit_pcs")
    private Integer endbitPcs;

    @Column(name = "est_pillows")
    private Integer estPillows;

    @Column(name = "pillow_prorata")
    private Integer pillowProrata;

    @Column(name = "rejected_fabric")
    private Integer rejectedFabric;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoNo() {
        return poNo;
    }

    public LayslipRollDetails poNo(String poNo) {
        this.poNo = poNo;
        return this;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getComMaterialCode() {
        return comMaterialCode;
    }

    public LayslipRollDetails comMaterialCode(String comMaterialCode) {
        this.comMaterialCode = comMaterialCode;
        return this;
    }

    public void setComMaterialCode(String comMaterialCode) {
        this.comMaterialCode = comMaterialCode;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public LayslipRollDetails rollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
        return this;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Integer getRollQty() {
        return rollQty;
    }

    public LayslipRollDetails rollQty(Integer rollQty) {
        this.rollQty = rollQty;
        return this;
    }

    public void setRollQty(Integer rollQty) {
        this.rollQty = rollQty;
    }

    public String getGrade() {
        return grade;
    }

    public LayslipRollDetails grade(String grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getShade() {
        return shade;
    }

    public LayslipRollDetails shade(String shade) {
        this.shade = shade;
        return this;
    }

    public void setShade(String shade) {
        this.shade = shade;
    }

    public Integer getFlatStart() {
        return flatStart;
    }

    public LayslipRollDetails flatStart(Integer flatStart) {
        this.flatStart = flatStart;
        return this;
    }

    public void setFlatStart(Integer flatStart) {
        this.flatStart = flatStart;
    }

    public Integer getFittedStart() {
        return fittedStart;
    }

    public LayslipRollDetails fittedStart(Integer fittedStart) {
        this.fittedStart = fittedStart;
        return this;
    }

    public void setFittedStart(Integer fittedStart) {
        this.fittedStart = fittedStart;
    }

    public Integer getPillowStart() {
        return pillowStart;
    }

    public LayslipRollDetails pillowStart(Integer pillowStart) {
        this.pillowStart = pillowStart;
        return this;
    }

    public void setPillowStart(Integer pillowStart) {
        this.pillowStart = pillowStart;
    }

    public Integer getFlatEnd() {
        return flatEnd;
    }

    public LayslipRollDetails flatEnd(Integer flatEnd) {
        this.flatEnd = flatEnd;
        return this;
    }

    public void setFlatEnd(Integer flatEnd) {
        this.flatEnd = flatEnd;
    }

    public Integer getFittedEnd() {
        return fittedEnd;
    }

    public LayslipRollDetails fittedEnd(Integer fittedEnd) {
        this.fittedEnd = fittedEnd;
        return this;
    }

    public void setFittedEnd(Integer fittedEnd) {
        this.fittedEnd = fittedEnd;
    }

    public Integer getPillowEnd() {
        return pillowEnd;
    }

    public LayslipRollDetails pillowEnd(Integer pillowEnd) {
        this.pillowEnd = pillowEnd;
        return this;
    }

    public void setPillowEnd(Integer pillowEnd) {
        this.pillowEnd = pillowEnd;
    }

    public Integer getFullLength() {
        return fullLength;
    }

    public LayslipRollDetails fullLength(Integer fullLength) {
        this.fullLength = fullLength;
        return this;
    }

    public void setFullLength(Integer fullLength) {
        this.fullLength = fullLength;
    }

    public Integer getHalfLength() {
        return halfLength;
    }

    public LayslipRollDetails halfLength(Integer halfLength) {
        this.halfLength = halfLength;
        return this;
    }

    public void setHalfLength(Integer halfLength) {
        this.halfLength = halfLength;
    }

    public Integer getEndbitPcs() {
        return endbitPcs;
    }

    public LayslipRollDetails endbitPcs(Integer endbitPcs) {
        this.endbitPcs = endbitPcs;
        return this;
    }

    public void setEndbitPcs(Integer endbitPcs) {
        this.endbitPcs = endbitPcs;
    }

    public Integer getEstPillows() {
        return estPillows;
    }

    public LayslipRollDetails estPillows(Integer estPillows) {
        this.estPillows = estPillows;
        return this;
    }

    public void setEstPillows(Integer estPillows) {
        this.estPillows = estPillows;
    }

    public Integer getPillowProrata() {
        return pillowProrata;
    }

    public LayslipRollDetails pillowProrata(Integer pillowProrata) {
        this.pillowProrata = pillowProrata;
        return this;
    }

    public void setPillowProrata(Integer pillowProrata) {
        this.pillowProrata = pillowProrata;
    }

    public Integer getRejectedFabric() {
        return rejectedFabric;
    }

    public LayslipRollDetails rejectedFabric(Integer rejectedFabric) {
        this.rejectedFabric = rejectedFabric;
        return this;
    }

    public void setRejectedFabric(Integer rejectedFabric) {
        this.rejectedFabric = rejectedFabric;
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
        LayslipRollDetails layslipRollDetails = (LayslipRollDetails) o;
        if (layslipRollDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), layslipRollDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LayslipRollDetails{" +
            "id=" + getId() +
            ", poNo='" + getPoNo() + "'" +
            ", comMaterialCode='" + getComMaterialCode() + "'" +
            ", rollNumber='" + getRollNumber() + "'" +
            ", rollQty=" + getRollQty() +
            ", grade='" + getGrade() + "'" +
            ", shade='" + getShade() + "'" +
            ", flatStart=" + getFlatStart() +
            ", fittedStart=" + getFittedStart() +
            ", pillowStart=" + getPillowStart() +
            ", flatEnd=" + getFlatEnd() +
            ", fittedEnd=" + getFittedEnd() +
            ", pillowEnd=" + getPillowEnd() +
            ", fullLength=" + getFullLength() +
            ", halfLength=" + getHalfLength() +
            ", endbitPcs=" + getEndbitPcs() +
            ", estPillows=" + getEstPillows() +
            ", pillowProrata=" + getPillowProrata() +
            ", rejectedFabric=" + getRejectedFabric() +
            "}";
    }
}
