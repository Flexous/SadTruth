package backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gui.MainGui;
import gui.OptionDialog;

public class Application
{
    public static MainGui mainGui;

    public static void main(String[] args) 
    {
		OptionDialog optionDialog = new OptionDialog();
        optionDialog.setTitle("Crying");
    }

    public static long calcYourFailure(String input)
    {
		try 
        {
			Date birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(input);
            Date currentDate = new Date();  
            
            return (currentDate.getTime()-birthDate.getTime())/1000;
		} 
        catch (ParseException e) 
        {
			e.printStackTrace();
		}  
        return -1;
    }
}