package ru.yaharuott.pkg.exeptions;

public class WrongScoreException extends RuntimeException{
    public WrongScoreException(){
        super("Wrong score (score cannot be lower than 0 and be same)");
    }
}
