package xyz.devyang.sw.core;

import java.io.Serializable;

/**
 * key for shortest path
 *
 * Created by YangYu on 11/4/15.
 */
public class HashKey implements Serializable {

    private final Node x; // source
    private final Node y; // destination

    public HashKey(Node x, Node y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashKey hashKey = (HashKey) o;

        if (x != null ? !x.equals(hashKey.x) : hashKey.x != null) return false;
        return !(y != null ? !y.equals(hashKey.y) : hashKey.y != null);

    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HashKey{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
