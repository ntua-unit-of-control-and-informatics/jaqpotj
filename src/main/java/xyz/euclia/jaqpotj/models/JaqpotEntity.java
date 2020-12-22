package xyz.euclia.jaqpotj.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 *
 * @author Pantelispanka
 *
 */
public class JaqpotEntity {

    /**
    * Identifier of the entity.
    */
    private String id;
    /**
     * Meta data of the entity.
     */
    private MetaInfo meta;
    /**
     * Set of ontological characterizations.
     */
    private Set<String> ontologicalClasses;

    private Boolean visible;

    private Boolean temporary;

    private Boolean featured;

    public JaqpotEntity() {
    }

    public JaqpotEntity(String id) {
        this.id = id;
    }

    public JaqpotEntity(JaqpotEntity other) {
        if (other == null) {
            throw new NullPointerException("Cannot copy null object");
        }
        this.id = other.id;
        this.meta = other.meta != null ? new MetaInfo(meta) : null;
        this.ontologicalClasses = other.ontologicalClasses != null ? new HashSet<>(other.ontologicalClasses) : null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public void setMeta(MetaInfo meta) {
        this.meta = meta;
    }

    public Set<String> getOntologicalClasses() {
        return ontologicalClasses;
    }

    public void setOntologicalClasses(Set<String> ontologicalClasses) {
        this.ontologicalClasses = ontologicalClasses;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JaqpotEntity other = (JaqpotEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
