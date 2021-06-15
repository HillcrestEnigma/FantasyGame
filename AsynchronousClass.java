public class AsynchronousClass implements Asynchronous {
    private int lastUpdate = 0;

    private void tick() {
    }

    public void update() {
        long timeDifference = currentTime() - lastUpdate;
        lastUpdate = currentTime();
        for (int i=0; i < timeDifference / 500L; i++) {
            tick();
        }
    }

    public static long currentTime() {
        // https://stackoverflow.com/questions/732034/getting-unixtime-in-java
        return System.currentTimeMillis();
    }

    public Asynchronous() {
        lastUpdate = currentTime();
    }
}
