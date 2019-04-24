package org.psalms.entities;

import javax.persistence.*;

@Entity
@Table(name = "PSALM")
public class Psalm {

    @Id
    @GeneratedValue
    public long id;

    @Column(name = "psalm_number")
    public int psalmNumber;

    @Column(name = "reading_order")
    public int readingOrder;

    @Column(name = "read")
    public boolean read;


}
