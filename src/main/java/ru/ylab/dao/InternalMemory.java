package ru.ylab.dao;

import ru.ylab.entitys.Users;
import ru.ylab.entitys.WorkOut;


import java.util.ArrayList;
import java.util.List;


public class InternalMemory {
    public static List<Users> usersList = new ArrayList<>();
    public static List<WorkOut> workOutsList = new ArrayList<>();
}
