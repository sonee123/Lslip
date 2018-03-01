package rightchamps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LayslipKeyHeader.
 */
@Entity
@Table(name = "layslip_key_header")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LayslipKeyHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Layslipheader layslipheader;

    @ManyToOne
    private LayslipRollDetails layslipRoll;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Layslipheader getLayslipheader() {
        return layslipheader;
    }

    public LayslipKeyHeader layslipheader(Layslipheader layslipheader) {
        this.layslipheader = layslipheader;
        return this;
    }

    public void setLayslipheader(Layslipheader layslipheader) {
        this.layslipheader = layslipheader;
    }

    public LayslipRollDetails getLayslipRoll() {
        return layslipRoll;
    }

    public LayslipKeyHeader layslipRoll(LayslipRollDetails layslipRollDetails) {
        this.layslipRoll = layslipRollDetails;
        return this;
    }

    public void setLayslipRoll(LayslipRollDetails layslipRollDetails) {
        this.layslipRoll = layslipRollDetails;
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
        LayslipKeyHeader layslipKeyHeader = (LayslipKeyHeader) o;
        if (layslipKeyHeader.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), layslipKeyHeader.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LayslipKeyHeader{" +
            "id=" + getId() +
            "}";
    }
}
