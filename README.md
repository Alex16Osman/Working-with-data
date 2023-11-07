# **Working-with-data** 
## Данный проект охватывает такие операции, как: ##
- Навыки работы с лямбда-выражениями и Stream API;
- Исключения, отладка, тестирование и логирование;
- Работа с файлами и сетью.
_____
### 1. Лямбды и стримы ###
 [Работа][1], которая представлена у меня на данную тему давольно 
 простая. Она реализована 3-мя способами.      

  >Сортировка сотрудников по зарплате и алфавиту:
 ```java
  public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort((e1, e2)->{
            int comparison =
                    e1.getSalary().compareTo(e2.getSalary());
            if (comparison == 0) {
                return e1.getName().compareTo(e2.getName());
            }
            return comparison;
        });
    }
 ```
 ____
>Сортировка, которая выводит сотрудника с определенным годом прихода в компанию и максимальной зарплатой:
```java
 public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        return staff.stream()
        .filter((e) -> e.getWorkStart().getYear() == year)
        .max((e1, e2) ->
            e1.getSalary().compareTo(e2.getSalary()))
        .get();
    }
```
___
>Реализация метода, который возвращает список рейсов вылетающих в ближайшие 2 часа:  
```java
 public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        final  int TWO_HOURS = 7_200_000;
        return airport.getTerminals().stream()
                .map(Terminal::getFlights).flatMap(List::stream)
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE
                && flight.getDate().getTime() > System.currentTimeMillis()
                && flight.getDate().getTime() < System.currentTimeMillis() + TWO_HOURS)
                .collect(Collectors.toList());
    }
```
> Классы с реализацией getter and setter лежат в папке `lib` в [.jar][2]
___
### 2. Исключения, отладка, тестирование и логирование ###
По данной теме обращю ваше внимание ко второй работе.
В ней совмещены спразу 2 модуля, но затроним мы именно отладку приложения и тестировку.  

>Для начала рассмотрим с Вами рефакторинг исключений, ну или как говорят программисты **"Exeption"**.

> И так пример реализации:
```java
 public void addCustomer(String data) throws InvalidEmailFormatException {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong command size");
        }
        String phoneNumber = components[INDEX_PHONE];
        String email = components[INDEX_EMAIL];
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if (!isValidEmail(components[2])) {
            throw new InvalidEmailFormatException("Invalid email format: " + email);
        }
        if (!components[3].matches("^\\+?\\d{1,2}\\(?\\d{1,4}\\)?\\d{7}$")) {
            throw new IllegalArgumentException("Invalid phone format: " + phoneNumber);
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }
```
>Также в той работе вы сможете найти граммотную реализацию лога через расширение *log4j*.

>>Конечно при просмотре работ вы заметите, что тесты я спользую везде, и в моем случае это **модульное тестирование**. То есть я сначала пишу тест, а потом на его фоне пишу реализацию метода - очень упрощает работу над кодом.<br>
Пример реализации вы сможете посмотреть в [первой][1] или [второй][3] работах.
### 3. Работа с файлами и сетью ###
Ну и напоследок самая, на мой взгляд, не менее важная тема, которая повсеместно затрагивается в любом веб-приложении и топу подобных.<br> 
**Основные пункты, которые реализованы в самой работе:**
> - Парсинг HTML, JSON, CVS фвйлов.
> - Запись полученных данных в 2 новых JSON-файла.
> - Шаблонная реализация исходного класса (getter - setter)

## Итог: ##
> Подводя итоги, данный проект был создан для показа реализованных мной методов. Для более детального рассмотрения кода, прошу перейти к основным папкам репозитория.
### Спасибо большое, что посетили мой GitHub! Хорошего дня! ###



[1]: (https://github.com/Alex16Osman/Working-with-data/tree/main/AdvancedOOPFeatures)
[2]: (https://github.com/Alex16Osman/Working-with-data/blob/main/AdvancedOOPFeatures/DepartureBoard/Airport/lib/airport-1.0.1.jar)
[3]: (https://github.com/Alex16Osman/Working-with-data/tree/main/ExceptionsDebuggingTestingAndParse)
