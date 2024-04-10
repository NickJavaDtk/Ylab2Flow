package ru.ylab.service.impl;

import ru.ylab.dao.InternalMemory;
import ru.ylab.entitys.Users;
import ru.ylab.logger.CustomLogger;
import ru.ylab.service.UsersInterface;

public class UsersInterfaceImpl implements UsersInterface {
    @Override
    public Boolean addUser(Users user) {
          Users current = getUserByLogin(user.getUsername( ));
        if (current == null) {
            CustomLogger.addMessageLogger("Пользователь " + user.getUsername( ) + " добавлен во внутреннюю память");
            return InternalMemory.usersList.add(user);

        } else {
            return false;
        }

    }

    @Override
    public Users getUserByLogin(String username) {
        return InternalMemory.usersList.stream()
                .filter(name -> name.getUsername().equals(username))
                .findAny().orElse(null);
    }

    @Override
    public Users getUserByIsActive() {
        return InternalMemory.usersList.stream()
                .filter(Users::isActive)
                .findAny().orElse(null);
    }

    @Override
    public void updateUserEntrance(Users user) {
        Users current = getUserByLogin(user.getUsername( ));
        int index = InternalMemory.usersList.indexOf(current);
        current.setActive(true);
        InternalMemory.usersList.set(index, current);
    }

    @Override
    public void updateUserExit(Users user) {
        Users current = getUserByLogin(user.getUsername( ));
        int index = InternalMemory.usersList.indexOf(current);
        current.setActive(true);
        InternalMemory.usersList.set(index, current);
    }
}
