package cn.edu.whut.sept.zuul;

import java.util.Set;
import java.util.HashMap;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private int num;//物品数量
    private int[] weight=new int[10];//物品重量
    private String[] thing=new String[10];//物品描述

    /**
     *构造函数，构建地图
     * @param description
     */
    public Room(String description,int num)
    {
        this.description = description;
        exits = new HashMap<>();
        this.num=num;
        for(int i=0;i<num;i++)
        {
            weight[i]=(int)(Math.random()*1000-1);
            thing[i]="第"+(i+1)+"件物品:";
        }
    }

    /**
     * 设置房间可前往的方向.
     * @param direction 传入方向.
     * @param neighbor 传入相邻的房间.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * 方法：获取玩家当前房间描述.
     * @return 返回玩家当前房间描述.
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * 方法：获得玩家的当前位置及房间的全部信息.
     * 包括：所在位置、房间描述、出口信息等.
     * @return 返回玩家的当前位置及房间的全部信息.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * 方法：获得玩家的当前房间的全部信息.
     * 包括：房间描述、出口信息等.
     * @return 返回玩家的当前房间信息.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * 离开当前房间.
     * @param direction 传入要去往的方向.
     * @return 返回前往方向的出口.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * 房间物品描述  扩充
     * @return 返回物品描述字符串
     */
    public String getweight()
    {
        String s = "";
        for(int i=0;i<num;i++){
            s+=thing[i]+weight[i]+"\n";
        }
        return s;
    }
}


