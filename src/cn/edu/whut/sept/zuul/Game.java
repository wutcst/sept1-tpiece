/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;

import java.util.Random;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private String backdirection=null;//返回方向
    private String backstack[]=new String[100];//返回方向栈
    private int backnum=0;//返回方向的数量
    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office,auxiliary;

        // create the rooms
        outside = new Room("outside the main entrance of the university",0);
        theater = new Room("in a lecture theater",2);
        pub = new Room("in the campus pub",2);
        lab = new Room("in a computing lab",5);
        office = new Room("in the computing admin office",4);
        auxiliary = new Room("in the auxiliary room",3);//辅助房间。随机传送

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("south",auxiliary);

        auxiliary.setExit("north",office);

        currentRoom = outside;  // start game outside
    }

    /**
     *  游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * 向用户输出欢迎信息.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * 执行用户输入的游戏指令.
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        /**
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
         */
        switch (commandWord){
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            case "back":
                goback();//扩充
                break;
            case "look":
                lookRoom();//扩充
                break;
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(nextRoom.getShortDescription().equals("in the auxiliary room")){
            int num=(int)(Math.random()*50)+1;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            for(int i=0;i<num;i++){
                int rdirection=new Random().nextInt(4);
                switch (rdirection){
                    case 0:
                        direction="east";
                        break;
                    case 1:
                        direction="south";
                        break;
                    case 2:
                        direction="west";
                        break;
                    case 3:
                        direction="north";
                        break;
                }
                nextRoom = currentRoom.getExit(direction);
                if(nextRoom==null){}
                else{
                    currentRoom = nextRoom;
                }
            }
            if(currentRoom.getShortDescription().equals("in the auxiliary room")){
                nextRoom = currentRoom.getExit("north");
                currentRoom = nextRoom;
            }
            else{
                System.out.println(currentRoom.getLongDescription());
            }
        }
        else {
            switch(direction){
                case "east":
                    backdirection="west";
                    break;
                case "west":
                    backdirection="east";
                    break;
                case "south":
                    backdirection="north";
                    break;
                case "north":
                    backdirection="south";
                    break;
            }
            backstack[backnum++]=backdirection;/////////
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * 执行back命令，返回上一个房间
     */
    private void goback()
    {
        if(backnum>0){
            backdirection=backstack[--backnum];
            Room nextRoom = currentRoom.getExit(backdirection);
            if(nextRoom==null){
                System.out.println("back is null");
            }
            else{
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
            }
        }
        else{
            System.out.println("back is null");
        }
    }

    /**
     * 执行look命令，查看当前房间内的物品描述和重量
     */
    private void lookRoom()
    {
        System.out.println(currentRoom.getweight());
    }
    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

