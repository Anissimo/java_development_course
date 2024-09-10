package org.example.lab1.utilities;

import org.example.lab1.interfaces.Transport;

public class TransportUtils {
    public static double getAveragePrice(Transport transport) {
        double[] prices = transport.getModelPrices();
        if (prices.length == 0) return 0;
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static void printAllModels(Transport transport) {
        String[] models = transport.getModelNames();
        for (String model : models) {
            System.out.println(model);
        }
    }

    public static void printAllPrices(Transport transport) {
        double[] prices = transport.getModelPrices();
        for (double price : prices) {
            System.out.println(price);
        }
    }
}
