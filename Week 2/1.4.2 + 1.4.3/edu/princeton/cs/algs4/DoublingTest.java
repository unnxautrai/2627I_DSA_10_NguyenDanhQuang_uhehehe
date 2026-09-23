package edu.princeton.cs.algs4;

import java.util.ArrayList;

public class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;

    private DoublingTest() { }

    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        int ignore = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    /**
     * Vẽ lại đồ thị chuẩn (Standard Plot: N vs Time) với tỷ lệ tự điều chỉnh
     */
    private static void drawStandardPlot(ArrayList<Integer> nList, ArrayList<Double> timeList) {
        StdDraw.clear();
        
        int lastIdx = nList.size() - 1;
        double maxN = nList.get(lastIdx);
        double maxTime = timeList.get(lastIdx);

        // Tránh lỗi chia cho 0 nếu thời gian chạy quá nhỏ (= 0.0)
        if (maxTime <= 0) maxTime = 0.1;

        // Cấu hình tỷ lệ trục X và Y có chừa biên 10% để đồ thị nằm gọn trong khung
        StdDraw.setXscale(-0.05 * maxN, maxN * 1.1);
        StdDraw.setYscale(-0.05 * maxTime, maxTime * 1.1);

        // Vẽ hệ trục tọa độ đơn giản
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.002);
        StdDraw.line(0, 0, maxN * 1.05, 0);
        StdDraw.line(0, 0, 0, maxTime * 1.05);

        // Vẽ các điểm và các đoạn nối
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        for (int i = 0; i < nList.size(); i++) {
            double x = nList.get(i);
            double y = timeList.get(i);

            // Vẽ điểm dữ liệu
            StdDraw.setPenRadius(0.015);
            StdDraw.point(x, y);

            // Nối đường giữa các điểm liên tiếp
            if (i > 0) {
                double prevX = nList.get(i - 1);
                double prevY = timeList.get(i - 1);
                StdDraw.setPenRadius(0.005);
                StdDraw.line(prevX, prevY, x, y);
            }
        }
    }

    /**
     * Vẽ đồ thị Log-Log Plot (lg(N) vs lg(Time))
     */
    private static void drawLogLogPlot(ArrayList<Integer> nList, ArrayList<Double> timeList) {
        // Chỉ vẽ các điểm có Time > 0 (vì log(0) không xác định)
        ArrayList<Double> logN = new ArrayList<>();
        ArrayList<Double> logT = new ArrayList<>();

        for (int i = 0; i < nList.size(); i++) {
            if (timeList.get(i) > 0) {
                logN.add(Math.log(nList.get(i)) / Math.log(2)); // lg(N)
                logT.add(Math.log(timeList.get(i)) / Math.log(2)); // lg(T)
            }
        }

        if (logN.isEmpty()) return;

        double minX = logN.get(0), maxX = logN.get(logN.size() - 1);
        double minY = logT.get(0), maxY = logT.get(logT.size() - 1);

        if (minX == maxX) maxX += 1.0;
        if (minY == maxY) maxY += 1.0;

        StdDraw.setXscale(minX - 0.5, maxX + 0.5);
        StdDraw.setYscale(minY - 0.5, maxY + 0.5);

        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < logN.size(); i++) {
            double x = logN.get(i);
            double y = logT.get(i);

            StdDraw.setPenRadius(0.015);
            StdDraw.point(x, y);

            if (i > 0) {
                StdDraw.setPenRadius(0.005);
                StdDraw.line(logN.get(i - 1), logT.get(i - 1), x, y);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> nList = new ArrayList<>();
        ArrayList<Double> timeList = new ArrayList<>();

        // Thiết lập kích thước cửa sổ vẽ
        StdDraw.setCanvasSize(600, 600);

        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f\n", n, time);

            nList.add(n);
            timeList.add(time);

            // Cập nhật lại đồ thị chuẩn
            drawStandardPlot(nList, timeList);
            
            // Nếu muốn dùng đồ thị Log-Log, đổi thành:
            // drawLogLogPlot(nList, timeList);
        }
    }
}