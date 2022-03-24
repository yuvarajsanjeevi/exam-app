package com.example.demo.source.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;


/**
 * Added constructor for setting the values
 * Added default constructor for JPA
 * UpdateTimestamp for auto set date time on update
 * implemented equals and hashcode
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;
    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (!(object instanceof Customer)) return false;

        Customer other = (Customer) object;
        return Objects.equals(getId(), other.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
