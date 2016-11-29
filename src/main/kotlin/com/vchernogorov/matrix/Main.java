package com.vchernogorov.matrix;

public class Main {

    final static int V = 7;

    public static void main(String[] args) {
        double leftPart = 1;
        double rightPart = 10;
        int countPartitions = 10;
        double one = rightPart - leftPart;
        one /= countPartitions - 1;
        double[] xArray = new double[countPartitions];
        xArray[0] = leftPart;


        for (int i = 1; i < countPartitions; i++) {
            xArray[i] = xArray[i - 1] + one;
        }

        System.out.println("Значения x:");
        printArray(xArray);
        System.out.println();

        double[] yPreciseArray = new double[xArray.length];

        for (int i = 0; i < yPreciseArray.length; i++) {
            yPreciseArray[i] = GetYPrecise(xArray[i]);
        }

        double[] yArray = new double[xArray.length];
        yArray[0] = V + 2;

        for (int i = 1; i < yArray.length; i++) {
            double h = xArray[i] - xArray[i - 1];
            yArray[i] = getYEuler(xArray[i-1], yArray[i - 1], h);                           //Метод Эйлера
            yArray[i] = getYModifiedEuler(xArray[i - 1], xArray[i], yArray[i - 1], h);      //Модифицированный метод Эйлера
            yArray[i] = getPredictCorrector(xArray[i - 1], xArray[i], yArray[i - 1], h);    //Метод предиктора-корректора (улучшенный)
        }

        System.out.println("Значения точных y:");
        printArray(yPreciseArray);
        System.out.println();

        System.out.println("Значения посчитанных y:");
        printArray(yArray);
        System.out.println();

        System.out.println("Разница между значениями y");
        for (int i = 0; i < yArray.length; i++) {
            System.out.print(String.format("%(.2f | ", (Math.abs(yArray[i] - yPreciseArray[i]))));
        }
        System.out.println();
    }

    public static double GetYPrecise(double x) {
        return V * Math.pow(x, 3.0) + Math.pow(x, 2.0) + x;
    }

    public static double getYEuler(double x, double y, double h) {
        return y + h * getF(x, y);
    }

    public static double getYModifiedEuler(double x1, double x2, double y, double h) {
        double x = (x1 + x2) / 2;
        double yNew = y + (h / 2) * getF(x1, y);
        return y + h * getF(x, yNew);
    }

    public static double getPredictCorrector(double x1, double x2, double y1, double h) {
        //double y2 = getYEuler(x1, y1, h);             //Точно
        double y2 = getYModifiedEuler(x1, x2, y1, h);    //Очень точно
        return y1 + h * ((getF(x1, y1) + getF(x2, y2)) / 2);
    }

    public static double getF(double x, double y) {
        return V * Math.pow(x, 3.0) + (3 * V + 1) * Math.pow(x, 2.0) + 3 * x + 1 - y;
    }

    public static void printArray(double[] array) {
        System.out.print("| ");
        for (double value : array) {
            System.out.print(String.format("%(.2f | ", value));
        }
        System.out.println();
    }
}
