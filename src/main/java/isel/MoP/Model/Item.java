package isel.MoP.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private boolean checked = false;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setCheck(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
