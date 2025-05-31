package crm.personnal.scrimlab.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "title")
public class TitleEntity {
    @Id
    @Column(name = "title_name", nullable = false, length = Integer.MAX_VALUE)
    private String titleName;

    @NotNull
    @Column(name = "title_color", nullable = false, length = Integer.MAX_VALUE)
    private String titleColor;

    @ColumnDefault("false")
    @Column(name = "is_neon")
    private Boolean isNeon;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public Boolean getIsNeon() {
        return isNeon;
    }

    public void setIsNeon(Boolean isNeon) {
        this.isNeon = isNeon;
    }

}