package pl.pabjan.bankmanagementsystem.model;


import lombok.Getter;

import javax.persistence.*;

/* Superclass that will not generate a table */
@MappedSuperclass
@Getter
public abstract class AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public int hashCode() {
        if(getId()!=null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }
        if(obj==null) {
            return false;
        }
        if(getClass()!=obj.getClass()) {
            return false;
        }
        AbstractModel other = (AbstractModel) obj;
        if(getId()==null || other.getId() == null) {
            return false;
        }
        return getId().equals(other.getId());
    }
}
