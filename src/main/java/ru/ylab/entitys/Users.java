package ru.ylab.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.ylab.entitys.enums.UserRole;

/**
 * Класс сущности пользователь
 */

@Getter
@Setter
@Builder
public class Users {
    private String username;
    private String password;
    private UserRole role;

    private boolean active;
}
