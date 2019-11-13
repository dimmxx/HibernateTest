package mate;

import mate.controller.DbService;

public class App
{
    public static void main( String[] args){

        DbService dbService = new DbService();
        dbService.printConnectionInfo();
        dbService.addUser("Dima");



    }
}
