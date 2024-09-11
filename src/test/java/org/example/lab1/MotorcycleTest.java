package org.example.lab1;

import org.example.lab1.entities.Motorcycle;
import org.example.lab1.exeptions.ModelPriceOutOfBoundsException;
import org.example.lab1.exeptions.NoSuchModelNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Motorcycle")
public class MotorcycleTest {

    @Test
    @DisplayName("Добавление и удаление модели у мотоцикла")
    public void testAddAndRemoveModel() {
        Motorcycle motorcycle = new Motorcycle("Yamaha");
        motorcycle.addModel("Ninja", 15000);
        motorcycle.addModel("Harley", 25000);

        assertAll(
                () -> assertArrayEquals(new String[]{"Ninja", "Harley"}, motorcycle.getModelNames()),
                () -> assertEquals(15000, motorcycle.getModelPrice("Ninja")),
                () -> assertEquals(25000, motorcycle.getModelPrice("Harley"))
        );

        motorcycle.removeModel("Ninja");
        assertArrayEquals(new String[]{"Harley"}, motorcycle.getModelNames());
    }

    @Test
    @DisplayName("Установка цены модели для существующей модели у мотоцикла")
    public void testSetModelPrice() {
        Motorcycle motorcycle = new Motorcycle("Yamaha");
        motorcycle.addModel("Ninja", 15000);
        motorcycle.setModelPrice("Ninja", 16000);

        assertEquals(16000, motorcycle.getModelPrice("Ninja"));
    }

    @Test
    @DisplayName("Добавление модели с отрицательной ценой должно привести к исключению ModelPriceOutOfBoundsException")
    public void testAddModelWithNegativePrice() {
        Motorcycle motorcycle = new Motorcycle("Yamaha");
        assertThrows(ModelPriceOutOfBoundsException.class, () -> motorcycle.addModel("Ninja", -15000));
    }

    @Test
    @DisplayName("Удаление несуществующей модели должно привести к исключению NoSuchModelNameException")
    public void testRemoveNonExistentModel() {
        Motorcycle motorcycle = new Motorcycle("Yamaha");
        assertThrows(NoSuchModelNameException.class, () -> motorcycle.removeModel("NonExistentModel"));
    }
}