package com.vms.medxbid.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sub_catogery")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id", nullable = false)
    private Long id;

    @Column(name = "subcategory_name", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;
}
