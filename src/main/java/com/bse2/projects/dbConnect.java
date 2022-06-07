package com.bse2.projects;

import java.sql.Connection;

public class dbConnect {
    DatabaseConnection con = new DatabaseConnection();
    Connection connectdb = con.getConnection();
}
