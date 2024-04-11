package ru.ylab.console;

import ru.ylab.dao.InternalMemory;
import ru.ylab.entitys.AdditionalInformation;
import ru.ylab.entitys.CustomWorkOut;
import ru.ylab.entitys.Users;
import ru.ylab.entitys.WorkOut;
import ru.ylab.entitys.enums.UserRole;
import ru.ylab.exception.IOReadConsoleException;
import ru.ylab.in.ReadConsole;
import ru.ylab.logger.CustomLogger;
import ru.ylab.security.AuthenticationUser;
import ru.ylab.security.RegistrationUser;
import ru.ylab.service.impl.EnterTrainingImpl;
import ru.ylab.service.impl.UsersInterfaceImpl;
import ru.ylab.service.impl.WorkOutInterfaceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class UIclass {
    /**
     * Строка делитель разделяющая важные логический блоки (Регистарация, авторизация пользователя, ввод данных, запрос отчетов) в консоли.
     */
    public static final String DELIMITER = "============================================================================================";
    private static final String STARTGENERAL = "Здравствуйте данный диалог поможет соринтироваться в чате";
    private static final String START = "Алгоритм действий такой - сначала мы создаем базу пользователей \n" +
            "Затем мы авторизуемся под созданными пользователями и в зависимости от роли пользователя\n" +
            "просматриваем список доступных тренировок, вносим свои тренировки, вносим дополниельную информацию \n" +
            "корректируем или удаляем свои тренировки, просматриваем статистику тренировок";

    private static final String HELLO = "Для начала представьтесь. Нажмите 1 чтобы зарегистрироваться";

    private static final String ACTIONUSER = "Вы авторизовались как пользователь. Вам доступны функции: Добавление в дневник своей тренировки,\n" +
            "Просмотр доступных тренировок, Просмотр и корректировка своих тренировок,  Анализ прогресса";
    private static final String ACTIONADMIN = "Вы авторизовались как администратор. Вам доступны функции: Просмотр тренировок всех пользователей\n" +
            "Аудит действий пользователей";
    private static final String ACTIONUSERNUMBER = "Выберите 1 для добавление в дневник свой тренировки, 2 Для просмотра доступных\n" +
            "тренировок, 3 Для просмотра и корректировки своих тренировок, 0 для выхода из программы";
    private static final String ACTIONADMINNUMBER = "Выберите 4 для просмотра показаний счетчиков всех пользователей, 5 для просмотра\n" +
            "показаний счетчиков за конкретный месяц, 6 для просмотра истории подачи показаний, 7 Просмотр лога, 0 для выхода из программы";

    ReadConsole readConsole = new ReadConsole();
    RegistrationUser registration = new RegistrationUser();
    UsersInterfaceImpl usersInterfaceImp = new UsersInterfaceImpl();
    EnterTrainingImpl enterTraining = new EnterTrainingImpl();

    AuthenticationUser authenticationUser = new AuthenticationUser();

    WorkOutInterfaceImpl work = new WorkOutInterfaceImpl();
//    EnterReadingsMeter enterReadingsMeter = new EnterReadingsMeter( );
//    MetersDataImp metersDataImp = new MetersDataImp( );

    /**
     * Метод является обычной оберткой метода println() класса System
     *
     * @param message текст отправляемый в консоль
     * @see {@link System#out}
     */
    public static void sendConsole(String message) {
        System.out.println(message);
    }


    /**
     * Метод обеспечивающий стадию регистрации пользователя.
     *
     * @return Возвращает булево значения чтобы выйти из цикла регистрации
     * @throws IOReadConsoleException при ошибке ввода цифры не указанной в подсказках
     * @throws ClassCastException     при ошибке ввода символов или не целых чисел
     * @see ReadConsole#readValue() читает данные с консоли и возвращает обертку типа Object для приведения к
     * соответствующему типу
     * @see RegistrationUser#createUsers() создает пользователя
     * @see CustomLogger#Logger записывает в лог
     */
    public boolean stageRegistrationUser() {
        if (InternalMemory.usersList.size() == 0) {
            sendConsole(STARTGENERAL);
            sendConsole(START);
            sendConsole(HELLO);
        } else {
            sendConsole("");
            sendConsole("Просьба зарегистрировать пользователя № " + (InternalMemory.usersList.size() + 1));
            sendConsole("Просьба ввести 1 для его регистрации. Если вы хотите отказаться от регистрации нажмите 0 для выхода из регистрации");
        }


        try {
            Integer value = (Integer) readConsole.readValue();
            if (value == 0) {
                return false;
            } else if (value == 1) {
                Users user = registration.createUsers();
                CustomLogger.addMessageLogger("Создан пользователь с логином " + user.getUsername() + " и ролью " + user.getRole());
                sendConsole("Создан пользователь с логином " + user.getUsername() + " и ролью " + user.getRole());
                usersInterfaceImp.addUser(user);
                return true;
            } else {
                CustomLogger.addMessageLogger("Введена цифра которая не указана в подсказках консоли");
                throw new IOReadConsoleException("Вы ввели цифру не указанную в диалоге. Просьба ввести цифру 1");
            }
        } catch (ClassCastException exception) {
            CustomLogger.addMessageLogger("Пользователь на этапе выбора регистрации/авторизации вместо цифры 1  ввел символы");
            throw new IOReadConsoleException("Введено не целое число. Просьба в данном поле ввести 1");

        }

    }

    /**
     * Метод обеспечивающий стадию аутентификации пользователя. После успешной аутентификации пользователю преедоставляется возможность выполнить действие
     * (ввести информацию о показаниях счетчика, Просмотреть отчеты). Также вносит информацию в логгер
     *
     * @return Возвращает булево значения чтобы выйти из цикла авторизации
     * @throws IOReadConsoleException при ошибке ввода цифры не указанной в подсказках
     * @throws ClassCastException     при ошибке ввода символов или не целых чисел
     * @see AuthenticationUser#authentication() аутентифицирует пользователя по имени и паролю
     * @see UsersInterfaceImpl#getUserByIsActive() возвращает пользователя который стал активным (прошел процедуру авторизации)
     * @see ReadConsole#readValue() читает данные с консоли и возвращает обертку типа Object для приведения к
     * соответствующему типу
     * @see CustomLogger#Logger список показаний счетчиков в памяти приложения
     * @see CustomLogger#addMessageLogger(String)  логгирует действия пользвотеля
     */

    public boolean stageAuthenticationUser() {
        authenticationUser.authentication();
        Users userAuthentication = usersInterfaceImp.getUserByIsActive();
        if (getUserRoleIsActive().equals(UserRole.USER)) {
            sendConsole(ACTIONUSER);
            sendConsole(ACTIONUSERNUMBER);
        } else if (getUserRoleIsActive().equals(UserRole.ADMIN)) {
            sendConsole(ACTIONADMIN);
            sendConsole(ACTIONADMINNUMBER);
        }
        try {
            Integer value = (Integer) readConsole.readValue();
            if (value == 0) {
                return false;
            } else if (value == 1) {
                enterTraining.createTraining(userAuthentication);
            } else if (value == 2) {
                for (WorkOut workOut : InternalMemory.workOutsList) {
                    CustomWorkOut custom = (CustomWorkOut) workOut;
                    sendConsole(custom.toStringListTraining());
                }
            } else if (value == 3) {
               viewCorrectDiaryTraining(userAuthentication);
            } else if (value == 4) {
//                Map<String, Integer> mapMeter = metersDataImp.getCurrentIndicationMeter(userAuthentication);
//                mapMeter.entrySet( ).forEach(System.out::println);
            } else if (value == 5) {
                sendConsole("Введите номер месяца цифрой от 1 до 12");
                try {
                    Integer valueMonth = (Integer) readConsole.readValue();
                    if ((13 <= valueMonth) || (valueMonth <= 0)) {
                        CustomLogger.addMessageLogger("Введен неправильно номер месяца");
                        throw new IOReadConsoleException("Вы ввели цифру не указанную в диалоге. Просьба ввести цифру от 1 до 12");
                    } else {
//                        switchReaderMeterMonth(userAuthentication, valueMonth);
                    }
                } catch (ClassCastException exception) {
                    CustomLogger.addMessageLogger("Пользователь на этапе ввода номера месяца вместо цифр от 1 до 12  ввел символы");
                    throw new IOReadConsoleException("Вы ввели цифру не указанную в диалоге. Просьба ввести цифру от 1 до 12");

                }
            } else if (value == 6) {
//                MetersData.metersDataList.forEach(System.out::println);
            } else if (value == 7) {
                CustomLogger.Logger.forEach(System.out::println);
            } else {
                CustomLogger.addMessageLogger("Введена цифра которая не указана в подсказках консоли");
                throw new IOReadConsoleException("Вы ввели цифру не указанную в диалоге.");
            }
        } catch (ClassCastException exception) {
            CustomLogger.addMessageLogger("Пользователь на этапе выбора регистрации/авторизации вместо цифры 1  ввел символы");
            throw new IOReadConsoleException("Введено не целое число. Просьба в данном поле ввести 1");

        }
        return true;

    }

    /**
     * Данный метод проверяет роль авторизаванного пользователя и в соответствии с ролью выводит сообщения и предоставляет методы
     *
     * @return UserRole возвращает роль пользователя
     * @see UIclass#stageAuthenticationUser()#getUserRoleIsActive()
     */
    private UserRole getUserRoleIsActive() {
        return usersInterfaceImp.getUserByIsActive().getRole();
    }

    private void viewCorrectDiaryTraining(Users users) {
        boolean exit = true;
        while (exit) {
            sendConsole("Введите 1 для просмотра своего дневника тренировок и для корректировки или удаления записи в дневнике,\n" +
                    "0 для выхода");
            int value = (int) readConsole.readValue();
            switch (value) {
                case 1 -> {
                    Map<Integer, CustomWorkOut> map = work.viewRecordDiary(users);
                    sendConsole("Выберите запись дневника для корректировки или удаления");
                    int choice = (int) readConsole.readValue();
                    sendConsole("Выбрана запись дневника под номером " + choice);
                    CustomWorkOut recordMap = map.get(choice);
                    CustomWorkOut recordInternalMemory = work.getCustomWork(recordMap.getDataTraining(), recordMap.getTypeTraining(), users);
                    sendConsole(UIclass.DELIMITER);
                    sendConsole("Введите 1 для корректировки данной записи дневника, 2 для удаления записи дневника");
                    int choiceEditDelete = (int) readConsole.readValue();
                    if (choiceEditDelete == 1) {
                        sendConsole("Введите 1 для корректировки даты тренировки, 2 для корректировки типа тренировки,\n" +
                                "3 для корректировки количества выполненных упражнений 4 для корректировки количества потраченных калорий");
                        int choiceEdit = (int) readConsole.readValue();
                        switch (choiceEdit) {
                            case 1 -> {
                                sendConsole("Введите Дату тренировки в формате dd.MM.yyyy например 10.04.2024\n");
                                LocalDateTime localDateTime = readConsole.readDate();
                                LocalDate data = localDateTime.toLocalDate();
                                work.checkTrainingDone(data, recordMap.getTypeTraining(), users);
                                recordInternalMemory.setDataTraining(data);
                                CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " изменил запись о дате тренировки от " +
                                        recordInternalMemory.getDataTraining() + " с типом тренировки " + recordInternalMemory.getTypeTraining());

                            }
                            case 2 -> {
                                sendConsole("Введите тип тренировки");
                                String type = readConsole.readString();
                                work.checkTrainingDone(recordInternalMemory.getDataTraining(), type, users);
                                recordInternalMemory.setTypeTraining(type);
                                CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " изменил запись о типе тренировки от " +
                                        recordInternalMemory.getDataTraining() + " с типом тренировки " + recordInternalMemory.getTypeTraining());

                            }
                            case 3 -> {
                                sendConsole("Введите длительность тренировки");
                                int time = (int) readConsole.readValue();
                                work.checkInputParameters(time, "Пользователь " + users.getUsername() + " ввел некорретную" +
                                        " длительность тренировки", "Введеное значение длительности тренировки должно быть больше нуля");
                                recordInternalMemory.setDurationTraining(time);
                                CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " изменил запись о длительности тренировки от " +
                                        recordInternalMemory.getDataTraining() + " с типом тренировки " + recordInternalMemory.getTypeTraining());

                            }
                            case 4 -> {
                                sendConsole("Введите количество потраченных калорий");
                                int count = (int) readConsole.readValue();
                                work.checkInputParameters(count, "Пользователь " + users.getUsername() + " ввел некорретное" +
                                        " количество потраченных калорий", "Введеное значение количества потраченных калорий должно быть больше нуля");
                                recordInternalMemory.setDurationTraining(count);
                                CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " изменил запись о количестве потраченных калорий и от " +
                                        recordInternalMemory.getDataTraining() + " с типом тренировки " + recordInternalMemory.getTypeTraining());

                            }
                            default -> {
                                CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " ввел цифру не указанную в подсказках");
                                throw new IOReadConsoleException("Вы ввели цифру не указанную в подсказках");
                            }

                        }
                    } else if (choiceEditDelete == 2) {
                        List<WorkOut> listWork = InternalMemory.workOutsMap.get(users.getUsername());
                        listWork.removeIf(list -> list.equals(recordInternalMemory));
                    } else {
                        CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " ввел цифру не указанную в подсказках");
                        throw new IOReadConsoleException("Вы ввели цифру не указанную в подсказках");
                    }

                }
                case 0 -> {
                    exit = false;
                }
                default -> {
                    CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " ввел цифру не указанную в подсказках");
                    throw new IOReadConsoleException("Вы ввели цифру не указанную в подсказках");
                }
            }
        }

    }
}
