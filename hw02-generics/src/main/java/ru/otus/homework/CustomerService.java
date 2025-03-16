package ru.otus.homework;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final NavigableMap<Customer, String> customersMap = new TreeMap<>(Comparator.comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return getEntry(customersMap.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (customersMap.higherEntry(customer) == null) {
            return null;
        }
        return getEntry(customersMap.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        customersMap.put(customer, data);
    }

    private Map.Entry<Customer, String> getEntry(Map.Entry<Customer, String> e) {
        return Map.entry(
                new Customer(e.getKey().getId(), e.getKey().getName(), e.getKey().getScores()),
                e.getValue()
        );
    }
}
