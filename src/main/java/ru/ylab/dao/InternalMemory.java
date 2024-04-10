package ru.ylab.dao;

import ru.ylab.entitys.CustomWorkOut;
import ru.ylab.entitys.Users;
import ru.ylab.entitys.WorkOut;


import java.util.*;


public class InternalMemory {
    public static List<Users> usersList = new ArrayList<>();
    public static List<WorkOut> workOutsList = new ArrayList<>();

    public static Map<String, List<WorkOut>> workOutsMap = new HashMap<>();


}
