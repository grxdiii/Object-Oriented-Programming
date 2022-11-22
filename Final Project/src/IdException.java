class IdException extends Exception {
    IdException(){}

    IdException(String message){
        super(message);
    }

    IdException(Throwable cause){
        super(cause);
    }

    @Override
    public String getMessage(){
        return "Invalid ID format. Must be LetterLetterDigitDigitDigitDigit";
    }
}
