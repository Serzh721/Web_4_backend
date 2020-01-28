package app.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POINTZ")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double x;
    private Double y;
    private Double r;
    private String owner;
    private Boolean checked;

    public Point(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}