package isel.MoP.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private boolean unchecked = false;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUnchecked() {
        return unchecked;
    }

    public void setUnchecked(boolean unchecked) {
        this.unchecked = unchecked;
    }

    @Override
    public String toString() {
        return "name = " + name + ", unchecked = " + unchecked;
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
