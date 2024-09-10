package org.example.lab1;

import org.example.lab1.entities.Car;
import org.example.lab1.interfaces.Transport;
import org.example.lab1.utilities.TransportUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для утилит транспортных средств")
public class TransportUtilsTest {

    @Test
    @DisplayName("Вычисление средней цены моделей для транспортного средства")
    public void testGetAveragePrice() {
        Car car = new Car("Toyota", 2);
        car.addModel("Corolla", 20000);
        car.addModel("Camry", 30000);

        double averagePrice = TransportUtils.getAveragePrice(car);
        assertEquals(25000, averagePrice);
    }

    @Test
    @DisplayName("Вывод всех моделей для данного транспортного средства")
    public void testPrintAllModels() {
        Car car = new Car("Toyota", 2);
        car.addModel("Corolla", 20000);
        car.addModel("Camry", 30000);
    }

    @Test
    @DisplayName("Вывод всех цен на модели для данного транспортного средства")
    public void testPrintAllPrices() {
        Car car = new Car("Toyota", 2);
        car.addModel("Corolla", 20000);
        car.addModel("Camry", 30000);
    }
}
