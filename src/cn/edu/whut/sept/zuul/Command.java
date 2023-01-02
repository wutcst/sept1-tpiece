package cn.edu.whut.sept.zuul;

public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     *
     * @param firstWord
     * @param secondWord
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     *
     * @return 返回第一个命令
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     *
     * @return 返回第二个命令
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     *
     * @return 若第一个命令不为空则返回true，否则返回false
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     *
     * @return 若第二个命令不为空则返回true，否则返回false
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
