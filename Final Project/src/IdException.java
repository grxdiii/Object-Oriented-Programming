class IdException extends Exception {
    IdException(){}

    IdException(String message){
        super(message);
    }

    IdException(Throwable cause){
        super(cause);
    }

    public static void checkFormat(String id) throws IdException {
        if(id.length() != 6 || !id.substring(0,1).matches("[a-zA-ZX]+") ||
                !id.substring(2,5).matches("[0-9]+")) throw new IdException();
    }

    @Override
    public String getMessage(){
        return "\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit\n";
    }
}
