# Company
Приложение-тестовое задание

# Скриншоты

<img src="https://github.com/polyblack/Company-app/blob/main/screenshots/screenshot_7.jpg"/>

# Что используется
Retrofit 2, RxJava 2, LiveData, ViewModel, Navigation Component, Room. Архитектура MVVM.
Вложенные сущности (specialities из employees) решила хранить в БД через связную таблицу, а не сохраняя специальности в сотрудников через @TypeConverters. Такой подход мне кажется чище касаемо организации базы данных. Но может, второе решение лучше.
# Задание 

1 активити и много фрагментов

Функциональные требования.

Приложение должно реализовывать следующие функциональные требования:

1. Просмотр списка специальностей
2. Просмотр списка работников, работающих по выбранной специальности.
Выводится имя, фамилия и возраст работника.
3. Просмотр детальной информации о работнике.
Выводится имя, фамилия, дата рождения, возраст, специальность.

Приложение должно получить данные по сети. Сохранить их в БД (SQLite)

Имя и фамилия отображаются с заглавной буквы.

Дата рождения отображается в формате число.месяц.год. Пример: 22.11.1987 г.
В случае отсутствия даты, показывать прочерк «­«

Минимальная версия android: 4.0
Разрешается использовать любые сторонние библиотеки.
