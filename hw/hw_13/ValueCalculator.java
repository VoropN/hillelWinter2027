package hw_13;

import java.util.Arrays;

public class ValueCalculator {
    private final int size = 1_000_000;
    private final int halfSize = size / 2;
    private final float[] array = new float[size];

    public int getLength() {
        return size;
    }

    public int getHalfSize() {
        return halfSize;
    }

    public float[] getRealNumbers() {
        return array.clone();
    }

    public float[] getPositiveNumbers() {
        int positiveCount = 0;
        for (float value : array) {
            if (value > 0) {
                positiveCount++;
            }
        }

        float[] positiveNumbers = new float[positiveCount];
        int index = 0;
        for (float value : array) {
            if (value > 0) {
                positiveNumbers[index++] = value;
            }
        }
        return positiveNumbers;
    }

    public float[] getFirstHalf() {
        return Arrays.copyOfRange(array, 0, halfSize);
    }

    public float[] getSecondHalf() {
        return Arrays.copyOfRange(array, halfSize, size);
    }

    public void run() {
        long start = System.currentTimeMillis();
        float[] firstHalf = new float[halfSize];
        float[] secondHalf = new float[halfSize];
        Arrays.fill(array, 1f);
        System.arraycopy(array, 0, firstHalf, 0, halfSize);
        System.arraycopy(array, halfSize, secondHalf, 0, halfSize);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < firstHalf.length; i++) {
                firstHalf[i] = (float) (firstHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < secondHalf.length; i++) {
                secondHalf[i] = (float) (secondHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        System.arraycopy(firstHalf, 0, array, 0, halfSize);
        System.arraycopy(secondHalf, 0, array, halfSize, halfSize);
        System.out.printf("Time spent: %d ms%n", System.currentTimeMillis() - start);
    }
}
