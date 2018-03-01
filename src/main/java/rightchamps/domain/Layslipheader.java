package rightchamps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Layslipheader.
 */
@Entity
@Table(name = "layslipheader")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Layslipheader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "po_no")
    private String poNo;

    @Column(name = "material_no")
    private String materialNo;

    @Column(name = "material_desc")
    private String materialDesc;

    @Column(name = "main_grid")
    private String mainGrid;

    @Column(name = "order_qty")
    private Integer orderQty;

    @Column(name = "remaining_qty")
    private Integer remainingQty;

    @Column(name = "planned_qty")
    private Integer plannedQty;

    @Column(name = "lay_component")
    private String layComponent;

    @Column(name = "flat")
    private Integer flat;

    @Column(name = "fitted")
    private Integer fitted;

    @Column(name = "pillow")
    private Integer pillow;

    @Column(name = "flat_mat_code")
    private String flatMatCode;

    @Column(name = "fitted_mat_code")
    private String fittedMatCode;

    @Column(name = "pillow_mat_code")
    private String pillowMatCode;

    @Column(name = "pillow_grid")
    private String pillowGrid;

    @Column(name = "flat_to_be_made")
    private String flatToBeMade;

    @Column(name = "fitted_to_be_made")
    private String fittedToBeMade;

    @Column(name = "pillow_to_be_made")
    private String pillowToBeMade;

    @Column(name = "flat_ways")
    private Integer flatWays;

    @Column(name = "fitted_ways")
    private Integer fittedWays;

    @Column(name = "pillow_ways")
    private Integer pillowWays;

    @Column(name = "flat_pieces_per_way")
    private Integer flatPiecesPerWay;

    @Column(name = "fitted_pieces_per_way")
    private Integer fittedPiecesPerWay;

    @Column(name = "pillow_pieces_per_way")
    private Integer pillowPiecesPerWay;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public Layslipheader priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getPoNo() {
        return poNo;
    }

    public Layslipheader poNo(String poNo) {
        this.poNo = poNo;
        return this;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public Layslipheader materialNo(String materialNo) {
        this.materialNo = materialNo;
        return this;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public Layslipheader materialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
        return this;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getMainGrid() {
        return mainGrid;
    }

    public Layslipheader mainGrid(String mainGrid) {
        this.mainGrid = mainGrid;
        return this;
    }

    public void setMainGrid(String mainGrid) {
        this.mainGrid = mainGrid;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public Layslipheader orderQty(Integer orderQty) {
        this.orderQty = orderQty;
        return this;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getRemainingQty() {
        return remainingQty;
    }

    public Layslipheader remainingQty(Integer remainingQty) {
        this.remainingQty = remainingQty;
        return this;
    }

    public void setRemainingQty(Integer remainingQty) {
        this.remainingQty = remainingQty;
    }

    public Integer getPlannedQty() {
        return plannedQty;
    }

    public Layslipheader plannedQty(Integer plannedQty) {
        this.plannedQty = plannedQty;
        return this;
    }

    public void setPlannedQty(Integer plannedQty) {
        this.plannedQty = plannedQty;
    }

    public String getLayComponent() {
        return layComponent;
    }

    public Layslipheader layComponent(String layComponent) {
        this.layComponent = layComponent;
        return this;
    }

    public void setLayComponent(String layComponent) {
        this.layComponent = layComponent;
    }

    public Integer getFlat() {
        return flat;
    }

    public Layslipheader flat(Integer flat) {
        this.flat = flat;
        return this;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public Integer getFitted() {
        return fitted;
    }

    public Layslipheader fitted(Integer fitted) {
        this.fitted = fitted;
        return this;
    }

    public void setFitted(Integer fitted) {
        this.fitted = fitted;
    }

    public Integer getPillow() {
        return pillow;
    }

    public Layslipheader pillow(Integer pillow) {
        this.pillow = pillow;
        return this;
    }

    public void setPillow(Integer pillow) {
        this.pillow = pillow;
    }

    public String getFlatMatCode() {
        return flatMatCode;
    }

    public Layslipheader flatMatCode(String flatMatCode) {
        this.flatMatCode = flatMatCode;
        return this;
    }

    public void setFlatMatCode(String flatMatCode) {
        this.flatMatCode = flatMatCode;
    }

    public String getFittedMatCode() {
        return fittedMatCode;
    }

    public Layslipheader fittedMatCode(String fittedMatCode) {
        this.fittedMatCode = fittedMatCode;
        return this;
    }

    public void setFittedMatCode(String fittedMatCode) {
        this.fittedMatCode = fittedMatCode;
    }

    public String getPillowMatCode() {
        return pillowMatCode;
    }

    public Layslipheader pillowMatCode(String pillowMatCode) {
        this.pillowMatCode = pillowMatCode;
        return this;
    }

    public void setPillowMatCode(String pillowMatCode) {
        this.pillowMatCode = pillowMatCode;
    }

    public String getPillowGrid() {
        return pillowGrid;
    }

    public Layslipheader pillowGrid(String pillowGrid) {
        this.pillowGrid = pillowGrid;
        return this;
    }

    public void setPillowGrid(String pillowGrid) {
        this.pillowGrid = pillowGrid;
    }

    public String getFlatToBeMade() {
        return flatToBeMade;
    }

    public Layslipheader flatToBeMade(String flatToBeMade) {
        this.flatToBeMade = flatToBeMade;
        return this;
    }

    public void setFlatToBeMade(String flatToBeMade) {
        this.flatToBeMade = flatToBeMade;
    }

    public String getFittedToBeMade() {
        return fittedToBeMade;
    }

    public Layslipheader fittedToBeMade(String fittedToBeMade) {
        this.fittedToBeMade = fittedToBeMade;
        return this;
    }

    public void setFittedToBeMade(String fittedToBeMade) {
        this.fittedToBeMade = fittedToBeMade;
    }

    public String getPillowToBeMade() {
        return pillowToBeMade;
    }

    public Layslipheader pillowToBeMade(String pillowToBeMade) {
        this.pillowToBeMade = pillowToBeMade;
        return this;
    }

    public void setPillowToBeMade(String pillowToBeMade) {
        this.pillowToBeMade = pillowToBeMade;
    }

    public Integer getFlatWays() {
        return flatWays;
    }

    public Layslipheader flatWays(Integer flatWays) {
        this.flatWays = flatWays;
        return this;
    }

    public void setFlatWays(Integer flatWays) {
        this.flatWays = flatWays;
    }

    public Integer getFittedWays() {
        return fittedWays;
    }

    public Layslipheader fittedWays(Integer fittedWays) {
        this.fittedWays = fittedWays;
        return this;
    }

    public void setFittedWays(Integer fittedWays) {
        this.fittedWays = fittedWays;
    }

    public Integer getPillowWays() {
        return pillowWays;
    }

    public Layslipheader pillowWays(Integer pillowWays) {
        this.pillowWays = pillowWays;
        return this;
    }

    public void setPillowWays(Integer pillowWays) {
        this.pillowWays = pillowWays;
    }

    public Integer getFlatPiecesPerWay() {
        return flatPiecesPerWay;
    }

    public Layslipheader flatPiecesPerWay(Integer flatPiecesPerWay) {
        this.flatPiecesPerWay = flatPiecesPerWay;
        return this;
    }

    public void setFlatPiecesPerWay(Integer flatPiecesPerWay) {
        this.flatPiecesPerWay = flatPiecesPerWay;
    }

    public Integer getFittedPiecesPerWay() {
        return fittedPiecesPerWay;
    }

    public Layslipheader fittedPiecesPerWay(Integer fittedPiecesPerWay) {
        this.fittedPiecesPerWay = fittedPiecesPerWay;
        return this;
    }

    public void setFittedPiecesPerWay(Integer fittedPiecesPerWay) {
        this.fittedPiecesPerWay = fittedPiecesPerWay;
    }

    public Integer getPillowPiecesPerWay() {
        return pillowPiecesPerWay;
    }

    public Layslipheader pillowPiecesPerWay(Integer pillowPiecesPerWay) {
        this.pillowPiecesPerWay = pillowPiecesPerWay;
        return this;
    }

    public void setPillowPiecesPerWay(Integer pillowPiecesPerWay) {
        this.pillowPiecesPerWay = pillowPiecesPerWay;
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
        Layslipheader layslipheader = (Layslipheader) o;
        if (layslipheader.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), layslipheader.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Layslipheader{" +
            "id=" + getId() +
            ", priority=" + getPriority() +
            ", poNo='" + getPoNo() + "'" +
            ", materialNo='" + getMaterialNo() + "'" +
            ", materialDesc='" + getMaterialDesc() + "'" +
            ", mainGrid='" + getMainGrid() + "'" +
            ", orderQty=" + getOrderQty() +
            ", remainingQty=" + getRemainingQty() +
            ", plannedQty=" + getPlannedQty() +
            ", layComponent='" + getLayComponent() + "'" +
            ", flat=" + getFlat() +
            ", fitted=" + getFitted() +
            ", pillow=" + getPillow() +
            ", flatMatCode='" + getFlatMatCode() + "'" +
            ", fittedMatCode='" + getFittedMatCode() + "'" +
            ", pillowMatCode='" + getPillowMatCode() + "'" +
            ", pillowGrid='" + getPillowGrid() + "'" +
            ", flatToBeMade='" + getFlatToBeMade() + "'" +
            ", fittedToBeMade='" + getFittedToBeMade() + "'" +
            ", pillowToBeMade='" + getPillowToBeMade() + "'" +
            ", flatWays=" + getFlatWays() +
            ", fittedWays=" + getFittedWays() +
            ", pillowWays=" + getPillowWays() +
            ", flatPiecesPerWay=" + getFlatPiecesPerWay() +
            ", fittedPiecesPerWay=" + getFittedPiecesPerWay() +
            ", pillowPiecesPerWay=" + getPillowPiecesPerWay() +
            "}";
    }
}
