package dao.hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "balance")
public class ProductBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private ProductEntity product;
    private int quantity;
}
