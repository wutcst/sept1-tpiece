package cn.edu.whut.sept.zuul;

public class CommandWords
{
    /**
    *返回提示信息
     */
    private static final String[] validCommands = {
            "go", "quit", "help","back","look"
    };

    /**
     * 构造函数
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * 识别传入的字符串是否为命令.
     * @param aString 传入一个字符串.
     * @return 返回一个布尔值.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        return false;
    }

    /**
     * 方法：显示全部命令.
     * 用于显示全部命令.
     */
    public void showAll()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
