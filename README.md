Сделать условный трекер задач.

Возможность строить отчеты,количество задач предполагаемое 100к+.

Использовать любые (Spring,Spring Boot...)? фреймворки,БД и т.д. на своё усмотрение.

Достаточно набросать абстрактную структуру приложения.



Существует внешний сервис который хранит дополнительную информацию по пользователю (рейтинг к примеру 1..3).(к примеру REST)



Элементы:



Подразделение.\
Пользователь (name,id подразделения).\
Таска.(дата создания,тема,описание,автор,исполнитель,статус)\
Комментарии к таске (могут быть от разных пользователей).\
Вложения к задаче (любые файлы).




Нужны REST методы:



Получения списка задач (возможность фильтрации по подразделению+сортировка по дате создания).Должен возвращать полную информацию по подразделению,пользователю (включая рейтинг.Если сервис с доп.инфо недоступен-значение по умолчанию).
Получение деталей конкретной задачи (комменты,вложения).
Создание задачи.
Изменения задачи (статус,исполнитель).
Добавление комментария.
Удаление комментария.