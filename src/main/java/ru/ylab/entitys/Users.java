package ru.ylab.entitys;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.ylab.entitys.enums.UserRole;

/**
 * Класс сущности пользователь
 */

@Data
@Builder
public class Users {
    private String username;
    private String password;
    private UserRole role;
    private boolean active;
}
