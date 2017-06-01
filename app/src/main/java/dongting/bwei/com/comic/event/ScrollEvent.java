package dongting.bwei.com.comic.event;

/**
 * ${董婷}
 * 2017/5/2.
 */

public class ScrollEvent {
    public  boolean up;

    public ScrollEvent(boolean up) {
        this.up = up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isUp() {
        return up;
    }

    @Override
    public String toString() {
        return "ScrollEvent{" +
                "up=" + up +
                '}';
    }
}
